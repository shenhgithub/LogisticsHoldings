package com.port.logistics.holdings.util;
/**
 * Created by 超悟空 on 2015/6/18.
 */

import org.json.JSONArray;
import org.json.JSONException;

/**
 * 类型转换
 *
 * @author 超悟空
 * @version 1.0 2015/6/18
 * @since 1.0
 */
public class TypeConvert {

    /**
     * 将JSONArray转换为String数组
     *
     * @param jsonArray json数组
     *
     * @return 字符串数组
     *
     * @throws JSONException 取值失败
     */
    public static String[] JsonArrayToString(JSONArray jsonArray) throws JSONException {
        String[] array = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            array[i] = jsonArray.getString(i);
        }

        return array;
    }

    /**
     * String数组转为double数组
     *
     * @param strings 要转换的数组
     *
     * @return 结果数组
     *
     * @throws NumberFormatException 字符串存在不能转换为浮点型的值
     */
    public static double[] StringToDouble(String[] strings) throws NumberFormatException {
        double[] doubles = new double[strings.length];

        for (int i = 0; i < strings.length; i++) {
            doubles[i] = Double.valueOf(strings[i]);
        }
        return doubles;
    }
}
