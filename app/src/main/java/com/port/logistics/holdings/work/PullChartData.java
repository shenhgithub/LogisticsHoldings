package com.port.logistics.holdings.work;
/**
 * Created by 超悟空 on 2015/6/17.
 */

import android.util.Log;

import com.port.logistics.holdings.data.base.IChartData;
import com.port.logistics.holdings.data.chart.ChartData;
import com.port.logistics.holdings.util.StaticValue;

import org.mobile.model.work.WorkModel;
import org.mobile.network.communication.ICommunication;
import org.mobile.network.factory.CommunicationFactory;
import org.mobile.network.factory.NetworkType;

/**
 * 从服务器获取图表数据任务
 *
 * @author 超悟空
 * @version 1.0 2015/6/17
 * @since 1.0
 */
public class PullChartData extends WorkModel<IChartData, IChartData> {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "PullChartData.";

    @Override
    protected boolean onDoWork(IChartData... parameters) {
        if (parameters == null || parameters.length == 0) {
            // 数据异常
            Log.d(LOG_TAG + "onDoWork", "IChartData is null");
            return false;
        }

        // 新建http get请求的通讯工具
        ICommunication communication = CommunicationFactory.Create(NetworkType.HTTP_GET);

        // 新建图表数据对象
        ChartData data = new ChartData();
        // 设置参数
        data.setChartData(parameters[0]);

        // 设置调用的方法名
        communication.setTaskName(StaticValue.HTTP_GET_ROOT_URL + "ServiceWLKG.aspx");
        Log.i(LOG_TAG + "onDoWork", "update request url is " + StaticValue.HTTP_GET_ROOT_URL + "ServiceWLKG.aspx");

        // 发送请求
        //noinspection unchecked
        communication.Request(data.serialization());

        // 解析数据
        if (data.parse(communication.Response())) {
            // 执行成功
            Log.i(LOG_TAG + "onDoWork", "check success");
            // 设置结果
            setResult(parameters[0]);
            return true;
        } else {
            // 执行失败
            Log.i(LOG_TAG + "onDoWork", "check failed");
            return false;
        }
    }
}
