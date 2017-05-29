package com.cjw.demo.mycat;

import java.math.BigInteger;

/**
 * Created by 828471 on 2017/4/17.
 */
public class PartitionByHashMod {
    private static boolean watch = false;
    private static int count;

    public static void setCount(int count1) {
        count = count1;
        if ((count1 & (count1 - 1)) == 0) {
            watch = true;
        }
    }

    /**
     * Using Wang/Jenkins Hash
     *
     * @param key
     * @return hash value
     */
    protected static int hash(int key) {
        key = (~key) + (key << 21); // key = (key << 21) - key - 1;
        key = key ^ (key >> 24);
        key = (key + (key << 3)) + (key << 8); // key * 265
        key = key ^ (key >> 14);
        key = (key + (key << 2)) + (key << 4); // key * 21
        key = key ^ (key >> 28);
        key = key + (key << 31);
        return key;
    }

    //    @Override
    public static   Integer calculate(String columnValue) {
        columnValue = columnValue.replace("\'", " ");
        columnValue = columnValue.trim();
        BigInteger bigNum = new BigInteger(hash(columnValue.hashCode()) + "").abs();
        // if count==2^n, then m%count == m&(count-1)
        if (watch) {
            return bigNum.intValue() & (count - 1);
        }
        return (bigNum.mod(BigInteger.valueOf(count))).intValue();
    }

    //    @Override
//    public void init() {
//        super.init();
//    }
    public static void main(String[] args) {
        setCount(16);
        Integer value = calculate("5FDC000C795F45B9B2A7133BEBAA44E9");
        System.out.println(value);
    }
}
