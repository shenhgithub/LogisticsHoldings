package com.port.logistics.holdings.activity;
/**
 * Created by 超悟空 on 2015/6/17.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.port.logistics.holdings.R;
import com.port.logistics.holdings.adapter.FunctionIndex;
import com.port.logistics.holdings.factory.FunctionFragmentFactory;
import com.port.logistics.holdings.fragment.FunctionErrorFragment;
import com.port.logistics.holdings.util.StaticValue;

/**
 * 不带有导航抽屉的Activity，
 * 使用{@link com.port.logistics.holdings.factory.FunctionFragmentFactory}生成承载的片段
 *
 * @author 超悟空
 * @version 1.0 2015/6/17
 * @since 1.0
 */
public class NoDrawerActivity extends AppCompatActivity {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "NoDrawerActivity.";

    /**
     * 标题栏的标题文本
     */
    private TextView toolbarTitleTextView = null;

    /**
     * 功能代码
     */
    private String functionCode = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_drawer_with_fragment);

        initView();
    }

    /**
     * 初始化布局
     */
    private void initView() {
        // 初始化标题栏
        initToolbar();
        // 提取功能代码
        loadFunctionCode();
        // 初始化界面布局
        initFragment();
    }

    /**
     * 初始化标题栏
     */
    private void initToolbar() {
        // 得到Toolbar标题栏
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // 得到标题文本
        toolbarTitleTextView = (TextView) findViewById(R.id.toolbar_title);

        // 关联ActionBar
        setSupportActionBar(toolbar);

        // 取消原actionBar标题
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    /**
     * 提取功能代码
     */
    private void loadFunctionCode() {
        // 提取功能码
        functionCode = getIntent().getStringExtra(StaticValue.FUNCTION_FRAGMENT_MARK);
        Log.i(LOG_TAG + "loadFunctionCode", "function code is " + functionCode);

        // 设置标题
        if (functionCode != null) {
            setTitle(FunctionIndex.getFunctionIndex().getFunctionName(functionCode));
        } else {
            setTitle(R.string.app_name);
        }
    }

    /**
     * 初始化功能布局片段
     */
    private void initFragment() {
        // 将要加载的片段
        Fragment fragment = null;

        if (functionCode != null) {
            // 创建指定的功能片段
            fragment = FunctionFragmentFactory.CreateFunctionFragment(functionCode);
        }

        if (fragment == null) {
            // 加载失败
            fragment = new FunctionErrorFragment();
        }

        Log.i(LOG_TAG + "initFragment", "fragment is " + fragment.toString());

        getSupportFragmentManager().beginTransaction().add(R.id.activity_no_drawer_fragment_container, fragment).commit();
    }

    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        super.onTitleChanged(title, color);
        // 设置标题
        toolbarTitleTextView.setText(title);
    }
}
