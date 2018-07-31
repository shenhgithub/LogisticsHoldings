package com.port.logistics.holdings.fragment;
/**
 * Created by 超悟空 on 2015/6/12.
 */

import com.port.logistics.holdings.R;
import com.port.logistics.holdings.fragment.base.BaseGridFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 承载功能项的Fragment片段
 *
 * @author 超悟空
 * @version 1.0 2015/6/12
 * @since 1.0
 */
public class FunctionListFragment extends BaseGridFragment {

    @Override
    protected int onLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected int onGridViewId() {
        return R.id.function_gridView;
    }

    @Override
    protected List<String> onFunctionCodeList() {

        // 全部功能代码数组
        String[] functionCodes = getResources().getStringArray(R.array.grid_item_function_code_list);
        // 主页功能代码
        List<String> mainFunctionList = new ArrayList<>();
        for (String functionCode : functionCodes) {
            // 仅添加首页功能项
            if (functionCode.startsWith("MF")) {
                mainFunctionList.add(functionCode);
            }
        }

        return mainFunctionList;
    }

}
