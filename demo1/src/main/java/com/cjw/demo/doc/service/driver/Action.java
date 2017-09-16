package com.cjw.demo.doc.service.driver;

/**
 * Created by 828471 on 2016/11/21.
 */
public abstract class Action {
    /**
     * 方向顺时针环绕
     */
    public static final String direction = "ESWN";

    public Point fowardMoveAction(Point endPoint) {
        char dir = endPoint.getDirection().toCharArray()[0];
        switch (dir) {
            case 'W':
                endPoint.setLongtitude(endPoint.getLongtitude() - 1);
                break;
            case 'N':
                endPoint.setLatitude(endPoint.getLatitude() + 1);
                break;
            case 'E':
                endPoint.setLongtitude(endPoint.getLongtitude() + 1);
                break;
            case 'S':
                endPoint.setLatitude(endPoint.getLatitude() - 1);
                break;
        }
        return endPoint;
    }

    public Point rightMoveAction(Point endPoint) {
        String state = endPoint.getDirection();
        int len = direction.length();
        int idx = direction.indexOf(state);
        char endDirection = ((idx < len - 1) ? direction.charAt(idx + 1) : direction.charAt(0));
        endPoint.setDirection(String.valueOf(endDirection));
        return endPoint;
    }

    public Point leftMoveAction(Point endPoint) {
        String state = endPoint.getDirection();
        int len = direction.length();
        int idx = direction.indexOf(state);
        char endDirection = ((idx > 0) ? direction.charAt(idx - 1) : direction.charAt(len - 1));
        endPoint.setDirection(String.valueOf(endDirection));
        return endPoint;
    }
}
