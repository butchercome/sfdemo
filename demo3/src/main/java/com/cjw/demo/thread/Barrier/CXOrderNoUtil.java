package com.cjw.demo.thread.Barrier;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by 828471 on 2017/9/14.
 */
public class CXOrderNoUtil {
    public static final String pattern3 = "yyyyMMddHHmmssSSS";
    public static final String pattern4 = "MddHHmmssSSS";
    private static  LinkedList<String> linkedList =new LinkedList<>();
    private static ConcurrentLinkedQueue<String> queue=new ConcurrentLinkedQueue<>();


    public static String getOrderNo() {
        String random4 = String.format("%04d", (int) (Math.random() * 9999 + 1));
        String orderNo = "CX" + DateFormatUtils.format(new Date(), pattern4) + random4;
//        linkedList.add(orderNo);
        queue.add(orderNo);
        return orderNo;
    }

    public static void checkIsExist() {
        try {
            System.out.println("start check is Exist Before");
            int before = queue.size();
            linkedList=new LinkedList<>(queue);
            System.out.println("the size of list    "+before);
            Set<String> set = new HashSet<>();
            set.addAll(linkedList);
            int end = set.size();
            System.out.println("the value  before and end " + before + ".............." + end);
            if (before != end) {
                do {
                    String value = linkedList.get(0);
                    linkedList.remove(0);
                    if (null!=linkedList&&linkedList.size()>0){
                        for (int i = 0; i < linkedList.size(); i++) {
                            if (value.equals(linkedList.get(i))) {
                                System.out.println(linkedList.get(i));
                            }
                        }
                    }

                } while (linkedList.size() > 0);
            }
            queue=new ConcurrentLinkedQueue<>();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

//    public static void main(String[] args) {
//        getOrderNo();
//
//    }


}
