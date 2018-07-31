package com.port.logistics.holdings.adapter;
/**
 * Created by 超悟空 on 2015/6/12.
 */

import android.content.res.TypedArray;

import com.port.logistics.holdings.R;

import org.mobile.util.ContextUtil;

import java.util.Hashtable;

/**
 * 功能索引
 *
 * @author 超悟空
 * @version 1.0 2015/6/12
 * @since 1.0
 */
public class FunctionIndex {

    /**
     * 自身静态对象
     */
    private static FunctionIndex functionIndex = new FunctionIndex();

    /**
     * 存放功能<编号,名称>的列表
     */
    private Hashtable<String, String> functionNameTable = new Hashtable<>();

    /**
     * 存放功能<名称,编号>的列表
     */
    private Hashtable<String, String> functionCodeTable = new Hashtable<>();

    /**
     * 存放功能<编号,图标>的列表
     */
    private Hashtable<String, Integer> functionImageTable = new Hashtable<>();

    /**
     * 私有构造函数
     */
    private FunctionIndex() {
        // 初始化功能<编号,名称>列表
        initFunctionNameTable();
        // 初始化功能<名称,编号>列表
        initFunctionCodeTable();
        // 初始化功能<编号,图标>列表
        initFunctionImageTable();
    }

    /**
     * 初始化功能<编号,名称>列表
     */
    private void initFunctionNameTable() {
        functionNameTable.clear();
        // 功能编号列表
        String[] functionCodeList = loadFunctionCodeList();
        // 功能名称列表
        String[] functionNameList = loadFunctionNameList();

        // 填充表
        for (int i = 0; i < functionCodeList.length; i++) {
            functionNameTable.put(functionCodeList[i], functionNameList[i]);
        }
    }

    /**
     * 初始化功能<名称,编号>列表
     */
    private void initFunctionCodeTable() {
        functionCodeTable.clear();
        // 功能编号列表
        String[] functionCodeList = loadFunctionCodeList();
        // 功能名称列表
        String[] functionNameList = loadFunctionNameList();

        // 填充表
        for (int i = 0; i < functionCodeList.length; i++) {
            functionCodeTable.put(functionNameList[i], functionCodeList[i]);
        }
    }

    /**
     * 初始化功能<编号,图标>列表
     */
    private void initFunctionImageTable() {
        functionImageTable.clear();
        // 功能编号列表
        String[] functionCodeList = loadFunctionCodeList();
        // 功能图标列表
        int[] functionImageList = loadFunctionDrawableLis();

        // 填充表
        for (int i = 0; i < functionCodeList.length; i++) {
            functionImageTable.put(functionCodeList[i], functionImageList[i]);
        }
    }

    /**
     * 加载功能编号列表
     *
     * @return 编号数组
     */
    private String[] loadFunctionCodeList() {
        return ContextUtil.getContext().getResources().getStringArray(R.array.grid_item_function_code_list);
    }

    /**
     * 加载功能名称列表
     *
     * @return 名称数组
     */
    private String[] loadFunctionNameList() {
        return ContextUtil.getContext().getResources().getStringArray(R.array.grid_item_function_name_list);
    }

    /**
     * 加载功能图标列表
     *
     * @return 图标ID数组
     */
    private int[] loadFunctionDrawableLis() {
        // 资源类型数组
        TypedArray images = ContextUtil.getContext().getResources().obtainTypedArray(R.array.grid_item_function_image_list);
        // 存放图片对象的数组
        int[] drawables = new int[images.length()];
        // 填充图片数组
        for (int i = 0; i < images.length(); i++) {
            drawables[i] = images.getResourceId(i, R.mipmap.ic_launcher);
        }
        // 释放资源
        images.recycle();
        return drawables;
    }

    /**
     * 获取功能索引对象
     *
     * @return 功能索引对象实例
     */
    public static FunctionIndex getFunctionIndex() {
        return functionIndex;
    }

    /**
     * 通过功能编号获取功能名称
     *
     * @param functionCode 功能编号
     *
     * @return 功能名称，不存在返回null
     */
    public String getFunctionName(String functionCode) {
        return functionNameTable.get(functionCode);
    }

    /**
     * 通过功能名称获取功能编号
     *
     * @param functionName 功能名称
     *
     * @return 功能编号，不存在返回null
     */
    public String getFunctionCode(String functionName) {
        return functionCodeTable.get(functionName);
    }

    /**
     * 通过功能编号获取功能图标
     *
     * @param functionCode 功能编号
     *
     * @return 功能图标，不存在返回null
     */
    public int getFunctionImage(String functionCode) {
        return functionImageTable.get(functionCode);
    }

}
