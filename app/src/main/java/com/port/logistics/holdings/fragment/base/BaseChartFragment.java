package com.port.logistics.holdings.fragment.base;
/**
 * Created by 超悟空 on 2015/6/17.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.port.logistics.holdings.R;
import com.port.logistics.holdings.chart.BaseSupportUtils;

/**
 * 带有图表的片段
 *
 * @author 超悟空
 * @version 1.0 2015/6/17
 * @since 1.0
 */
public abstract class BaseChartFragment extends Fragment {

    /**
     * 图表工具
     */
    private BaseSupportUtils chartUtil = null;

    /**
     * 图表布局位置
     */
    private LinearLayout chartLinearLayout = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_chart, container, false);

        // 初始化布局
        initView(rootView);

        return rootView;
    }

    /**
     * 初始化布局
     *
     * @param rootView 根布局
     */
    private void initView(View rootView) {
        // 得到图表工具实例
        chartUtil = onCreateChartUtil();

        chartLinearLayout = (LinearLayout) rootView.findViewById(R.id.fragment_chart_linear_layout);

        // 构建图表
        onBuildChart(chartUtil);
    }

    /**
     * 绘制图表，需要子类显式调用
     */
    protected final void drawChartView() {
        // 绘制图表
        chartLinearLayout.addView(chartUtil.GenerateChartView());
    }

    /**
     * 创建图表渲染工具
     *
     * @return 图表渲染工具实例
     */
    protected abstract BaseSupportUtils onCreateChartUtil();

    /**
     * 构建图表，
     * 包括填充图表数据及生成图表，
     * 数据填充完毕后显式调用{@link #drawChartView()}绘制图表
     *
     * @param chartUtil 图表绘制工具
     */
    protected abstract void onBuildChart(BaseSupportUtils chartUtil);
}
