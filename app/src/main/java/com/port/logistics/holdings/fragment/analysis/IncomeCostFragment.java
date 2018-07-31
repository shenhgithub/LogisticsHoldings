package com.port.logistics.holdings.fragment.analysis;
/**
 * Created by 超悟空 on 2015/6/18.
 */

import com.port.logistics.holdings.chart.BaseSupportUtils;
import com.port.logistics.holdings.chart.ColorLevelDoubleLineUtils;
import com.port.logistics.holdings.data.base.IChartData;
import com.port.logistics.holdings.data.chart.IncomeCostData;
import com.port.logistics.holdings.fragment.base.BaseChartFragment;
import com.port.logistics.holdings.util.ValueHandle;
import com.port.logistics.holdings.work.PullChartData;

import org.achartengine.model.XYSeries;
import org.mobile.model.work.WorkBack;

import java.util.ArrayList;
import java.util.List;

/**
 * 收入成本图表
 *
 * @author 超悟空
 * @version 1.0 2015/6/18
 * @since 1.0
 */
public class IncomeCostFragment extends BaseChartFragment {

    @Override
    protected BaseSupportUtils onCreateChartUtil() {
        return new ColorLevelDoubleLineUtils(getActivity());
    }

    @Override
    protected void onBuildChart(final BaseSupportUtils chartUtil) {
        // 新建收入成本数据类型
        IncomeCostData incomeCostData = new IncomeCostData();

        // 新建数据请求任务
        PullChartData pullChartData = new PullChartData();

        // 设置回调
        pullChartData.setWorkBackListener(new WorkBack<IChartData>() {
            @Override
            public void doEndWork(boolean state, IChartData data) {
                if (state) {
                    fillChartDataSet(chartUtil, (IncomeCostData) data);
                    drawChartView();
                }
            }
        });

        pullChartData.beginExecute(incomeCostData);
    }

    /**
     * 填充图表数据
     *
     * @param chartUtil      图表工具
     * @param incomeCostData 图表数据集合
     */
    private void fillChartDataSet(BaseSupportUtils chartUtil, IncomeCostData incomeCostData) {

        // X坐标值
        double[] XValues = new double[incomeCostData.getMonths().length];

        // 将X轴的值转为从1开始的整数
        for (int i = 0; i < XValues.length; i++) {
            XValues[i] = i + 1;
        }

        // Y坐标标签对应值
        //double[] YValues = new double[]{0 , 50 , 100 , 150};

        // X轴显示的文本
        String[] XLabel = incomeCostData.getMonths();
        // Y轴显示的文本
        //String[] YLabel = new String[]{"0" , "50" , "100" , "150"};

        // 数据的纵坐标实际值，收入
        double[] incomes = incomeCostData.getIncome();

        // XY坐标对
        XYSeries incomeSysSeries = new XYSeries("收入");
        for (int i = 0; i < incomes.length; i++) {
            incomeSysSeries.add(i + 1, incomes[i]);
        }

        // 数据的纵坐标实际值，成本
        double[] costs = incomeCostData.getCost();

        // XY坐标对
        XYSeries costSysSeries = new XYSeries("成本");
        for (int i = 0; i < costs.length; i++) {
            costSysSeries.add(i + 1, costs[i]);
        }

        List<XYSeries> dataSet = new ArrayList<>();
        dataSet.add(incomeSysSeries);
        dataSet.add(costSysSeries);

        chartUtil.setChartTitle("收入、成本月度情况");
        chartUtil.setXTitle("月");
        chartUtil.setYTitle("百万元");

        // 设置XY轴最大值
        chartUtil.setXMax(XValues.length + 1);
        chartUtil.setYMax(ValueHandle.getUpThreshold(Math.max(ValueHandle.getMax(incomes), ValueHandle.getMax(costs)), 50, 150));
        chartUtil.setYMin(ValueHandle.getDownThreshold(Math.min(ValueHandle.getMin(incomes), ValueHandle.getMin(costs)), 50, 0));

        chartUtil.setXAxisScaleLabel(XValues, XLabel);
        //chartUtil.setYAxisScaleLabel(YValues, YLabel);

        chartUtil.setDataSet(dataSet);
    }
}
