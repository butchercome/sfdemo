package com.cjw.demo.service.driver;

/**
 * Created by 828471 on 2016/11/21.
 */
public class DriverAction extends Action{

    private String instruct;//指令
    private Point point;

    public DriverAction(String instruct, Point point) {
        this.instruct = instruct;
        this.point = point;
    }

    /**
     * 动作拆解
     * @return
     */
    public Point pointMove() {
        char[] strArray = this.instruct.toCharArray();
        Point endPoint = this.point;
        for (int a = 0; a < strArray.length; a++) {
            endPoint = moveAction(strArray[a], endPoint);
        }
        return endPoint;
    }

    /**
     * 指令转换
     * @param c
     * @param endPoint
     * @return
     */
    private  Point moveAction(char c, Point endPoint) {
        switch (c) {
            case 'L':
                endPoint = leftMoveAction(endPoint);
                break;
            case 'R':
                endPoint = rightMoveAction(endPoint);
                break;
            case 'M':
                endPoint = fowardMoveAction(endPoint);
                break;
        }
        return endPoint;
    }
}
