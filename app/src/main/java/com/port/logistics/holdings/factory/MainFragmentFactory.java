package com.port.logistics.holdings.factory;
/**
 * Created by 超悟空 on 2015/6/15.
 */

import android.support.v4.app.Fragment;

import com.port.logistics.holdings.R;
import com.port.logistics.holdings.fragment.FunctionListFragment;
import com.port.logistics.holdings.fragment.PasswordChangeFragment;
import com.port.logistics.holdings.fragment.PersonalInfoFragment;

/**
 * 主Activity加载的片段的工厂
 *
 * @author 超悟空
 * @version 1.0 2015/6/15
 * @since 1.0
 */
public class MainFragmentFactory {

    /**
     * 功能标签枚举接口
     */
    public interface FragmentTagEnum {
        /**
         * 主界面功能页标签，默认为启动主页
         */
        String MAIN_FUNCTION = "main_function";

        /**
         * 主界面密码修改标签
         */
        String PASSWORD_CHANGE = "password_change";

        /**
         * 主界面个人信息管理标签
         */
        String PERSONAL_INFO = "personal_info";
    }

    /**
     * 创建指定的片段对象
     *
     * @param tag 标签字符串{@link FragmentTagEnum}
     *
     * @return 新建的片段对象
     */
    public static Fragment CreateFragment(String tag) {
        switch (tag) {
            case FragmentTagEnum.MAIN_FUNCTION:
                // 主功能界面
                return new FunctionListFragment();
            case FragmentTagEnum.PASSWORD_CHANGE:
                // 密码修改
                return new PasswordChangeFragment();
            case FragmentTagEnum.PERSONAL_INFO:
                // 个人信息管理
                return new PersonalInfoFragment();
            default:
                return null;
        }
    }

    /**
     * 获取指定片段的标题资源ID
     *
     * @param tag 标签字符串{@link FragmentTagEnum}
     *
     * @return 标题资源ID
     */
    public static int getFragmentTitle(String tag) {
        switch (tag) {
            case FragmentTagEnum.MAIN_FUNCTION:
                // 主功能界面
                return R.string.app_name;
            case FragmentTagEnum.PASSWORD_CHANGE:
                // 密码修改
                return R.string.function_password_change_title;
            case FragmentTagEnum.PERSONAL_INFO:
                // 个人信息管理
                return R.string.function_personal_info_title;
            default:
                return 0;
        }
    }
}
