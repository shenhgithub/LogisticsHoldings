package com.port.logistics.holdings.fragment.base;
/**
 * Created by 超悟空 on 2015/6/17.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.port.logistics.holdings.R;
import com.port.logistics.holdings.activity.LoginActivity;
import com.port.logistics.holdings.activity.NoDrawerActivity;
import com.port.logistics.holdings.adapter.FunctionIndex;
import com.port.logistics.holdings.util.StaticValue;

import org.mobile.util.LoginStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 仅有GridView的片段布局
 *
 * @author 超悟空
 * @version 1.0 2015/6/17
 * @since 1.0
 */
public abstract class BaseGridFragment extends Fragment {

    /**
     * 日志前缀
     */
    private static final String LOG_TAG = "BaseGridFragment.";

    /**
     * 功能名称取值标签
     */
    private static final String FUNCTION_NAME = "function_name";

    /**
     * 功能图标取值图标
     */
    private static final String FUNCTION_IMAGE = "function_image";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // 当前功能片段布局
        View rootView = inflater.inflate(onLayoutId(), container, false);

        // 初始化表格布局
        initGridView(rootView);

        // 自定义布局
        onCustomView(rootView);

        return rootView;
    }

    /**
     * 设置根布局
     *
     * @return 布局ID
     */
    protected abstract int onLayoutId();

    /**
     * 设置表格布局
     *
     * @return 布局ID
     */
    protected abstract int onGridViewId();

    /**
     * 表格填充的功能代码列表
     *
     * @return 代码列表
     */
    protected abstract List<String> onFunctionCodeList();

    /**
     * 自定义布局
     *
     * @param rootView 根布局
     */
    protected void onCustomView(View rootView) {

    }

    /**
     * 设置表格适配器，否则使用默认配置
     *
     * @return 简单适配器对象
     */
    protected SimpleAdapter onGridSimpleAdapter() {
        return new SimpleAdapter(getActivity(), initFunctionResource(), R.layout.function_grid_item, new String[]{FUNCTION_NAME , FUNCTION_IMAGE}, new int[]{R.id.function_grid_item_text , R.id.function_grid_item_image});
    }

    /**
     * 初始化表格布局
     *
     * @param rootView 根布局
     */
    private void initGridView(View rootView) {
        // 片段中的表格布局
        GridView gridView = (GridView) rootView.findViewById(onGridViewId());

        // 表格使用的数据适配器
        SimpleAdapter adapter = onGridSimpleAdapter();

        // 设置适配器
        gridView.setAdapter(adapter);

        // 设置监听器
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onGridItemClick(view, position);
            }
        });
    }

    /**
     * 表格项点击事件触发操作，
     * 默认触发功能跳转，
     * 并检测登录状态
     *
     * @param view     当前点击的功能布局对象
     * @param position 点击的位置索引
     */
    protected void onGridItemClick(View view, int position) {

        if (!LoginStatus.getLoginStatus().isLogin()) {
            // 未登录
            // 新建意图,跳转到登录页面
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            // 执行跳转
            startActivity(intent);
            return;
        }

        // 获取功能名称控件
        TextView textView = (TextView) view.findViewById(R.id.function_grid_item_text);

        // 功能代码
        String functionCode = FunctionIndex.getFunctionIndex().getFunctionCode(textView.getText().toString());


        // 新建意图
        Intent intent = new Intent(getActivity(), NoDrawerActivity.class);
        // 加入功能代码
        intent.putExtra(StaticValue.FUNCTION_FRAGMENT_MARK, functionCode);
        // 执行跳转
        startActivity(intent);
    }

    /**
     * 生成功能项标签资源的数据源
     *
     * @return 返回SimpleAdapter适配器使用的数据源
     */
    private List<Map<String, Object>> initFunctionResource() {
        // 加载功能项
        // 资源集合
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();

        // 功能代码数组
        List<String> functionCodes = onFunctionCodeList();

        // 功能资源适配器
        FunctionIndex functionIndex = FunctionIndex.getFunctionIndex();
        for (String functionCode : functionCodes) {

            // 新建一个功能项标签
            Map<String, Object> function = new HashMap<>();

            // 添加功能标签名称资源
            function.put(FUNCTION_NAME, functionIndex.getFunctionName(functionCode));
            Log.i(LOG_TAG + "initFunctionResource", "function name is " + function.get(FUNCTION_NAME));

            // 添加功能标签图标资源
            function.put(FUNCTION_IMAGE, functionIndex.getFunctionImage(functionCode));

            // 将标签加入数据源
            dataList.add(function);
        }

        return dataList;
    }
}