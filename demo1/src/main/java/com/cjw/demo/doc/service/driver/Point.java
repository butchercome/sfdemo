package com.cjw.demo.doc.service.driver;

/**
 * Created by 828471 on 2016/11/21.
 */
public class Point {
    private Integer longtitude;//经度
    private Integer latitude;//纬度
    private String direction;

    public Point(Integer longtitude, Integer latitude, String direction) {
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.direction = direction;
    }

    public Point() {
    }

    public Integer getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(Integer longtitude) {
        this.longtitude = longtitude;
    }

    public Integer getLatitude() {

        return latitude;
    }

    public void setLatitude(Integer latitude) {
        this.latitude = latitude;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
