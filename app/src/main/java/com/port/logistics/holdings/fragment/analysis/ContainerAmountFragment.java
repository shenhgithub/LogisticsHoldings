package com.port.logistics.holdings.fragment.analysis;
/**
 * Created by 超悟空 on 2015/6/19.
 */

import com.port.logistics.holdings.chart.BaseSupportUtils;
import com.port.logistics.holdings.chart.ColorLevelLineUtils;
import com.port.logistics.holdings.data.base.IChartData;
import com.port.logistics.holdings.data.chart.ContainerAmountData;
import com.port.logistics.holdings.fragment.base.BaseChartFragment;
import com.port.logistics.holdings.util.ValueHandle;
import com.port.logistics.holdings.work.PullChartData;

import org.achartengine.GraphicalView;
import org.achartengine.model.SeriesSelection;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.support.SupportSeriesRender;
import org.mobile.model.work.WorkBack;

import java.util.ArrayList;
import java.util.List;

/**
 * 集装箱代理量
 *
 * @author 超悟空
 * @version 1.0 2015/6/19
 * @since 1.0
 */
public class ContainerAmountFragment extends BaseChartFragment {

    /**
     * 点击点显示的内容
     */
    private String[] clickPointTexts = null;

    @Override
    protected BaseSupportUtils onCreateChartUtil() {
        return new ColorLevelLineUtils(getActivity());
    }

    @Override
    protected void onBuildChart(final BaseSupportUtils chartUtil) {

        // 新建集装箱数据类型
        ContainerAmountData containerAmountData = new ContainerAmountData();

        // 新建数据请求任务
        PullChartData pullChartData = new PullChartData();

        // 设置回调
        pullChartData.setWorkBackListener(new WorkBack<IChartData>() {
            @Override
            public void doEndWork(boolean state, IChartData data) {
                if (state) {
                    fillChartDataSet(chartUtil, (ContainerAmountData) data);
                    drawChartView();
                }
            }
        });

        pullChartData.beginExecute(containerAmountData);
    }

    /**
     * 填充图表数据
     *
     * @param chartUtil           图表工具
     * @param containerAmountData 图表数据集合
     */
    private void fillChartDataSet(BaseSupportUtils chartUtil, ContainerAmountData containerAmountData) {

        // X坐标值
        double[] XValues = new double[containerAmountData.getDates().length];

        // 将X轴的值转为从1开始的整数
        for (int i = 0; i < XValues.length; i++) {
            XValues[i] = i + 1;
        }

        // Y坐标标签对应值
        //double[] YValues = new double[]{0 , 50 , 100 , 150};

        // X轴显示的文本
        String[] XLabel = containerAmountData.getDates();
        // Y轴显示的文本
        //String[] YLabel = new String[]{"0" , "50" , "100" , "150"};

        // 数据的纵坐标实际值
        double[] amount = containerAmountData.getAmount();

        // XY坐标对
        XYSeries sysSeries = new XYSeries("代理量");
        for (int i = 0; i < amount.length; i++) {
            sysSeries.add(i + 1, amount[i]);
        }

        List<XYSeries> dataSet = new ArrayList<>();
        dataSet.add(sysSeries);

        chartUtil.setChartTitle("本年集装箱代理量趋势分析");
        chartUtil.setXTitle("月度");
        chartUtil.setYTitle("TUE");

        // 设置XY轴最大最小值
        chartUtil.setXMax(XValues.length + 1);
        chartUtil.setYMax(ValueHandle.getUpThreshold(ValueHandle.getMax(amount), 300, 1500));
        chartUtil.setYMin(ValueHandle.getDownThreshold(ValueHandle.getMin(amount), 300, 0));

        chartUtil.setXAxisScaleLabel(XValues, XLabel);
        //chartUtil.setYAxisScaleLabel(YValues, YLabel);
        chartUtil.setYLabels(6);

        chartUtil.setDataSet(dataSet);

        clickPointTexts = containerAmountData.getAmountPercent();

        chartUtil.setViewClickListener(new BaseSupportUtils.ViewClickListener() {
            @Override
            public void onClick(SupportSeriesRender lineSeriesRender, GraphicalView graphicalView) {
                if (clickPointTexts != null) {
                    // 点击后显示百分比

                    SeriesSelection seriesSelection = graphicalView.getCurrentSeriesAndPoint();
                    if (seriesSelection != null) {
                        graphicalView.handPointClickEvent(lineSeriesRender, clickPointTexts[seriesSelection.getPointIndex()]);
                    }
                }
            }
        });
    }
}
