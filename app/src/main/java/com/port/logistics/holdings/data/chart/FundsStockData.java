package com.port.logistics.holdings.data.chart;
/**
 * Created by 超悟空 on 2015/6/17.
 */

import android.util.Log;

import com.port.logistics.holdings.data.base.IChartData;
import com.port.logistics.holdings.util.TypeConvert;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 资金存量数据
 *
 * @author 超悟空
 * @version 1.0 2015/6/17
 * @since 1.0
 */
public class FundsStockData implements IChartData {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "FundsStockData.";

    /**
     * 显示日期（X轴刻度）
     */
    private String[] dates = null;

    /**
     * 资金量（Y轴坐标）
     */
    private double[] funds = null;

    /**
     * 资金百分比
     */
    private String[] fundPercent = null;

    /**
     * 获取日期（X轴刻度）
     *
     * @return 日期数组
     */
    public String[] getDates() {
        return dates;
    }

    /**
     * 获取资金存量（Y轴坐标）
     *
     * @return 资金数组
     */
    public double[] getFunds() {
        return funds;
    }

    /**
     * 获取资金百分比
     *
     * @return 百分比数组
     */
    public String[] getFundPercent() {
        return fundPercent;
    }

    @Override
    public boolean parse(JSONObject jsonObject) throws JSONException {

        // 日期数组
        dates = TypeConvert.JsonArrayToString(jsonObject.getJSONArray("Date"));

        // 资金数组
        String[] fundString = TypeConvert.JsonArrayToString(jsonObject.getJSONArray("Fund"));

        try {
            // 转为浮点型
            funds = TypeConvert.StringToDouble(fundString);
        } catch (NumberFormatException e) {
            Log.e(LOG_TAG + "parse", e.getMessage());
            return false;
        }

        // 资金百分比
        fundPercent = TypeConvert.JsonArrayToString(jsonObject.getJSONArray("FundPercent"));

        return true;
    }

    @Override
    public int serviceNumber() {
        return 0;
    }
}
