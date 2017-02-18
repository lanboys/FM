package com.bing.lan.comm.utils;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * 根据数据动态计算listView高度,实现listView不能滑动
 * 必须在加载数据后进行测量
 */
public class FixedViewUtil {

    public static void setGridViewHeightBasedOnChildren(GridView gv, int col) {
        // 获取listview的adapter
        ListAdapter listAdapter = gv.getAdapter();
        if (listAdapter == null) {
            return;
        }
        // 固定列宽，有多少列
        int totalHeight = 0;
        // i每次加4，相当于listAdapter.getCount()小于等于4时 循环一次，计算一次item的高度，
        // listAdapter.getCount()小于等于8时计算两次高度相加
        for (int i = 0; i < listAdapter.getCount(); i += col) {
            // 获取listview的每一个item
            View listItem = listAdapter.getView(i, null, gv);
            listItem.measure(0, 0);
            // 获取item的高度和
            totalHeight += listItem.getMeasuredHeight();
            totalHeight += gv.getVerticalSpacing();
            if (i == listAdapter.getCount() - 1) {
                totalHeight += gv.getVerticalSpacing();
            }
        }
        // 获取listview的布局参数
        ViewGroup.LayoutParams params = gv.getLayoutParams();
        // 设置高度
        params.height = totalHeight;
        // 设置margin
        ((MarginLayoutParams) params).setMargins(10, 10, 10, 10);
        // 设置参数
        gv.setLayoutParams(params);
    }

    public static void setListViewHeightBasedOnChildren(ListView lv) {
        ListAdapter listAdapter = lv.getAdapter();
        int listViewHeight = 0;
        int adaptCount = listAdapter.getCount();
        for (int i = 0; i < adaptCount; i++) {
            View temp = listAdapter.getView(i, null, lv);
            temp.measure(0, 0);
            listViewHeight += temp.getMeasuredHeight();
        }
        LayoutParams layoutParams = lv.getLayoutParams();
        layoutParams.width = LayoutParams.MATCH_PARENT;
        layoutParams.height = listViewHeight;
        lv.setLayoutParams(layoutParams);
    }
}
