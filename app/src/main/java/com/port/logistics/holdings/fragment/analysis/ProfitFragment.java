package com.port.logistics.holdings.fragment.analysis;
/**
 * Created by 超悟空 on 2015/6/18.
 */

import com.port.logistics.holdings.chart.BaseSupportUtils;
import com.port.logistics.holdings.chart.ColorLevelLineUtils;
import com.port.logistics.holdings.data.base.IChartData;
import com.port.logistics.holdings.data.chart.ProfitData;
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
 * 利润图表
 *
 * @author 超悟空
 * @version 1.0 2015/6/18
 * @since 1.0
 */
public class ProfitFragment extends BaseChartFragment {

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
        // 新建利润数据类型
        ProfitData profitData = new ProfitData();

        // 新建数据请求任务
        PullChartData pullChartData = new PullChartData();

        // 设置回调
        pullChartData.setWorkBackListener(new WorkBack<IChartData>() {
            @Override
            public void doEndWork(boolean state, IChartData data) {
                if (state) {
                    fillChartDataSet(chartUtil, (ProfitData) data);
                    drawChartView();
                }
            }
        });

        pullChartData.beginExecute(profitData);
    }

    /**
     * 填充图表数据
     *
     * @param chartUtil  图表工具
     * @param profitData 图表数据集合
     */
    private void fillChartDataSet(BaseSupportUtils chartUtil, ProfitData profitData) {

        // X坐标值
        double[] XValues = new double[profitData.getMonths().length];

        // 将X轴的值转为从1开始的整数
        for (int i = 0; i < XValues.length; i++) {
            XValues[i] = i + 1;
        }

        // Y坐标标签对应值
        //double[] YValues = new double[]{0 , 50 , 100 , 150};
        //String[] YLabel = new String[]{"0" , "50" , "100" , "150"};

        // X轴显示的文本
        String[] XLabel = profitData.getMonths();

        // 数据的纵坐标实际值，利润
        double[] profit = profitData.getProfit();

        // XY坐标对
        XYSeries profitSysSeries = new XYSeries("利润");
        for (int i = 0; i < profit.length; i++) {
            profitSysSeries.add(i + 1, profit[i]);
        }

        List<XYSeries> dataSet = new ArrayList<>();
        dataSet.add(profitSysSeries);

        chartUtil.setChartTitle("利润月度情况");
        chartUtil.setXTitle("月");
        chartUtil.setYTitle("百万元");

        // 设置XY轴最大最小值
        chartUtil.setXMax(XValues.length + 1);
        //chartUtil.setYMax(YValues[YValues.length - 1]);
        chartUtil.setYMax(ValueHandle.getUpThreshold(ValueHandle.getMax(profit), 3, 0));
        chartUtil.setYMin(ValueHandle.getDownThreshold(ValueHandle.getMin(profit), 3, 0));

        chartUtil.setXAxisScaleLabel(XValues, XLabel);
        //chartUtil.setYAxisScaleLabel(YValues, YLabel);
        chartUtil.setYLabels(6);

        chartUtil.setDataSet(dataSet);

        clickPointTexts = profitData.getCashRate();

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
