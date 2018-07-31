package com.port.logistics.holdings.chart;
/**
 * Created by 超悟空 on 2015/6/16.
 */

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.support.SupportSeriesRender;

/**
 * 普通曲线工具
 *
 * @author 超悟空
 * @version 1.0 2015/6/16
 * @since 1.0
 */

public class ColorLevelLineUtils extends BaseSupportUtils {
    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "ColorLevelLineUtils.";

    /**
     * 线和点的扩展属性
     */
    private SupportSeriesRender lineSeriesRender = null;

    public ColorLevelLineUtils(Context context) {
        super(context);
    }

    @Override
    protected View onCreateView(Context context, XYMultipleSeriesDataset dataSet, XYMultipleSeriesRenderer render) {
        return ChartFactory.getLineChartView(context, dataSet, render);
    }

    @Override
    protected void onSetSupportSeriesRender(XYMultipleSeriesRenderer render) {
        lineSeriesRender = new SupportSeriesRender();
        lineSeriesRender.setClickPointColor(Color.RED);
        lineSeriesRender.setColorLevelValid(false);
        render.addSupportRenderer(lineSeriesRender);
    }

    @Override
    protected void onCreateRender(XYMultipleSeriesRenderer render) {

    }

    @Override
    protected void onViewClickEvent(GraphicalView graphicalView) {
        if (viewClickListener != null) {
            viewClickListener.onClick(lineSeriesRender, graphicalView);
        }
    }
}
