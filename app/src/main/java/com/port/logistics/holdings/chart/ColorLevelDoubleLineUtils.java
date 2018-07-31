package com.port.logistics.holdings.chart;
/**
 * Created by 超悟空 on 2015/6/18.
 */

import android.content.Context;
import android.graphics.Paint;
import android.view.View;

import com.port.logistics.holdings.R;

import org.achartengine.ChartFactory;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import org.achartengine.renderer.support.SupportSeriesRender;

/**
 * 双曲线工具
 *
 * @author 超悟空
 * @version 1.0 2015/6/18
 * @since 1.0
 */
public class ColorLevelDoubleLineUtils extends BaseSupportUtils {

    /**
     * 带有上下文的构造函数
     *
     * @param context 上下文
     */
    public ColorLevelDoubleLineUtils(Context context) {
        super(context);
    }

    @Override
    protected View onCreateView(Context context, XYMultipleSeriesDataset dataSet, XYMultipleSeriesRenderer render) {
        return ChartFactory.getLineChartView(context, dataSet, render);
    }

    @Override
    protected void onCreateRender(XYMultipleSeriesRenderer render) {

        render.removeAllRenderers();

        XYSeriesRenderer xySeriesRenderer1 = defaultSimpleSeriesRender();
        xySeriesRenderer1.setColor(context.getResources().getColor(R.color.chart_line_orange_color));
        xySeriesRenderer1.setChartValuesTextAlign(Paint.Align.RIGHT);

        XYSeriesRenderer xySeriesRenderer2 = defaultSimpleSeriesRender();
        xySeriesRenderer2.setColor(context.getResources().getColor(R.color.chart_line_green_color));
        xySeriesRenderer2.setChartValuesTextAlign(Paint.Align.LEFT);

        render.addSeriesRenderer(xySeriesRenderer1);
        render.addSeriesRenderer(xySeriesRenderer2);
    }

    @Override
    protected void onSetSupportSeriesRender(XYMultipleSeriesRenderer render) {
        SupportSeriesRender lineSeriesRender1 = new SupportSeriesRender();
        lineSeriesRender1.setColorLevelValid(false);
        render.addSupportRenderer(lineSeriesRender1);

        SupportSeriesRender lineSeriesRender2 = new SupportSeriesRender();
        lineSeriesRender2.setColorLevelValid(false);
        render.addSupportRenderer(lineSeriesRender2);
    }
}
