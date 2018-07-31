package com.port.logistics.holdings.fragment.analysis;
/**
 * Created by 超悟空 on 2015/6/18.
 */

import com.port.logistics.holdings.chart.BaseSupportUtils;
import com.port.logistics.holdings.chart.ColorLevelDoubleLineUtils;
import com.port.logistics.holdings.data.base.IChartData;
import com.port.logistics.holdings.data.chart.AssetDebtData;
import com.port.logistics.holdings.fragment.base.BaseChartFragment;
import com.port.logistics.holdings.util.ValueHandle;
import com.port.logistics.holdings.work.PullChartData;

import org.achartengine.model.XYSeries;
import org.mobile.model.work.WorkBack;

import java.util.ArrayList;
import java.util.List;

/**
 * 资产负债图表
 *
 * @author 超悟空
 * @version 1.0 2015/6/18
 * @since 1.0
 */
public class AssetDebtFragment extends BaseChartFragment {

    @Override
    protected BaseSupportUtils onCreateChartUtil() {
        return new ColorLevelDoubleLineUtils(getActivity());
    }

    @Override
    protected void onBuildChart(final BaseSupportUtils chartUtil) {
        // 新建资产负债数据类型
        AssetDebtData assetDebtData = new AssetDebtData();

        // 新建数据请求任务
        PullChartData pullChartData = new PullChartData();

        // 设置回调
        pullChartData.setWorkBackListener(new WorkBack<IChartData>() {
            @Override
            public void doEndWork(boolean state, IChartData data) {
                if (state) {
                    fillChartDataSet(chartUtil, (AssetDebtData) data);
                    drawChartView();
                }
            }
        });

        pullChartData.beginExecute(assetDebtData);
    }

    /**
     * 填充图表数据
     *
     * @param chartUtil     图表工具
     * @param assetDebtData 图表数据集合
     */
    private void fillChartDataSet(BaseSupportUtils chartUtil, AssetDebtData assetDebtData) {

        // X坐标值
        double[] XValues = new double[assetDebtData.getMonths().length];

        // 将X轴的值转为从1开始的整数
        for (int i = 0; i < XValues.length; i++) {
            XValues[i] = i + 1;
        }

        // Y坐标标签对应值
        //double[] YValues = new double[]{0 , 50 , 100 , 150};
        //String[] YLabel = new String[]{"0" , "50" , "100" , "150"};

        // X轴显示的文本
        String[] XLabel = assetDebtData.getMonths();

        // 数据的纵坐标实际值，资产
        double[] asset = assetDebtData.getAsset();

        // XY坐标对
        XYSeries assetSysSeries = new XYSeries("资产");
        for (int i = 0; i < asset.length; i++) {
            assetSysSeries.add(i + 1, asset[i]);
        }

        // 数据的纵坐标实际值，负债
        double[] debt = assetDebtData.getDebt();

        // XY坐标对
        XYSeries debtSysSeries = new XYSeries("负债");
        for (int i = 0; i < debt.length; i++) {
            debtSysSeries.add(i + 1, debt[i]);
        }

        List<XYSeries> dataSet = new ArrayList<>();
        dataSet.add(assetSysSeries);
        dataSet.add(debtSysSeries);

        chartUtil.setChartTitle("公司流动资产、负债情况");
        chartUtil.setXTitle("月");
        chartUtil.setYTitle("百万元");

        // 设置XY轴最大值
        chartUtil.setXMax(XValues.length + 1);
        //chartUtil.setYMax(YValues[YValues.length - 1]);
        chartUtil.setYMax(ValueHandle.getUpThreshold(Math.max(ValueHandle.getMax(asset), ValueHandle.getMax(debt)), 500, 2000));
        chartUtil.setYMin(ValueHandle.getDownThreshold(Math.min(ValueHandle.getMin(asset), ValueHandle.getMin(debt)), 500, 0));

        chartUtil.setXAxisScaleLabel(XValues, XLabel);
        //chartUtil.setYAxisScaleLabel(YValues, YLabel);

        chartUtil.setDataSet(dataSet);
    }
}
