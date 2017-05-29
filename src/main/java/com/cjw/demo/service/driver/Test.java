package com.cjw.demo.service.driver;

import java.util.Scanner;

/**
 * Created by 828471 on 2016/11/21.
 */
public class Test {
    public static void main(String[] args) {
        //创建输入对象
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入所在点位置:");
        String str = sc.nextLine();
        char[] strbyte = str.toCharArray();
        Point point = new Point(Integer.valueOf(strbyte[0] + ""),Integer.valueOf(strbyte[1] + ""),String.valueOf(strbyte[2]));
        System.out.print("请输入移动的方向:");
        String urge = sc.nextLine();
        DriverAction action = new DriverAction(urge, point);
        Point resultPoint = action.pointMove();
        System.out.println("你的终点为:" + resultPoint.getLongtitude() + resultPoint.getLatitude() + resultPoint.getDirection());
    }
    public  void tests(){
        String  asasa="sasa";
        testxxxvalue(asasa);
    }

    private void testxxxvalue(String asasa) {
        String bbbb=asasa;
        System.out.println(bbbb);
    }
}
