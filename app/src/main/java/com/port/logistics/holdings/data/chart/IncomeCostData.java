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
 * 收入成本数据
 *
 * @author 超悟空
 * @version 1.0 2015/6/18
 * @since 1.0
 */
public class IncomeCostData implements IChartData {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "IncomeCostData.";

    /**
     * 显示月（X轴刻度）
     */
    private String[] months = null;

    /**
     * 收入（Y轴坐标）
     */
    private double[] income = null;

    /**
     * 成本（Y坐标）
     */
    private double[] cost = null;

    /**
     * 去年收入
     */
    private double[] lastIncome = null;

    /**
     * 去年成本
     */
    private double[] lastCost = null;

    /**
     * 获取月（X轴刻度标签）
     *
     * @return 月数组
     */
    public String[] getMonths() {
        return months;
    }

    /**
     * 获取收入（Y坐标）
     *
     * @return 收入数组
     */
    public double[] getIncome() {
        return income;
    }

    /**
     * 获取成本（Y坐标）
     *
     * @return 成本数组
     */
    public double[] getCost() {
        return cost;
    }

    /**
     * 获取去年收入
     *
     * @return 收入数组
     */
    public double[] getLastIncome() {
        return lastIncome;
    }

    /**
     * 获取去年成本
     *
     * @return 成本数组
     */
    public double[] getLastCost() {
        return lastCost;
    }

    @Override
    public boolean parse(JSONObject jsonObject) throws JSONException {

        // 日期数组
        months = TypeConvert.JsonArrayToString(jsonObject.getJSONArray("Month"));

        // 收入数组
        String[] incomeString = TypeConvert.JsonArrayToString(jsonObject.getJSONArray("Income"));

        // 成本数组
        String[] costString = TypeConvert.JsonArrayToString(jsonObject.getJSONArray("Cost"));

        // 去年收入数组
        String[] lastIncomeString = TypeConvert.JsonArrayToString(jsonObject.getJSONArray("LastIncome"));

        // 去年成本数组
        String[] lastCostString = TypeConvert.JsonArrayToString(jsonObject.getJSONArray("LastCost"));

        try {
            // 转为浮点型
            income = TypeConvert.StringToDouble(incomeString);
            cost = TypeConvert.StringToDouble(costString);
            lastCost = TypeConvert.StringToDouble(lastCostString);
            lastIncome = TypeConvert.StringToDouble(lastIncomeString);
            return true;
        } catch (NumberFormatException e) {
            Log.e(LOG_TAG + "parse", e.getMessage());
            return false;
        }
    }

    @Override
    public int serviceNumber() {
        return 2;
    }
}