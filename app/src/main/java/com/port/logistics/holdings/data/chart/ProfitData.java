package com.port.logistics.holdings.data.chart;
/**
 * Created by 超悟空 on 2015/6/18.
 */

import android.util.Log;

import com.port.logistics.holdings.data.base.IChartData;
import com.port.logistics.holdings.util.TypeConvert;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 利润数据
 *
 * @author 超悟空
 * @version 1.0 2015/6/18
 * @since 1.0
 */
public class ProfitData implements IChartData {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "ProfitData.";

    /**
     * 显示月（X轴刻度）
     */
    private String[] months = null;

    /**
     * 利润（Y轴坐标）
     */
    private double[] profit = null;

    /**
     * 计划
     */
    private double[] plan = null;

    /**
     * 去年利润
     */
    private double[] lastProfit = null;

    /**
     * 兑现率
     */
    private String[] cashRate = null;

    /**
     * 获取月（X轴刻度标签）
     *
     * @return 月数组
     */
    public String[] getMonths() {
        return months;
    }

    /**
     * 获取利润（Y坐标）
     *
     * @return 利润数组
     */
    public double[] getProfit() {
        return profit;
    }

    /**
     * 获取计划（Y坐标）
     *
     * @return 计划数组
     */
    public double[] getPlan() {
        return plan;
    }

    /**
     * 获取去年利润
     *
     * @return 去年利润数组
     */
    public double[] getLastProfit() {
        return lastProfit;
    }

    /**
     * 获取兑现率
     *
     * @return 兑现率数组
     */
    public String[] getCashRate() {
        return cashRate;
    }

    @Override
    public boolean parse(JSONObject jsonObject) throws JSONException {

        // 日期数组
        months = TypeConvert.JsonArrayToString(jsonObject.getJSONArray("Month"));

        // 利润数组
        String[] profitString = TypeConvert.JsonArrayToString(jsonObject.getJSONArray("Profit"));

        // 计划数组
        String[] planString = TypeConvert.JsonArrayToString(jsonObject.getJSONArray("Plan"));

        // 去年利润数组
        String[] lastProfitString = TypeConvert.JsonArrayToString(jsonObject.getJSONArray("LastProfi"));

        // 兑现率数组
        cashRate = TypeConvert.JsonArrayToString(jsonObject.getJSONArray("CashRate"));

        try {
            // 转为浮点型
            profit = TypeConvert.StringToDouble(profitString);
            plan = TypeConvert.StringToDouble(planString);
            lastProfit = TypeConvert.StringToDouble(lastProfitString);

            return true;
        } catch (NumberFormatException e) {
            Log.e(LOG_TAG + "parse", e.getMessage());
            return false;
        }
    }

    @Override
    public int serviceNumber() {
        return 4;
    }
}