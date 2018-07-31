package com.port.logistics.holdings.fragment.analysis;
/**
 * Created by 超悟空 on 2015/6/17.
 */

import com.port.logistics.holdings.chart.BaseSupportUtils;
import com.port.logistics.holdings.chart.ColorLevelLineUtils;
import com.port.logistics.holdings.data.base.IChartData;
import com.port.logistics.holdings.data.chart.FundsStockData;
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
 * 资金存量图表
 *
 * @author 超悟空
 * @version 1.0 2015/6/17
 * @since 1.0
 */
public class FundsStockFragment extends BaseChartFragment {

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

        // 新建资金存量数据类型
        FundsStockData fundsStockData = new FundsStockData();

        // 新建数据请求任务
        PullChartData pullChartData = new PullChartData();

        // 设置回调
        pullChartData.setWorkBackListener(new WorkBack<IChartData>() {
            @Override
            public void doEndWork(boolean state, IChartData data) {
                if (state) {
                    fillChartDataSet(chartUtil, (FundsStockData) data);
                    drawChartView();
                }
            }
        });

        pullChartData.beginExecute(fundsStockData);
    }

    /**
     * 填充图表数据
     *
     * @param chartUtil      图表工具
     * @param fundsStockData 图表数据集合
     */
    private void fillChartDataSet(BaseSupportUtils chartUtil, FundsStockData fundsStockData) {

        // X坐标值
        double[] XValues = new double[fundsStockData.getDates().length];

        // 将X轴的值转为从1开始的整数
        for (int i = 0; i < XValues.length; i++) {
            XValues[i] = i + 1;
        }

        // Y坐标标签对应值
        //double[] YValues = new double[]{0 , 50 , 100 , 150};

        // X轴显示的文本
        String[] XLabel = fundsStockData.getDates();
        // Y轴显示的文本
        //String[] YLabel = new String[]{"0" , "50" , "100" , "150"};

        // 数据的纵坐标实际值
        double[] allDataSets = fundsStockData.getFunds();

        // XY坐标对
        XYSeries sysSeries = new XYSeries("物流控股本埠");
        for (int i = 0; i < allDataSets.length; i++) {
            sysSeries.add(i + 1, allDataSets[i]);
        }

        List<XYSeries> dataSet = new ArrayList<>();
        dataSet.add(sysSeries);

        chartUtil.setChartTitle("今日资金存量");
        chartUtil.setXTitle("日期");
        chartUtil.setYTitle("百万元");

        // 设置XY轴最大最小值
        chartUtil.setXMax(XValues.length + 1);
        chartUtil.setYMax(ValueHandle.getUpThreshold(ValueHandle.getMax(allDataSets), 50, 150));
        chartUtil.setYMin(ValueHandle.getDownThreshold(ValueHandle.getMin(allDataSets), 50, 0));

        chartUtil.setXAxisScaleLabel(XValues, XLabel);
        //chartUtil.setYAxisScaleLabel(YValues, YLabel);

        chartUtil.setDataSet(dataSet);

        clickPointTexts = fundsStockData.getFundPercent();

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
