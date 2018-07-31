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

import com.port.logistics.holdings.R;
import com.port.logistics.holdings.work.ChangePassword;

import org.mobile.common.dialog.SimpleDialog;
import org.mobile.model.work.WorkBack;
import org.mobile.util.ConfigUtil;
import org.mobile.util.LoginStatus;

/**
 * 密码修改片段
 *
 * @author 超悟空
 * @version 1.0 2015/6/15
 * @since 1.0
 */
public class PasswordChangeFragment extends Fragment {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "PasswordChangeFragment.";

    /**
     * 进度条
     */
    private ProgressDialog progressDialog = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // 根布局
        View rootView = inflater.inflate(R.layout.fragment_password_change, container, false);

        // 加载界面
        initView(rootView);

        return rootView;
    }

    /**
     * 初始化控件
     *
     * @param rootView 根布局
     */
    private void initView(View rootView) {

        // 初始化修改确认按钮
        initButton(rootView);
    }

    /**
     * 初始化修改确认按钮
     *
     * @param rootView 根布局
     */
    private void initButton(final View rootView) {

        // 获取确认按钮
        Button changePasswordButton = (Button) rootView.findViewById(R.id.activity_password_change_button);

        // 设置点击事件
        changePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 执行修改
                doChangePassword(rootView);
            }
        });
    }

    /**
     * 确认修改密码操作
     *
     * @param rootView 根布局
     */
    private void doChangePassword(View rootView) {
        Log.i(LOG_TAG + "doChangePassword", "change password start");

        // 获取原密码输入框
        EditText oldPasswordEditText = (EditText) rootView.findViewById(R.id.activity_password_change_old_password);

        // 获取新密码输入框
        EditText newPasswordEditText = (EditText) rootView.findViewById(R.id.activity_password_change_new_password);

        // 获取二次确认密码框
        EditText reenterPasswordEditText = (EditText) rootView.findViewById(R.id.activity_password_change_reenter_password);

        // 得到原密码字符串
        String oldPassword = oldPasswordEditText.getText().toString();
        Log.i(LOG_TAG + "doChangePassword", "old password is " + oldPassword);

        // 得到新密码字符串
        final String newPassword = newPasswordEditText.getText().toString();
        Log.i(LOG_TAG + "doChangePassword", "new password is " + newPassword);

        // 得到二次确认密码字符串
        String reenterPassword = reenterPasswordEditText.getText().toString();
        Log.i(LOG_TAG + "doChangePassword", "reenter password is " + reenterPassword);

        // 密码合法性校验
        if (!checkPassword(oldPassword, newPassword, reenterPassword)) {
            Log.i(LOG_TAG + "doChangePassword", "password is error");
            return;
        }

        // 新建修改密码任务
        ChangePassword changePassword = new ChangePassword();

        // 设置回调监听
        changePassword.setWorkBackListener(new WorkBack<String>() {
            @Override
            public void doEndWork(boolean state, String data) {
                // 关闭进度条
                progressDialog.cancel();

                // 弹出结果提示窗
                SimpleDialog.showDialog(getActivity(), data);

                if (state && ConfigUtil.getInstance().isLoginSave()) {
                    // 修改成功且用户需要保存密码
                    // 保存新密码
                    ConfigUtil.getInstance().setPassword(newPassword);
                    ConfigUtil.getInstance().Save();
                }
            }
        });

        // 打开旋转进度条
        startProgressDialog();

        // 执行密码修改
        changePassword.beginExecute(oldPassword, newPassword);
    }

    /**
     * 密码合法性校验
     *
     * @param oldPassword     原密码
     * @param newPassword     新密码
     * @param reenterPassword 二次确认密码
     *
     * @return 合法返回true
     */
    private boolean checkPassword(String oldPassword, String newPassword, String reenterPassword) {

        // 判断是否已登录
        if (!LoginStatus.getLoginStatus().isLogin()) {
            Log.i(LOG_TAG + "checkPassword", "not login");
            // 弹出提示窗
            SimpleDialog.showDialog(getActivity(), getString(R.string.login_alert));
            return false;
        }

        // 判断是否输入原密码
        if (oldPassword.length() == 0) {
            Log.i(LOG_TAG + "checkPassword", "old password is null");
            // 弹出提示窗
            SimpleDialog.showDialog(getActivity(), getString(R.string.password_change_no_old_password));
            return false;
        }

        // 判断是否输入新密码
        if (newPassword.length() == 0) {
            Log.i(LOG_TAG + "checkPassword", "new password is null");
            // 弹出提示窗
            SimpleDialog.showDialog(getActivity(), getString(R.string.password_change_no_new_password));
            return false;
        }

        // 判断是否输入二次确认密码
        if (reenterPassword.length() == 0) {
            Log.i(LOG_TAG + "checkPassword", "reenter password is null");
            // 弹出提示窗
            SimpleDialog.showDialog(getActivity(), getString(R.string.password_change_no_reenter_password));
            return false;
        }

        // 判断新旧密码是否相同
        if (newPassword.equals(oldPassword)) {
            // 新旧密码相同
            Log.i(LOG_TAG + "checkPassword", "same old password new password");
            // 弹出提示窗
            SimpleDialog.showDialog(getActivity(), getString(R.string.password_change_same));
            return false;
        }

        // 判断两次新密码是否相同
        if (!newPassword.equals(reenterPassword)) {
            // 两次输入不相同
            Log.i(LOG_TAG + "checkPassword", "double password is different");
            // 弹出提示窗
            SimpleDialog.showDialog(getActivity(), getString(R.string.password_change_different));
            return false;
        }

        return true;
    }

    /**
     * 打开进度条
     */
    private void startProgressDialog() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // 设置提醒
        progressDialog.setMessage(getString(R.string.password_change_loading));
        progressDialog.setCancelable(true);
        progressDialog.show();
    }

}
