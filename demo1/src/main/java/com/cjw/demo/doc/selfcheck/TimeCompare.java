package com.cjw.demo.doc.selfcheck;

import com.cjw.demo.doc.util.DateUtils;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by 828471 on 2017/7/12.
 */
public class TimeCompare {

    public static boolean compareTimeLessThanSix() {
        try {
            Date endTime = DateUtils.parse(DateUtils.toDateTimeStr(new Date(), "yyyy-MM-dd") + " " + "06:00:00");
            return (DateUtils.compareDate(new Date(), endTime) < 0) ? true : false;
        } catch (ParseException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(compareTimeLessThanSix());
    }
}
