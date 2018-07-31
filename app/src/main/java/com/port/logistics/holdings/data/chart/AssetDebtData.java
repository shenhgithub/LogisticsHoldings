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
 * 资产负债数据
 *
 * @author 超悟空
 * @version 1.0 2015/6/18
 * @since 1.0
 */
public class AssetDebtData implements IChartData {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "AssetDebtData.";

    /**
     * 显示月（X轴刻度）
     */
    private String[] months = null;

    /**
     * 资产（Y轴坐标）
     */
    private double[] asset = null;

    /**
     * 负债（Y坐标）
     */
    private double[] debt = null;

    /**
     * 去年资产
     */
    private double[] lastAsset = null;

    /**
     * 去年负债
     */
    private double[] lastDebt = null;

    /**
     * 获取月（X轴刻度标签）
     *
     * @return 月数组
     */
    public String[] getMonths() {
        return months;
    }

    /**
     * 获取资产（Y坐标）
     *
     * @return 资产数组
     */
    public double[] getAsset() {
        return asset;
    }

    /**
     * 获取负债（Y坐标）
     *
     * @return 负债数组
     */
    public double[] getDebt() {
        return debt;
    }

    /**
     * 获取去年资产
     *
     * @return 资产数组
     */
    public double[] getLastAsset() {
        return lastAsset;
    }

    /**
     * 获取去年负债
     *
     * @return 负债数组
     */
    public double[] getLastDebt() {
        return lastDebt;
    }

    @Override
    public boolean parse(JSONObject jsonObject) throws JSONException {

        // 日期数组
        months = TypeConvert.JsonArrayToString(jsonObject.getJSONArray("Month"));

        // 资产数组
        String[] assetString = TypeConvert.JsonArrayToString(jsonObject.getJSONArray("Asset"));

        // 负债数组
        String[] debtString = TypeConvert.JsonArrayToString(jsonObject.getJSONArray("Debt"));

        // 去年资产数组
        String[] lastAssetString = TypeConvert.JsonArrayToString(jsonObject.getJSONArray("LastAsset"));

        // 去年负债数组
        String[] lastDebtString = TypeConvert.JsonArrayToString(jsonObject.getJSONArray("LastDebt"));

        try {
            // 转为浮点型
            asset = TypeConvert.StringToDouble(assetString);
            debt = TypeConvert.StringToDouble(debtString);
            lastDebt = TypeConvert.StringToDouble(lastDebtString);
            lastAsset = TypeConvert.StringToDouble(lastAssetString);
            return true;
        } catch (NumberFormatException e) {
            Log.e(LOG_TAG + "parse", e.getMessage());
            return false;
        }
    }

    @Override
    public int serviceNumber() {
        return 3;
    }
}