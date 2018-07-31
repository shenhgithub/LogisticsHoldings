package com.port.logistics.holdings.work;
/**
 * Created by 超悟空 on 2015/6/15.
 */

import android.util.Log;

import com.port.logistics.holdings.data.PasswordChangeData;

import org.mobile.model.work.WorkModel;
import org.mobile.network.communication.ICommunication;
import org.mobile.network.factory.CommunicationFactory;
import org.mobile.network.factory.NetworkType;
import org.mobile.util.LoginStatus;

/**
 * 修改密码任务类
 *
 * @author 超悟空
 * @version 1.0 2015/6/15
 * @since 1.0
 */
public class ChangePassword extends WorkModel<String, String> {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "ChangePassword.";

    @Override
    protected boolean onDoWork(String... parameters) {
        if (parameters == null || parameters.length < 2) {
            // 数据异常
            Log.d(LOG_TAG + "onDoWork", "password is null");
            return false;
        }

        // 新建http get请求的通讯工具
        ICommunication communication = CommunicationFactory.Create(NetworkType.HTTP_GET);

        // 新建密码数据对象
        PasswordChangeData data = new PasswordChangeData();

        // 传入参数
        data.setUserID(LoginStatus.getLoginStatus().getUserID());
        data.setOldPassword(parameters[0]);
        data.setNewPassword(parameters[1]);

        // 设置调用的方法名
        communication.setTaskName("ChangePassword.aspx");
        Log.i(LOG_TAG + "onDoWork", "task name is ChangePassword.aspx");

        // 发送请求
        //noinspection unchecked
        communication.Request(data.serialization());

        // 解析数据
        if (data.parse(communication.Response())) {
            // 执行成功
            Log.i(LOG_TAG + "onDoWork", "change password success");
            // 设置回显消息
            setResult(data.getMessage());
            return true;
        } else {
            // 执行失败
            Log.i(LOG_TAG + "onDoWork", "change password failed");
            // 设置回显消息
            setResult(data.getMessage());
            return false;
        }
    }
}
