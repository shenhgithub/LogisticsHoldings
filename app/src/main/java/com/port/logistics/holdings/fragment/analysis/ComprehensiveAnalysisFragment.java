package com.port.logistics.holdings.fragment.analysis;
/**
 * Created by 超悟空 on 2015/6/17.
 */

import com.port.logistics.holdings.R;
import com.port.logistics.holdings.fragment.base.BaseGridFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 综合分析功能列表
 *
 * @author 超悟空
 * @version 1.0 2015/6/17
 * @since 1.0
 */
public class ComprehensiveAnalysisFragment extends BaseGridFragment {

    @Override
    protected int onLayoutId() {
        return R.layout.fragment_only_grid_view;
    }

    @Override
    protected int onGridViewId() {
        return R.id.fragment_only_grid_gridView;
    }

    @Override
    protected List<String> onFunctionCodeList() {
        // 全部功能代码数组
        String[] functionCodes = getResources().getStringArray(R.array.grid_item_function_code_list);
        // 主页功能代码
        List<String> mainFunctionList = new ArrayList<>();
        for (String functionCode : functionCodes) {
            // 仅添加首页功能项
            if (functionCode.startsWith("CA")) {
                mainFunctionList.add(functionCode);
            }
        }

        return mainFunctionList;
    }
}
