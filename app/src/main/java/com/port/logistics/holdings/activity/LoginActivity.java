package com.port.logistics.holdings.activity;
/**
 * Created by 超悟空 on 2015/6/11.
 */

import android.content.Intent;

import com.port.logistics.holdings.R;
import com.port.logistics.holdings.util.StaticValue;

import org.mobile.model.activity.BaseLoginActivity;

/**
 * 登录Activity
 *
 * @author 超悟空
 * @version 1.0 2015/6/11
 * @since 1.0
 */
public class LoginActivity extends BaseLoginActivity {
    @Override
    protected int onActivityLoginLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected int onUserNameEditTextID() {
        return R.id.userName;
    }

    @Override
    protected int onPasswordEditTextID() {
        return R.id.password;
    }

    @Override
    protected String onAppName() {
        return StaticValue.APP_CODE;
    }

    @Override
    protected void onLoginCheckID() {
        setLoginSaveCheckID(R.id.loginSave);
        setLoginAutoCheckID(R.id.loginAuto);
    }

    @Override
    protected void onPostClickLoginButton() {
        // 跳转到主Activity
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
