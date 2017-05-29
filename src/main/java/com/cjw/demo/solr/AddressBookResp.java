package com.cjw.demo.solr;




import org.apache.solr.client.solrj.beans.Field;

import java.io.Serializable;

/**
 * Created by 828471 on 2016/8/31.
 */
public class AddressBookResp implements Serializable{
    @Field("MEMBERID")
    private String memId;   //会员号
    @Field("MEMBERTYPE")
    private String memType;  //用户类型，寄件人为0，收件人为1
    @Field("ADDRESSID")
    private String addressId;//地址编码
    @Field("CONTACTS")
    private String contactName;//联系人姓名
    @Field("PHONE")
    private String contactPhone;//联系人座机
    @Field("TELPHONE")
    private String contactTel;//联系人电话
    @Field("CONTACTSSPELL")
    private String contactSpell;//联系人拼音
    @Field("COMPANYNAME")
    private String companyName;//公司名称
    @Field("COUNTRY_CODE")
    private String countryCode;//国家
    @Field("COUNTRY")
    private String country;//国家
    @Field("PROVINCE_CODE")
    private String provinceCode;//省
    @Field("PROVINCE_NAME")
    private String province;//省
    @Field("CITY_CODE")
    private String cityCode;//城市
    @Field("CITY_NAME")
    private String city;//城市
    @Field("LOCATION_CODE")
    private String locationCode;//区域编码
    @Field("COUNTY_CODE")
    private String countyCode;//区编码
    @Field("COUNTY_NAME")
    private String county;//区名称
    @Field("ADDRESS")
    private String address;//地址
    @Field("POSTCODE")
    private String postCode;//邮政编码
    @Field("LONGITUDE")
    private String longitude;//经度
    @Field("LATITUDE")
    private String latitude;//纬度
    @Field("ISDEFAULT")
    private String isDefault;//是否设置为默认地址



    public String getMemId() {
        return memId;
    }

    public void setMemId(String memId) {
        this.memId = memId;
    }

    public String getMemType() {
        return memType;
    }

    public void setMemType(String memType) {
        this.memType = memType;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public String getContactSpell() {
        return contactSpell;
    }

    public void setContactSpell(String contactSpell) {
        this.contactSpell = contactSpell;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
}

