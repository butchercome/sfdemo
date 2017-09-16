//package com.cjw.demo.thread;
//
//import java.math.BigInteger;
//import java.util.Random;
//
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.sf.shiva.cache.SysConfigCache;
//import com.sf.shiva.constant.CommonConstant;
//import com.sf.shiva.domain.system.SysConfig;
//
//public class OrderNoModUtils {
//
//    private static final Logger logger = LoggerFactory.getLogger(OrderNoModUtils.class);
//
//    private OrderNoModUtils(){}
//
//    public static String orderNoMod(String orderNo){
//        if (StringUtils.isNotEmpty(orderNo)){
//            //从缓存中取出分库总量
//            SysConfig config = SysConfigCache
//                    .getOrderSystemBySystemCode(CommonConstant.SCHEMA_COUNT);
//            //判断值的存在
//            if (null != config && StringUtils.isNotEmpty(config.getConfigValue())){
//                try{
//                    return formatSchemaIndex(getMod(orderNo.trim(),Integer.parseInt(config.getConfigValue())));
//                }catch(Exception e){
//                    logger.error("OrderNoModUtils orderNoMod execute orderNo --{},Exception --{}" ,orderNo,ExceptionUtils.extractStackTrace(e));
//                }
//            }
//        }
//        return CommonConstant.MOD;
//    }
//
//    /**
//     * @Description 对数据进行求模
//     * @Param
//     * @Date 2016年10月13日
//     * @Return
//     */
//    public static String getMod(String orderNo,Integer length){
//        BigInteger bigNum = new BigInteger(String.valueOf(hash(orderNo.trim().hashCode()))).abs();
//        return String.valueOf(bigNum.mod(BigInteger.valueOf(length)).intValue());
//    }
//
//    /**
//     * @Description wang jeekins哈希算法，具有雪崩性，输入一位的变化造成哈希值一半位数的变化，增加离散度
//     * @Param        key 需要进行计算的值
//     * @Date 2016年6月15日
//     * @Author 80002031
//     * @Return
//     */
//    public static int hash(int key) {
//        key = (~key) + (key << 21);
//        key = key ^ (key >> 24);
//        key = (key + (key << 3)) + (key << 8);
//        key = key ^ (key >> 14);
//        key = (key + (key << 2)) + (key << 4);
//        key = key ^ (key >> 28);
//        key = key + (key << 31);
//        return key;
//    }
//
//    public static Integer getSchemaDiffTime(){
//        //从缓存中取出分库扫描时间
//        SysConfig config = SysConfigCache
//                .getOrderSystemBySystemCode(CommonConstant.SCHEMA_DIFF_TIME);
//        if (null != config){
//            return Integer.parseInt(config.getConfigValue());
//        }else{
//            return CommonConstant.DIFFMINUTE;
//        }
//    }
//
//    /**
//     *  截取订单号的0到2位 作为分库模值
//     * @param orderNo
//     * @return
//     * @author 80002031-2017年8月2日
//     */
//    public static String getSchemaIndex(String orderNo){
//        if (Integer.parseInt(orderNo.substring(0,3)) > 64 ) {
//            return "000";
//        }else{
//            return orderNo.substring(0,3);
//        }
//    }
//
//    //对分库序号进行补0处理
//    public static String formatSchemaIndex(String index){
//        if (index.length() == 1){
//            return "00" + index;
//        }else if (index.length() == 2){
//            return "0" + index;
//        }else{
//            return index;
//        }
//    }
//
//    /**
//     * @Description 根据城市代码获取订单分库号
//     * @Param
//     * @Date 2016年9月26日
//     * @Return
//     */
//    public static String orderNoModByCityCode(String cityCode,boolean isCreateVirtual,String sysCode){
//        if (StringUtils.isNotEmpty(cityCode)){ //从缓存中获取是否存在已配置的城市代码中
//            SysConfig config = SysConfigCache.getOrderSystemBySystemCode(
//                    CommonConstant.SCHEMA_DEFAULT_PREFIX_CODE + cityCode);
//            if (null != config){//已存在
//                return getSchemaIndex(config,isCreateVirtual,sysCode);
//            }else{//不存在
//                String configValue = getConfigValue(config);
//                config = SysConfigCache.getOrderSystemBySystemCode(CommonConstant.SCHEMA_COUNT);
//                int usedSchemaCount =Integer.parseInt(config.getConfigValue())- Integer.parseInt(configValue);
//                return getSchemaIndex(config, configValue, usedSchemaCount, cityCode);
//            }
//        }
//        return CommonConstant.MOD;
//    }
//
//    /**
//     * @Description 存在于SysConfig对象时，直接随机从对应的分库中取值
//     * @Param
//     * @Date 2016年9月26日
//     * @Return
//     */
//    public static String getSchemaIndex(SysConfig config,boolean isCreateVirtual,String sysCode){
//        String[] str = config.getConfigValue().split(",");
//        if (isCreateVirtual){ //虚拟订单号对运单号进行hash取模分库
//            return formatSchemaIndex(str[Integer.parseInt(getMod(sysCode,str.length))]);
//        }else{
//            int length = str.length;
//            return formatSchemaIndex(str[new Random().nextInt(length)]);
//        }
//    }
//
//    /**
//     * @Description 获取取出需要进行hash的分库总量
//     * @Param
//     * @Date 2016年9月26日
//     * @Return
//     */
//    private static String getConfigValue(SysConfig config){
//        config = SysConfigCache.getOrderSystemBySystemCode(CommonConstant.SCHEMA_HASH_COUNT);
//        String configValue =  CommonConstant.DEFAULT_SCHEMA_COUNT+"";
//        if (null != config){
//            configValue = config.getConfigValue();
//        }
//        return configValue;
//    }
//
//    /**
//     * @Description 不存在SysConfig对象时，通过城市代码hash去进行计算分库值
//     * @Param
//     * @Date 2016年9月26日
//     * @Return
//     */
//    public static String getSchemaIndex(SysConfig config , String configValue ,int usedSchemaCount,String cityCode){
//        //判断值的存在
//        if (null != config && StringUtils.isNotEmpty(configValue)){
//            try{
//                BigInteger bigNum = new BigInteger(String.valueOf(hash(cityCode.trim().hashCode()))).abs();
//                int intValue = (bigNum.mod(BigInteger.valueOf(Long.parseLong(configValue)))).intValue();
//                return formatSchemaIndex(String.valueOf(intValue+usedSchemaCount));
//            }catch(Exception e){
//                logger.error("OrderNoModUtils getSchemaIndex executeException --{}" ,ExceptionUtils.extractStackTrace(e));
//            }
//        }
//        return CommonConstant.MOD;
//    }
//}

