package com.port.logistics.holdings.data.chart;
/**
 * Created by 超悟空 on 2015/6/19.
 */

import android.util.Log;

import com.port.logistics.holdings.data.base.IChartData;
import com.port.logistics.holdings.util.TypeConvert;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 散杂货代理量数据
 *
 * @author 超悟空
 * @version 1.0 2015/6/19
 * @since 1.0
 */
public class ScatteredGroceriesAmountData implements IChartData {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "ScatteredGroceriesAmountData.";

    /**
     * 显示日期（X轴刻度）
     */
    private String[] dates = null;

    /**
     * 散杂货量（Y轴坐标）
     */
    private double[] amount = null;

    /**
     * 去年量
     */
    private double[] lastAmount = null;

    /**
     * 增量百分比
     */
    private String[] amountPercent = null;

    /**
     * 获取日期
     *
     * @return 日期数组
     */
    public String[] getDates() {
        return dates;
    }

    /**
     * 获取散杂货代理量
     *
     * @return 代理量数组
     */
    public double[] getAmount() {
        return amount;
    }

    /**
     * 获取去年散杂货代理量
     *
     * @return 代理量数组
     */
    public double[] getLastAmount() {
        return lastAmount;
    }

    /**
     * 获取散杂货增量百分比
     *
     * @return 百分比数组
     */
    public String[] getAmountPercent() {
        return amountPercent;
    }

    @Override
    public boolean parse(JSONObject jsonObject) throws JSONException {

        // 日期数组
        dates = TypeConvert.JsonArrayToString(jsonObject.getJSONArray("Date"));

        // 代理量数组
        String[] amountString = TypeConvert.JsonArrayToString(jsonObject.getJSONArray("Amount"));

        // 去年代理数组
        String[] lastAmountString = TypeConvert.JsonArrayToString(jsonObject.getJSONArray("LastAmount"));

        // 增量百分比数组
        amountPercent = TypeConvert.JsonArrayToString(jsonObject.getJSONArray("AmountPercent"));

        try {
            // 转为浮点型
            amount = TypeConvert.StringToDouble(amountString);
            lastAmount = TypeConvert.StringToDouble(lastAmountString);
            return true;
        } catch (NumberFormatException e) {
            Log.e(LOG_TAG + "parse", e.getMessage());
            return false;
        }
    }

    @Override
    public int serviceNumber() {
        return 6;
    }
}
