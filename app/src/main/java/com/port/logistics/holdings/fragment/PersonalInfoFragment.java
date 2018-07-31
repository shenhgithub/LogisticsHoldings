package com.port.logistics.holdings.fragment;
/**
 * Created by 超悟空 on 2015/6/15.
 */

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.port.logistics.holdings.R;
import com.port.logistics.holdings.data.PersonalInfo;
import com.port.logistics.holdings.work.ChangePersonalInfo;
import com.port.logistics.holdings.work.PullPersonalInfo;

import org.mobile.common.dialog.NoNetworkDialog;
import org.mobile.common.dialog.SimpleDialog;
import org.mobile.common.function.CheckNetwork;
import org.mobile.model.work.WorkBack;
import org.mobile.util.LoginStatus;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 个人信息片段
 *
 * @author 超悟空
 * @version 1.0 2015/6/15
 * @since 1.0
 */
public class PersonalInfoFragment extends Fragment {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "PersonalInfoActivity.";

    /**
     * 标题栏的标题文本
     */
    private TextView toolbarTitleTextView = null;

    /**
     * 电话输入框
     */
    private EditText phoneEditText = null;

    /**
     * 手机输入框
     */
    private EditText mobileEditText = null;

    /**
     * 邮箱输入框
     */
    private EditText emailEditText = null;

    /**
     * 进度条
     */
    private ProgressDialog progressDialog = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 根布局
        View rootView = inflater.inflate(R.layout.fragment_personal_info, container, false);

        // 加载界面
        initView(rootView);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        // 加载当前个人信息
        initInfo();
    }

    /**
     * 加载当前个人信息
     */
    private void initInfo() {
        // 判断是否有网络
        if (!CheckNetwork.isOpenNetwork()) {
            // 提示无网络
            NoNetworkDialog.showNoNetworkDialog(getActivity());
            return;
        }

        // 判断是否已登录
        if (!LoginStatus.getLoginStatus().isLogin()) {
            Log.i(LOG_TAG + "checkPassword", "not login");
            // 弹出提示窗
            SimpleDialog.showDialog(getActivity(), getString(R.string.login_alert));
            return;
        }

        // 新建个人信息获取任务
        PullPersonalInfo pullPersonalInfo = new PullPersonalInfo();

        // 设置任务回调
        pullPersonalInfo.setWorkBackListener(new WorkBack<PersonalInfo>() {
            @Override
            public void doEndWork(boolean state, PersonalInfo data) {
                // 关闭进度条
                progressDialog.cancel();

                if (state && data != null) {
                    // 数据获取成功
                    // 给输入框赋值
                    setEditText(data);
                }
            }
        });

        // 打开旋转进度条
        startProgressDialog(getString(R.string.personal_info_loading));

        // 执行任务
        pullPersonalInfo.beginExecute();
    }

    /**
     * 给输入框赋值
     *
     * @param info 个人信息数据对象
     */
    private void setEditText(PersonalInfo info) {
        phoneEditText.setText(info.getPhone());
        mobileEditText.setText(info.getMobile());
        emailEditText.setText(info.getEmail());
    }

    /**
     * 初始化控件
     *
     * @param rootView 根布局
     */
    private void initView(View rootView) {

        // 初始化输入框
        initEditText(rootView);

        // 初始化保存按钮
        initButton(rootView);
    }

    /**
     * 初始化输入框
     *
     * @param rootView 根布局
     */
    private void initEditText(View rootView) {
        // 电话输入框
        phoneEditText = (EditText) rootView.findViewById(R.id.activity_personal_info_phone);

        // 手机输入框
        mobileEditText = (EditText) rootView.findViewById(R.id.activity_personal_info_mobile);

        // 邮箱输入框
        emailEditText = (EditText) rootView.findViewById(R.id.activity_personal_info_email);
    }

    /**
     * 初始化保存按钮
     *
     * @param rootView 根布局
     */
    private void initButton(View rootView) {
        // 获取保存按钮
        Button saveButton = (Button) rootView.findViewById(R.id.activity_personal_info_button);

        // 设置点击事件
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 执行保存
                doSavePersonalInfo();
            }
        });
    }

    /**
     * 保存个人信息
     */
    private void doSavePersonalInfo() {
        Log.i(LOG_TAG + "doSavePersonalInfo", "save personal info start");

        // 得到个人信息数据对象
        PersonalInfo info = getSavePersonalInfo();

        if (info == null) {
            // 存在非法输入或未登录
            Log.d(LOG_TAG + "doSavePersonalInfo", "info error");
            return;
        }

        // 新建修改个人信息任务
        ChangePersonalInfo changePersonalInfo = new ChangePersonalInfo();

        // 设置任务回调
        changePersonalInfo.setWorkBackListener(new WorkBack<String>() {
            @Override
            public void doEndWork(boolean state, String data) {
                // 关闭进度条
                progressDialog.cancel();

                if (state) {
                    // 执行成功
                    // 弹出结果提示窗
                    SimpleDialog.showDialog(getActivity(), getString(R.string.personal_info_change_success));
                } else {
                    // 执行失败
                    // 弹出结果提示窗
                    SimpleDialog.showDialog(getActivity(), getString(R.string.personal_info_change_failed));
                }
            }
        });

        // 打开旋转进度条
        startProgressDialog(getString(R.string.personal_info_change_loading));

        // 执行任务
        changePersonalInfo.beginExecute(info);
    }

    /**
     * 装填个人信息数据对象
     *
     * @return 装配好的个人信息数据对象，如果未登录或输入存在不合法则返回null
     */
    private PersonalInfo getSavePersonalInfo() {

        // 判断是否已登录
        if (!LoginStatus.getLoginStatus().isLogin()) {
            Log.i(LOG_TAG + "getSavePersonalInfo", "not login");
            // 弹出提示窗
            SimpleDialog.showDialog(getActivity(), getString(R.string.login_alert));
            return null;
        }

        // 电话
        String phone = phoneEditText.getText().toString();
        Log.i(LOG_TAG + "getSavePersonalInfo", "phone is " + phone);
        // 有输入则校验格式
        if (phone.length() > 0 && !match("\\d{3}-\\d{8}|\\d{4}-\\d{7}", phone)) {
            // 不合法
            Log.d(LOG_TAG + "getSavePersonalInfo", "phone is error");
            // 弹出提示窗
            SimpleDialog.showDialog(getActivity(), getString(R.string.personal_info_phone_error));
            return null;
        }

        // 手机
        String mobile = mobileEditText.getText().toString();
        Log.i(LOG_TAG + "getSavePersonalInfo", "mobile is " + mobile);
        // 有输入则校验格式
        if (mobile.length() > 0 && !match("1[3|5|7|8|][0-9]{9}", mobile)) {
            // 不合法
            Log.d(LOG_TAG + "getSavePersonalInfo", "mobile is error");
            // 弹出提示窗
            SimpleDialog.showDialog(getActivity(), getString(R.string.personal_info_mobile_error));
            return null;
        }

        // 邮箱
        String email = emailEditText.getText().toString();
        Log.i(LOG_TAG + "getSavePersonalInfo", "email is " + email);
        // 有输入则校验格式
        if (email.length() > 0 && !match("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*", email)) {
            // 不合法
            Log.d(LOG_TAG + "getSavePersonalInfo", "email is error");
            // 弹出提示窗
            SimpleDialog.showDialog(getActivity(), getString(R.string.personal_info_email_error));
            return null;
        }

        // 新建个人信息数据对象
        PersonalInfo info = new PersonalInfo();
        // 填充信息
        info.setUserID(LoginStatus.getLoginStatus().getUserID());
        info.setPhone(phone);
        info.setMobile(mobile);
        info.setEmail(email);

        return info;
    }

    /**
     * 打开进度条
     *
     * @param message 要显示的消息
     */
    private void startProgressDialog(String message) {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // 设置提醒
        progressDialog.setMessage(message);
        progressDialog.setCancelable(true);
        progressDialog.show();
    }

    /**
     * 验证输入内容
     *
     * @param regex 正则表达式
     * @param text  要验证的字符串
     *
     * @return 匹配结果
     */
    private boolean match(String regex, String text) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }
}
