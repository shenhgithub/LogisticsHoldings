package com.port.logistics.holdings.fragment;
/**
 * Created by 超悟空 on 2015/6/17.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.port.logistics.holdings.R;

/**
 * 功能加载失败显示的fragment
 *
 * @author 超悟空
 * @version 1.0 2015/6/17
 * @since 1.0
 */
public class FunctionErrorFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_load_error, container, false);
    }
}
