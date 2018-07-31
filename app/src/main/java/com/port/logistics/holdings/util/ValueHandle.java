package com.port.logistics.holdings.util;
/**
 * Created by 超悟空 on 2015/6/18.
 */

import android.util.Log;

/**
 * 处理数据的工具
 *
 * @author 超悟空
 * @version 1.0 2015/6/18
 * @since 1.0
 */
public class ValueHandle {

    /**
     * 日志前缀
     */
    private static final String LOG_TAG = "ValueHandle.";

    /**
     * 得到数组中的最大值
     *
     * @param values 浮点型数组
     *
     * @return 最大值
     */
    public static double getMax(double[] values) {

        Log.i(LOG_TAG + "getMax", "values count is " + values.length);

        double max = values[0];
        for (double value : values) {
            max = value > max ? value : max;
        }
        Log.i(LOG_TAG + "getMax", "max is " + max);
        return max;
    }

    /**
     * 得到数组中的最小值
     *
     * @param values 浮点型数组
     *
     * @return 最小值
     */
    public static double getMin(double[] values) {
        Log.i(LOG_TAG + "getMin", "values count is " + values.length);

        double min = values[0];
        for (double value : values) {
            min = value < min ? value : min;
        }
        Log.i(LOG_TAG + "getMin", "min is " + min);
        return min;
    }

    /**
     * 计算值的上限阈值
     *
     * @param value    给定值
     * @param accuracy 精度
     *
     * @return 上限阈值
     */
    public static int getUpThreshold(double value, int accuracy) {
        Log.i(LOG_TAG + "getUpThreshold", "value is " + value);
        Log.i(LOG_TAG + "getUpThreshold", "accuracy is " + accuracy);

        int valueCeil = (int) Math.ceil(value);
        Log.i(LOG_TAG + "getUpThreshold", "valueCeil is " + valueCeil);

        int quotient = valueCeil / accuracy;
        Log.i(LOG_TAG + "getUpThreshold", "quotient is " + quotient);
        if (accuracy * quotient > value) {
            return accuracy * quotient;
        } else {
            return accuracy * (quotient + 1);
        }
    }

    /**
     * 计算值的上限阈值
     *
     * @param value    给定值
     * @param accuracy 精度
     * @param min      最小上限值
     *
     * @return 上限阈值
     */
    public static int getUpThreshold(double value, int accuracy, int min) {
        Log.i(LOG_TAG + "getUpThreshold", "min is " + min);
        if (value < min) {
            Log.i(LOG_TAG + "getUpThreshold", "value is " + value);
            return min;
        }
        return getUpThreshold(value, accuracy);
    }

    /**
     * 计算值的下限阈值
     *
     * @param value    给定值
     * @param accuracy 精度
     *
     * @return 下限阈值
     */
    public static int getDownThreshold(double value, int accuracy) {
        Log.i(LOG_TAG + "getDownThreshold", "value is " + value);
        Log.i(LOG_TAG + "getDownThreshold", "accuracy is " + accuracy);

        int valueCeil = (int) Math.floor(value);
        Log.i(LOG_TAG + "getDownThreshold", "valueCeil is " + valueCeil);

        int quotient = valueCeil / accuracy;
        Log.i(LOG_TAG + "getDownThreshold", "quotient is " + quotient);

        if (accuracy * quotient < value) {
            return accuracy * quotient;
        } else {
            return accuracy * (quotient - 1);
        }
    }

    /**
     * 计算值的下限阈值
     *
     * @param value    给定值
     * @param accuracy 精度
     * @param max      最大值
     *
     * @return 下限阈值
     */
    public static int getDownThreshold(double value, int accuracy, int max) {
        Log.i(LOG_TAG + "getDownThreshold", "max is " + max);
        if (value > max) {
            Log.i(LOG_TAG + "getDownThreshold", "value is " + value);
            return max;
        }

        return getDownThreshold(value, accuracy);
    }
}
