package com.port.logistics.holdings.work;
/**
 * Created by 超悟空 on 2015/6/15.
 */

import android.util.Log;

import com.port.logistics.holdings.data.PersonalInfo;
import com.port.logistics.holdings.data.PersonalInfoData;

import org.mobile.model.work.WorkModel;
import org.mobile.network.communication.ICommunication;
import org.mobile.network.factory.CommunicationFactory;
import org.mobile.network.factory.NetworkType;
import org.mobile.util.LoginStatus;

/**
 * 得到个人信息
 *
 * @author 超悟空
 * @version 1.0 2015/6/15
 * @since 1.0
 */
public class PullPersonalInfo extends WorkModel<Void, PersonalInfo> {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "PullPersonalInfo.";

    @Override
    protected boolean onDoWork(Void... parameters) {
        // 新建http get请求的通讯工具
        ICommunication communication = CommunicationFactory.Create(NetworkType.HTTP_GET);

        // 新建个人信息数据对象
        PersonalInfoData data = new PersonalInfoData();
        // 传入参数
        data.setUserID(LoginStatus.getLoginStatus().getUserID());

        // 设置调用的方法名
        communication.setTaskName("GetPersonDetailData.aspx");
        Log.i(LOG_TAG + "onDoWork", "task name is GetPersonDetailData.aspx");

        // 发送请求
        //noinspection unchecked
        communication.Request(data.serialization());

        // 解析数据
        if (data.parse(communication.Response())) {
            // 执行成功
            Log.i(LOG_TAG + "onDoWork", "personal info download success");
            // 设置结果数据
            setResult(data.getPersonalInfo());
            return true;
        } else {
            // 执行成功
            Log.i(LOG_TAG + "onDoWork", "personal info download failed");
            return false;
        }
    }
}
