package com.port.logistics.holdings.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.port.logistics.holdings.R;
import com.port.logistics.holdings.factory.MainFragmentFactory;
import com.port.logistics.holdings.fragment.NavigationDrawerFragment;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "MainActivity.";

    /**
     * 导航抽屉片段
     */
    private NavigationDrawerFragment navigationDrawerFragment = null;

    /**
     * 导航抽屉布局
     */
    private DrawerLayout drawerLayout = null;

    /**
     * 标题栏的标题文本
     */
    private TextView toolbarTitleTextView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    /**
     * 初始化布局
     */
    private void initView() {
        // 初始化标题栏
        initToolbar();
        // 初始化抽屉导航
        initDrawer();
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
     * 初始化导航抽屉布局
     */
    private void initDrawer() {
        // 抽屉布局片段
        navigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

        // 设置选择回调
        navigationDrawerFragment.setNavigationDrawerCallbacks(new NavigationDrawerFragment.NavigationDrawerCallbacks() {
            @Override
            public void onNavigationDrawerItemSelected(int position) {
                onDrawerItemSelected(position);
            }
        });

        // 抽屉布局
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        // 设置抽屉动画关联
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, (Toolbar) findViewById(R.id.toolbar), R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        actionBarDrawerToggle.syncState();
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
    }

    /**
     * 初始化功能布局片段
     */
    private void initFragment() {
        // 默认显示主功能列表页
        showFragment(MainFragmentFactory.FragmentTagEnum.MAIN_FUNCTION);
    }

    /**
     * 执行导航功能回调
     *
     * @param position 选中的功能索引
     */
    private void onDrawerItemSelected(int position) {
        switch (position) {
            case 0:
                // 切换到主功能页
                showFragment(MainFragmentFactory.FragmentTagEnum.MAIN_FUNCTION);
                setTitle(MainFragmentFactory.getFragmentTitle(MainFragmentFactory.FragmentTagEnum.MAIN_FUNCTION));
                break;
            case 1:
                // 切换到密码修改页
                showFragment(MainFragmentFactory.FragmentTagEnum.PASSWORD_CHANGE);
                setTitle(MainFragmentFactory.getFragmentTitle(MainFragmentFactory.FragmentTagEnum.PASSWORD_CHANGE));
                break;
            case 2:
                // 切换到个人信息页
                showFragment(MainFragmentFactory.FragmentTagEnum.PERSONAL_INFO);
                setTitle(MainFragmentFactory.getFragmentTitle(MainFragmentFactory.FragmentTagEnum.PERSONAL_INFO));
                break;
            default:
        }

        // 关闭抽屉
        closeDrawer();
    }

    /**
     * 显示指定标签的片段布局，没有则新建
     *
     * @param tag 指定的标签
     */
    private void showFragment(String tag) {
        // 获取片段管理器
        FragmentManager fragmentManager = getSupportFragmentManager();
        // 尝试获取该片段
        Fragment tagFragment = fragmentManager.findFragmentByTag(tag);
        // 尝试获取当前显示的片段对象
        Fragment currentFragment = getVisibleFragment();

        if (currentFragment != null) {
            // 有显示的片段则先隐藏
            Log.i(LOG_TAG + "showFragment", currentFragment.getTag() + " fragment is gone");
            fragmentManager.beginTransaction().hide(currentFragment).commit();
        }

        if (tagFragment == null) {
            Log.i(LOG_TAG + "showFragment", tag + " fragment is null");
            // 没有则新建
            tagFragment = MainFragmentFactory.CreateFragment(tag);
            if (tagFragment != null) {
                // 添加新片段
                fragmentManager.beginTransaction().add(R.id.container, tagFragment, tag).commit();
            }
        } else {
            // 有则显示
            Log.i(LOG_TAG + "showFragment", tagFragment.getTag() + " fragment is show");
            fragmentManager.beginTransaction().show(tagFragment).commit();
        }
    }

    /**
     * 获取当前显示的片段对象
     *
     * @return 当前显示的片段实例，没有则返回null
     */
    private Fragment getVisibleFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments == null || fragments.size() == 0) {
            Log.i(LOG_TAG + "getVisibleFragment", "no fragment");
            return null;
        } else {
            Log.i(LOG_TAG + "getVisibleFragment", "fragment count is " + fragments.size());
        }
        for (Fragment fragment : fragments) {
            if (fragment != null && fragment != navigationDrawerFragment && fragment.isVisible()) {
                Log.i(LOG_TAG + "getVisibleFragment", "fragment tag is " + fragment.getTag());
                return fragment;
            }
        }
        return null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        super.onTitleChanged(title, color);
        // 设置标题
        toolbarTitleTextView.setText(title);
    }

    /**
     * 关闭导航抽屉
     */
    public void closeDrawer() {
        if (drawerLayout != null) {
            drawerLayout.closeDrawer(Gravity.LEFT);
        }
    }

    @Override
    public void onBackPressed() {

        // 如果抽屉已打开，则先关闭抽屉
        if (drawerLayout != null && drawerLayout.isDrawerOpen(Gravity.LEFT)) {
            closeDrawer();
        } else {
            super.onBackPressed();
        }
    }
}
