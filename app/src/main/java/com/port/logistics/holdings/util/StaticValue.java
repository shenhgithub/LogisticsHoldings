package com.port.logistics.holdings.util;
/**
 * Created by 超悟空 on 2015/6/11.
 */

/**
 * 存放应用使用的全局静态常量
 *
 * @author 超悟空
 * @version 1.0 2015/6/11
 * @since 1.0
 */
public interface StaticValue {
    /**
     * 本应用编号
     */
    String APP_CODE = "WLKG";

    /**
     * 用于在Intent中传递功能标志
     */
    String FUNCTION_FRAGMENT_MARK = "function_fragment_mark";

    /**
     * http get请求根地址
     */
    String HTTP_GET_ROOT_URL = "http://168.100.1.218/wlkg/Service/";
}
