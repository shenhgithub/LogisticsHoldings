package com.port.logistics.holdings.factory;
/**
 * Created by 超悟空 on 2015/6/12.
 */

import android.support.v4.app.Fragment;

import com.port.logistics.holdings.fragment.analysis.AssetDebtFragment;
import com.port.logistics.holdings.fragment.analysis.ComprehensiveAnalysisFragment;
import com.port.logistics.holdings.fragment.analysis.ContainerAmountFragment;
import com.port.logistics.holdings.fragment.analysis.FundsStockFragment;
import com.port.logistics.holdings.fragment.analysis.IncomeCostFragment;
import com.port.logistics.holdings.fragment.analysis.ProfitFragment;
import com.port.logistics.holdings.fragment.analysis.ScatteredGroceriesAmountFragment;
import com.port.logistics.holdings.fragment.analysis.ShippingAmountFragment;

/**
 * 功能片段工厂
 *
 * @author 超悟空
 * @version 1.0 2015/6/12
 * @since 1.0
 */
public class FunctionFragmentFactory {

    /**
     * 创建功能片段
     *
     * @param functionCode 要创建的功能编号
     *
     * @return fragment对象
     */
    public static Fragment CreateFunctionFragment(String functionCode) {
        switch (functionCode) {
            case "MF0001":
                // 综合分析
                return new ComprehensiveAnalysisFragment();

            case "CA0001":
                // 资金存量
                return new FundsStockFragment();
            case "CA0002":
                // 收入、成本
                return new IncomeCostFragment();
            case "CA0003":
                // 资产、负债
                return new AssetDebtFragment();
            case "CA0004":
                // 利润
                return new ProfitFragment();
            case "CA0005":
                // 集装箱代理量
                return new ContainerAmountFragment();
            case "CA0006":
                // 散杂货代理量
                return new ScatteredGroceriesAmountFragment();
            case "CA0007":
                // 船舶代理量
                return new ShippingAmountFragment();
            default:
                return null;
        }
    }
}
