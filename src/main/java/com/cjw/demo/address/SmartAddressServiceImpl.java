package com.cjw.demo.address;


import com.cjw.demo.address.entity.DataConstant;
import com.cjw.demo.address.entity.Result;
import com.cjw.demo.address.entity.SmartAddressResp;
import com.cjw.demo.address.util.Address;
import com.cjw.demo.address.util.AddressScanner;
import com.cjw.demo.util.StringUtils;
import org.apache.commons.collections.CollectionUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created by 01367808 on 2017/4/22.
 */
@Service
public class SmartAddressServiceImpl  implements SmartAddressMatchingService{

    private static final Logger logger = LoggerFactory.getLogger(SmartAddressServiceImpl.class);

    private static final List<String> familyNameList = Arrays.asList(DataConstant.FAMILY_NAMES.split(","));//百家姓

    /**
     * 获取解析结果
     *
     * @param inputAddress
     * @return
     */
    @Override
    public Result<SmartAddressResp> getAddressDividing(String inputAddress) {
        logger.info("getAddressDividing_input_address:{}", inputAddress);
        Result<SmartAddressResp> respResult = new Result<SmartAddressResp>(true);
        try {
            //如果使用特殊分隔符分隔后是三个数据,则使用拆分后的数据
            final List<String> strings = filterBySymbol(inputAddress);
            if (CollectionUtils.isNotEmpty(strings)) {
                final SmartAddressResp smartAddressResp = getAddressBySymbol(strings);
                respResult.setObj(smartAddressResp);
                return respResult;
            }
            //去掉特殊符号
            inputAddress = stringFilter(inputAddress).trim();
            final String phone = getPhone(inputAddress);
            //去掉输入字符串里的电话号码
            final String replace = inputAddress.replace(phone, "");
            Address.Info address = AddressScanner.scan(replace).info();
            final String name = getName(address, phone, inputAddress);
            SmartAddressResp resp = new SmartAddressResp();
            resp.setTelephone(phone);
            resp.setCity(address.getCityAddress());
            resp.setProvince(address.getProvinceAddress());
            resp.setPersonalName(name);
            resp.setArea(address.getAreaAddress());
            resp.setSite(address.getDetailAddress());
            respResult.setObj(resp);
        } catch (Exception e) {
            logger.error("解析地址出错,地址:{},{}", inputAddress, e.getMessage(), e);
            respResult.setSuccess(false);
            respResult.setErrorMessage("解析失败");
        }
        return respResult;
    }

    private String getName(Address.Info address, String phone, String inputAddress) {
        String old = inputAddress;
        inputAddress = inputAddress.replace(phone, "");
        final String[] split = old.split(phone);
        List<String> splitList = new ArrayList<String>();
        for (String s : split) {
            if (StringUtils.isNotBlank(s)) {
                splitList.add(s);
            }
        }
        splitList=Arrays.asList(split);
        String detailAddress = address.getDetailAddress();
        String areaAddress = address.getAreaAddress();
        String provinceAddress = address.getProvinceAddress();
        final String cityAddress = address.getCityAddress();
        String name = "";
        //如果地址姓名和地址用手机号码隔开
        if (splitList.size() == 2) {
            for (int i = 0; i < splitList.size(); i++) {
                String s = splitList.get(i);
                if ((StringUtils.isNotBlank(cityAddress) && s.contains(cityAddress.substring(0, 2)))
                        || (StringUtils.isNotBlank(provinceAddress) && s.contains(provinceAddress.substring(0, 2)))
                        || (StringUtils.isNotBlank(areaAddress) && s.contains(areaAddress.substring(0, 2)))) {
                    name = splitList.get(1 - i);
                }
            }
            //地址里面不包含省市区,那么就根据字符长度,以及是否包含百家姓来分割名字和地址
            if (StringUtils.isBlank(name)) {
                String first = splitList.get(0);
                String two = splitList.get(1);
                if (first.length() < two.length()) {
                    for (String s : familyNameList) {
                        if (first.contains(s)) {
                            name = first;
                            break;
                        }
                    }
                } else {
                    name = two;
                }
            }
            address.setDetailAddress(detailAddress.replace(name, ""));
            return name;
        }
        int provinceIndex = -1;
        int cityIndex = -1;
        int areaIndex = -1;
        //String replaceProvince = "";
        if (StringUtils.isNotBlank(provinceAddress)) {
            //replaceProvince = provinceAddress.replace("省", "");
            //detailAddress = detailAddress.replace(replaceProvince,"");
            //截取第一个字符判断
            provinceIndex = inputAddress.indexOf(provinceAddress.substring(0, 2));
        }
        //String replaceCity = "";
        if (StringUtils.isNotBlank(cityAddress)) {
            //replaceCity = cityAddress.replace("市", "");
            //detailAddress = detailAddress.replace(replaceCity,"");
            cityIndex = inputAddress.indexOf(cityAddress.substring(0, 2));
        }
        //String replaceArea = "";
        if (StringUtils.isNotBlank(areaAddress)) {
            //replaceArea = areaAddress.replace("区", "");
            //detailAddress = detailAddress.replace(replaceArea,"");
            areaIndex = inputAddress.indexOf(areaAddress.substring(0, 2));
        }


        int index = -1;
        //如果用户输入的开头是地址,那么名字就位于最后
        if ((index = provinceIndex) > -1 || (index = cityIndex) > -1 || (index = areaIndex) > -1) {
            if (index == 0) {
                int resultIndex = -1;
                for (String s : familyNameList) {
                    int currentIndex = detailAddress.lastIndexOf(s);
                    //遍历详细地址,获取最后面的姓,如果最后一个字是姓,则忽略()
                    if (currentIndex < detailAddress.length() - 1 && currentIndex > resultIndex) {
                        resultIndex = currentIndex;
                    }
                    //resultIndex = resultIndex > currentIndex?resultIndex:currentIndex;
                }
                if (resultIndex > -1) {
                    name = detailAddress.substring(resultIndex);
                }
            } else {
                int resultIndex = -1;
                for (String s : familyNameList) {
                    int currentIndex = inputAddress.indexOf(s);
                    //遍历详细地址,获取第一个姓
                    if (currentIndex > -1) {
                        if (resultIndex > -1) {
                            resultIndex = resultIndex < currentIndex ? resultIndex : currentIndex;
                        } else {
                            resultIndex = currentIndex;
                        }
                    }
                    if (currentIndex > -1 && currentIndex < index) {
                        name = inputAddress.substring(resultIndex, index);
                    }
                }
            }
        } else {
            int resultIndex = -1;
            for (String s : familyNameList) {
                int currentIndex = detailAddress.lastIndexOf(s);
                //遍历详细地址,获取最后面的姓
                if (currentIndex > resultIndex && currentIndex != detailAddress.length() - 1) {
                    resultIndex = currentIndex;
                }
                //resultIndex = resultIndex > currentIndex?resultIndex:currentIndex;
            }
            if (resultIndex > -1) {
                name = detailAddress.substring(resultIndex);
            }
        }
        address.setDetailAddress(detailAddress.replace(name, ""));

        return name;
    }

    private String getPhone(String sParam) {
        if (sParam.length() <= 0)
            return "";
        //Pattern pattern = Pattern.compile("(\\+86)?((1[3578]\\d{9})|(0\\d{12})|((\\d{3,5})?-?(\\d{7,8}))|((\\d{3})-(\\d{4})-(\\d{4})))");
        //只保留手机号码的匹配逻辑,匹配最后一个
        Pattern pattern = Pattern.compile("(\\+86)?((1[3578]\\d{9})|((1[3578]\\d)-(\\d{4})-(\\d{4})))");
        Matcher matcher = pattern.matcher(sParam);
        String phone = "";
        while (matcher.find()) {
            phone = matcher.group();
        }
        //获取手机为空时再去获取座机
        if (StringUtils.isBlank(phone)) {
            return getSeatPhone(sParam);
        }
        return phone;
    }

    private String getSeatPhone(String sParam) {
        Pattern pattern = Pattern.compile("(\\+86)?((0\\d{12})|((\\d{3,5})?-?(\\d{7,8})))");
        Matcher matcher = pattern.matcher(sParam);
        if (matcher.find()) {
            return matcher.group();
        }
        return "";
    }


    /**
     * 过滤特殊字符
     *
     * @param str
     * @return
     * @throws PatternSyntaxException
     */
    private static String stringFilter(String str) throws PatternSyntaxException {
        //str = str.replaceAll("((\\+)86( )?)","");
        String regEx = "(((\\+)86( )?)|[`~!@$%^&*+=|{}':;',\\[\\].<>/?~！@#￥%……&*+|{}【】‘；：”“’。，、？\n ])";
        return str.replaceAll(regEx, "");
    }

    private SmartAddressResp getAddressBySymbol(List<String> strs) {
        String phone = "";
        String address = "";
        String name = "";
        //判断数组里面那个是电话
        for (int i = 0; i < strs.size(); i++) {
            String str = strs.get(i);
            if (StringUtils.isNotBlank(getPhone(str))) {
                phone = str;
                //停止循环
                i = 4;
            }
        }
        strs.remove(phone);
        Address.Info addressInfo = null;
        //判断数组里面哪个是地址
        String provinceAddress = "";
        for (int i = 0; i < strs.size(); i++) {
            String str = strs.get(i);
            addressInfo = AddressScanner.scan(str).info();
            provinceAddress = addressInfo.getProvinceAddress();
            if (StringUtils.isNotBlank(provinceAddress)) {
                address = str;
                name = strs.get(1 - i);
                //终止循环
                i = 2;
            }
        }
        //如果地址没有查找出来,继续判断名字
        if (StringUtils.isBlank(name)) {
            for (int i = 0; i < strs.size(); i++) {
                String one = strs.get(i);
                String two = strs.get(1 - i);
                for (String s : familyNameList) {
                    final int currentIndex = one.indexOf(s);
                    if (currentIndex == 0) {
                        name = one;
                        address = two;
                        //退出循环
                        i = 2;
                    } else if (currentIndex > -1) {
                        name = one;
                        address = two;
                        //结果不一定对,继续循环
                    }
                }
                //如果姓名还为空,则判断长度,长度小的作为名字,长度大的作为
                if (StringUtils.isBlank(name)) {
                    if (one.length() <= two.length()) {
                        name = one;
                        address = two;
                    } else {
                        name = two;
                        address = two;
                    }
                }
            }
        }
        SmartAddressResp smartAddressResp = new SmartAddressResp();
        smartAddressResp.setPersonalName(name);
        smartAddressResp.setTelephone(phone);
        if (addressInfo != null && StringUtils.isNotBlank(provinceAddress)) {
            smartAddressResp.setProvince(provinceAddress);
            smartAddressResp.setSite(addressInfo.getDetailAddress());
            smartAddressResp.setArea(addressInfo.getAreaAddress());
            smartAddressResp.setCity(addressInfo.getCityAddress());
        } else {
            smartAddressResp.setSite(address);
        }
        return smartAddressResp;
    }

    private List<String> filterBySymbol(String inputAddress) {
        for (String symbol : DataConstant.SYMBOL_LIST) {
            final String[] split = inputAddress.split(symbol);
            List<String> splitList = new ArrayList<>();
            for (String s : split) {
                if (StringUtils.isNotBlank(s)) {
                    splitList.add(s);
                }
            }
            if (splitList.size() == 3) {
                return splitList;
            }
        }
        return null;
    }

}






