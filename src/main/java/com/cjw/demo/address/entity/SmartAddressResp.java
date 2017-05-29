package com.cjw.demo.address.entity;

import java.io.Serializable;

/**
 * Created by 80002331 on 2017/3/21.
 */
public class SmartAddressResp implements Serializable{

    private static final long serialVersionUID = 1L;

    private String site;
   // private String  SendRegional;
    private String personalName;
    private String telephone;
    private String area;
    private String  province;
    private String city;

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getPersonalName() {
        return personalName;
    }

    public void setPersonalName(String personalName) {
        this.personalName = personalName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
