package com.port.logistics.holdings.data.chart;
/**
 * Created by 超悟空 on 2015/6/17.
 */

import android.util.Log;

import com.port.logistics.holdings.data.base.IChartData;

import org.json.JSONException;
import org.json.JSONObject;
import org.mobile.model.data.IDataModel;
import org.mobile.parser.HttpResponseHttpEntityToStringParser;

import java.util.HashMap;
import java.util.Map;

/**
 * 服务器交互用图表数据类
 *
 * @author 超悟空
 * @version 1.0 2015/6/17
 * @since 1.0
 */
public class ChartData implements IDataModel<Object, Map<String, String>> {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "ChartData.";

    /**
     * 要填充的图表对象
     */
    private IChartData chartData = null;

    /**
     * 设置图表数据对象
     *
     * @param chartData 指定的图表数据实现
     */
    public void setChartData(IChartData chartData) {
        this.chartData = chartData;
    }

    @Override
    public Map<String, String> serialization() {
        Log.i(LOG_TAG + "serialization", "serialization start");

        Log.i(LOG_TAG + "serialization", "chartData is " + chartData.toString());
        // 序列化后的参数集
        Map<String, String> dataMap = new HashMap<>();

        dataMap.put("ServiceNum", String.valueOf(chartData.serviceNumber()));
        Log.i(LOG_TAG + "serialization", "ServiceNum is " + chartData.serviceNumber());

        return dataMap;
    }

    @Override
    public boolean parse(Object data) {
        Log.i(LOG_TAG + "parse", "parse start");

        if (data == null) {
            // 通信异常
            Log.d(LOG_TAG + "parse", "data is null");
            return false;
        }
        Log.i(LOG_TAG + "parse", "data is " + data);


        if (chartData == null) {
            // 图表数据对象为空
            Log.d(LOG_TAG + "parse", "chartData is null");
            return false;
        }

        // 新建解析器
        HttpResponseHttpEntityToStringParser parser = new HttpResponseHttpEntityToStringParser();

        // 获取结果字符串
        String resultString = parser.DataParser(data);
        Log.i(LOG_TAG + "parse", "result string is " + resultString);

        try {
            // 解析提取数据
            return chartData.parse(new JSONObject(resultString));
        } catch (JSONException e) {
            Log.e(LOG_TAG + "parse", "JSONException is " + e.getMessage());
            return false;
        }
    }
}
