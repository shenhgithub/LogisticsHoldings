package com.port.logistics.holdings.chart;
/**
 * Created by 超悟空 on 2015/6/16.
 */

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

import com.port.logistics.holdings.R;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import org.achartengine.renderer.support.SupportSeriesRender;
import org.achartengine.renderer.support.SupportXAlign;
import org.achartengine.renderer.support.SupportYAlign;

import java.util.List;

/**
 * 图表工具基类
 *
 * @author 超悟空
 * @version 1.0 2015/6/16
 * @since 1.0
 */
public abstract class BaseSupportUtils {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "BaseSupportUtils.";

    /**
     * 上下文
     */
    protected Context context;

    /**
     * 图表渲染器
     */
    private XYMultipleSeriesRenderer xyMultipleSeriesRenderer = new XYMultipleSeriesRenderer();

    /**
     * 图表数据集
     */
    private XYMultipleSeriesDataset xyMultipleSeriesDataset = new XYMultipleSeriesDataset();

    /**
     * 图表标题
     */
    private String chartTitle = null;

    /**
     * X轴标题
     */
    private String XTitle = null;

    /**
     * Y轴标题
     */
    private String YTitle = null;

    /**
     * X轴刻度值集合
     */
    private double[] XAxisScaleValues = null;

    /**
     * X轴刻度显示文本集合
     */
    private String[] XAxisScaleTexts = null;

    /**
     * Y轴刻度值集合
     */
    private double[] YAxisScaleValues = null;

    /**
     * Y轴刻度显示文本集合
     */
    private String[] YAxisScaleTexts = null;

    /**
     * 要显示的数据集合
     */
    private List<XYSeries> dataSet = null;

    /**
     * X轴最小值
     */
    private double XMin = 0;

    /**
     * Y轴最小值
     */
    private double YMin = 0;

    /**
     * X轴最大值
     */
    private double XMax = 10;

    /**
     * Y轴最大值
     */
    private double YMax = 100;

    /**
     * Y轴显示标签数
     */
    private int YLabels = 5;

    /**
     * X轴显示标签数
     */
    private int XLabels = 10;

    /**
     * 线和点的扩展属性
     */
    private SupportSeriesRender lineSeriesRender = null;

    /**
     * 点击监听器
     */
    protected ViewClickListener viewClickListener = null;

    /**
     * 带有上下文的构造函数
     *
     * @param context 上下文
     */
    protected BaseSupportUtils(Context context) {
        this.context = context;
    }

    /**
     * 设置图表标题
     *
     * @param chartTitle 标题文本
     */
    public void setChartTitle(String chartTitle) {
        this.chartTitle = chartTitle;
    }

    /**
     * 设置X轴标题
     *
     * @param XTitle 标题文本
     */
    public void setXTitle(String XTitle) {
        this.XTitle = XTitle;
    }

    /**
     * 设置Y轴标题
     *
     * @param YTitle 标题文本
     */
    public void setYTitle(String YTitle) {
        this.YTitle = YTitle;
    }

    /**
     * 设置Y轴标签数
     *
     * @param YLabels 最大标签数
     */
    public void setYLabels(int YLabels) {
        this.YLabels = YLabels;
    }

    /**
     * 设置X轴标签数
     *
     * @param XLabels 最大标签数
     */
    public void setXLabels(int XLabels) {
        this.XLabels = XLabels;
    }

    /**
     * 设置X轴刻度值集合
     *
     * @param XAxisScaleValues X轴刻度值集合，与{@link #XAxisScaleTexts}数组等长
     * @param XAxisScaleTexts  X轴刻度文本，与{@link #XAxisScaleValues}数组等长
     */
    public void setXAxisScaleLabel(double[] XAxisScaleValues, String[] XAxisScaleTexts) {
        this.XAxisScaleValues = XAxisScaleValues;
        this.XAxisScaleTexts = XAxisScaleTexts;
    }


    /**
     * 设置Y轴刻度值集合
     *
     * @param YAxisScaleValues Y轴刻度值集合，与{@link #YAxisScaleTexts}数组等长
     * @param YAxisScaleTexts  Y轴刻度显示文本集合，与{@link #YAxisScaleValues}数组等长
     */
    public void setYAxisScaleLabel(double[] YAxisScaleValues, String[] YAxisScaleTexts) {
        this.YAxisScaleValues = YAxisScaleValues;
        this.YAxisScaleTexts = YAxisScaleTexts;
    }

    /**
     * 设置X轴最小值
     *
     * @param XMin 最小值，默认为0
     */
    public void setXMin(double XMin) {
        this.XMin = XMin;
    }

    /**
     * 设置Y轴最小值
     *
     * @param YMin 最小值，默认为0
     */
    public void setYMin(double YMin) {
        this.YMin = YMin;
    }

    /**
     * 设置X轴最大值
     *
     * @param XMax 最大值，默认为10
     */
    public void setXMax(double XMax) {
        this.XMax = XMax;
    }

    /**
     * 设置Y轴最大值
     *
     * @param YMax 最大值，默认为100
     */
    public void setYMax(double YMax) {
        this.YMax = YMax;
    }

    /**
     * 设置点击监听器
     *
     * @param viewClickListener 监听器对象
     */
    public void setViewClickListener(ViewClickListener viewClickListener) {
        this.viewClickListener = viewClickListener;
    }

    /**
     * 设置要显示的数据集合
     *
     * @param dataSet 数据集合，每一条为一组坐标序列
     */
    public void setDataSet(List<XYSeries> dataSet) {
        this.dataSet = dataSet;
    }

    /**
     * 使用先前配置的参数生成最终渲染完成的图表控件
     *
     * @return 渲染完成的图表控件
     */
    public final View GenerateChartView() {
        // 初始化渲染器
        initXYMultipleSeriesRenderer();

        // 子类重设渲染器
        onCreateRender(xyMultipleSeriesRenderer);

        // 设置点和线的相关属性
        onSetSupportSeriesRender(xyMultipleSeriesRenderer);

        // 填充数据
        onFillDataSet(dataSet);

        // 生成图表控件
        View view = onCreateView(context, xyMultipleSeriesDataset, xyMultipleSeriesRenderer);

        // 点击点的事件
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onViewClickEvent((GraphicalView) v);
            }
        });

        return view;
    }

    /**
     * 创建特定类型的图表，使用{@link ChartFactory}创建
     *
     * @param context 上下文
     * @param dataSet 数据集
     * @param render  渲染器
     *
     * @return 生成的图表控件
     */
    protected abstract View onCreateView(Context context, XYMultipleSeriesDataset dataSet, XYMultipleSeriesRenderer render);

    /**
     * 设置点和线的相关属性，
     * 包括颜色分级，
     * 方法体内需要设置{@link XYMultipleSeriesRenderer#addSupportRenderer(SupportSeriesRender)}
     *
     * @param render 渲染器
     */
    protected void onSetSupportSeriesRender(XYMultipleSeriesRenderer render) {
        lineSeriesRender = new SupportSeriesRender();
        lineSeriesRender.setClickPointColor(Color.RED);
        lineSeriesRender.setColorLevelValid(false);
        render.addSupportRenderer(lineSeriesRender);
    }

    /**
     * 图表点击事件，绑定
     *
     * @param graphicalView 图表布局
     */
    protected void onViewClickEvent(GraphicalView graphicalView) {
        if (viewClickListener != null) {
            viewClickListener.onClick(lineSeriesRender, graphicalView);
        }
    }

    /**
     * 向图表填充数据
     *
     * @param dataSet 数据源
     */
    protected void onFillDataSet(List<XYSeries> dataSet) {
        for (XYSeries xySeries : dataSet) {
            // 设置数据集
            xyMultipleSeriesDataset.addSeries(xySeries);
        }
    }

    /**
     * 初始化表渲染器
     */
    private void initXYMultipleSeriesRenderer() {
        Log.i(LOG_TAG + "initXYMultipleSeriesRenderer", "initXYMultipleSeriesRenderer() is invoked");

        // 默认字体大小
        float textSize = 20f;

        // 设置标题
        xyMultipleSeriesRenderer.setChartTitle(chartTitle == null ? "Chart Title" : chartTitle);
        xyMultipleSeriesRenderer.setXTitle(XTitle == null ? "X Title" : XTitle);
        xyMultipleSeriesRenderer.setYTitle(YTitle == null ? "Y Title" : YTitle);

        //设置字体的大小
        xyMultipleSeriesRenderer.setAxisTitleTextSize(textSize);
        xyMultipleSeriesRenderer.setChartTitleTextSize(textSize);
        xyMultipleSeriesRenderer.setLabelsTextSize(textSize);
        xyMultipleSeriesRenderer.setLegendTextSize(textSize);

        // 设置背景颜色，默认白色
        xyMultipleSeriesRenderer.setBackgroundColor(Color.WHITE);
        // 设置页边空白的颜色
        xyMultipleSeriesRenderer.setMarginsColor(Color.WHITE);
        // 设置背景颜色生效
        xyMultipleSeriesRenderer.setApplyBackgroundColor(true);

        // 设置坐标轴，轴的颜色
        xyMultipleSeriesRenderer.setXLabelsColor(Color.BLACK);
        xyMultipleSeriesRenderer.setYLabelsColor(0, Color.BLACK);
        // 设置网格的颜色
        xyMultipleSeriesRenderer.setShowGrid(true);
        xyMultipleSeriesRenderer.setGridColor(context.getResources().getColor(R.color.chart_grid_color));
        xyMultipleSeriesRenderer.addSeriesRenderer(defaultSimpleSeriesRender());
        // 设置点的大小
        xyMultipleSeriesRenderer.setPointSize(12f);

        // 设置合适的刻度,在轴上显示的数量是 MAX / labels

        // 设置 Y 轴不显示数字（改用我们手动添加的文字标签）
        xyMultipleSeriesRenderer.setYLabels(0);
        // 设置 X 轴不显示数字（改用我们手动添加的文字标签）
        xyMultipleSeriesRenderer.setXLabels(0);

        if (XAxisScaleValues != null && XAxisScaleTexts != null && XAxisScaleValues.length == XAxisScaleTexts.length) {
            // 自定义显示
            for (int i = 0; i < XAxisScaleValues.length; i++) {
                xyMultipleSeriesRenderer.addXTextLabel(XAxisScaleValues[i], XAxisScaleTexts[i]);
            }
        } else {
            // 默认显示
            xyMultipleSeriesRenderer.setXLabels(XLabels);
        }

        if (YAxisScaleValues != null && YAxisScaleTexts != null && YAxisScaleValues.length == YAxisScaleTexts.length) {
            // 自定义显示
            for (int i = 0; i < YAxisScaleValues.length; i++) {
                xyMultipleSeriesRenderer.addYTextLabel(YAxisScaleValues[i], YAxisScaleTexts[i]);
            }
            xyMultipleSeriesRenderer.setYLabels(YAxisScaleValues.length);
        } else {
            // 默认显示
            xyMultipleSeriesRenderer.setYLabels(YLabels);
        }

        // 图表边距
        xyMultipleSeriesRenderer.setMargins(new int[]{50 , 40 , 20 , 20});

        // 值范围
        xyMultipleSeriesRenderer.setXAxisMin(XMin);
        xyMultipleSeriesRenderer.setYAxisMin(YMin);
        xyMultipleSeriesRenderer.setXAxisMax(XMax);
        xyMultipleSeriesRenderer.setYAxisMax(YMax);

        // 设置x,y轴显示的排列,默认是 Align.CENTER
        xyMultipleSeriesRenderer.setSupportYAlign(SupportYAlign.TOP);
        xyMultipleSeriesRenderer.setSupportXAlign(SupportXAlign.RIGHT);
        xyMultipleSeriesRenderer.setXLabelsAlign(Paint.Align.CENTER);
        xyMultipleSeriesRenderer.setYLabelsAlign(Paint.Align.RIGHT);

        xyMultipleSeriesRenderer.setAntialiasing(true);
        xyMultipleSeriesRenderer.setFitLegend(true);
        xyMultipleSeriesRenderer.setShowAxes(true);  // 设置坐标轴是否可见
        xyMultipleSeriesRenderer.setShowLegend(true);

        // 是否支持图表移动
        xyMultipleSeriesRenderer.setPanEnabled(false, false);// 表盘移动
        xyMultipleSeriesRenderer.setZoomEnabled(false, false);
        xyMultipleSeriesRenderer.setClickEnabled(true);// 是否可点击
        xyMultipleSeriesRenderer.setSelectableBuffer(30);// 点击区域大小

        //设置目标线
        //        xyMultipleSeriesRenderer.setTargetLineVisible(true);
        //        xyMultipleSeriesRenderer.setTargetLineColor(Color.MAGENTA);
        //        xyMultipleSeriesRenderer.setTargetValue(12.5f);
        //        xyMultipleSeriesRenderer.setTargetLineStyle(SupportTargetLineStyle.Line_Dotted);
        //        xyMultipleSeriesRenderer.setTargetDescription("GOAL");


        // 文本字体
        //        Typeface typeFace = Typeface.createFromAsset(context.getAssets(), "fonts/Futura.ttc");
        //        xyMultipleSeriesRenderer.setTextTypeface(typeFace);
    }

    protected abstract void onCreateRender(XYMultipleSeriesRenderer render);

    /**
     * 获取默认点属性
     *
     * @return XY坐标属性渲染器
     */
    protected XYSeriesRenderer defaultSimpleSeriesRender() {
        XYSeriesRenderer renderer = new XYSeriesRenderer();
        // 设置点颜色
        renderer.setColor(context.getResources().getColor(R.color.chart_line_green_color));
        // 是否是实心的点
        renderer.setFillPoints(true);
        // 设置是否在点上显示数据
        renderer.setDisplayChartValues(true);
        // 设置曲线的宽度
        renderer.setLineWidth(3f);
        // 点样式
        renderer.setPointStyle(PointStyle.CIRCLE);
        // 显示值大小
        renderer.setChartValuesTextSize(20f);
        renderer.setChartValuesSpacing(15f);
        // 填充颜色
        renderer.setInnerCircleColor(Color.BLUE);
        return renderer;
    }

    /**
     * 图表点的点击回调
     */
    public interface ViewClickListener {
        /**
         * 点击操作
         *
         * @param lineSeriesRender 当前使用的点线扩展属性
         * @param graphicalView    图表布局
         */
        void onClick(SupportSeriesRender lineSeriesRender, GraphicalView graphicalView);
    }
}

