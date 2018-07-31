package com.port.logistics.holdings.data.base;
/**
 * Created by 超悟空 on 2015/6/17.
 */

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 图表数据模型接口
 *
 * @author 超悟空
 * @version 1.0 2015/6/17
 * @since 1.0
 */
public interface IChartData {

    /**
     * 服务器数据解析及图表相关数据填充
     *
     * @param jsonObject 服务器结果数据
     *
     * @return 解析结果
     *
     * @throws JSONException 异常
     */
    boolean parse(JSONObject jsonObject) throws JSONException;

    /**
     * 提供本数据结构对应的服务号
     *
     * @return 服务序号
     */
    int serviceNumber();
}
