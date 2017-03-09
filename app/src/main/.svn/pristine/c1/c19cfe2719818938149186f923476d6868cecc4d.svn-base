package com.bing.lan.comm.base.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.bing.lan.comm.base.BaseViewHolder;
import com.bing.lan.comm.utils.LogUtil;

import java.util.List;

/**
 * Created by 520 on 2016/12/5.
 */

public abstract class MyBaseAdapter<T> extends BaseAdapter {

    private static final String TAG = "-->520it";

    public static final int ITEM_TYPE_NORMAL = 0;       // Plain item flag
    private final Object mLock = new Object();
    protected List<T> mData;
    protected Context mContext;
    protected LogUtil log = LogUtil.getLogUtil(getClass(), 1);

    public MyBaseAdapter() {
    }

    public MyBaseAdapter(Context context) {
        this(context, null);
    }

    public MyBaseAdapter(List<T> data) {
        this(null, data);
    }

    public MyBaseAdapter(Context context, List<T> data) {
        this.mContext = context;
        this.mData = data;
    }

    public boolean insert(List<T> data) {
        synchronized (mLock) {
            if (mData != null) {
                mData.addAll(data);
                notifyDataSetChanged();
                return true;
            } else {
                Log.w(TAG, "MyBaseAdapter.insert:::添加数据失败");
                return false;
            }
        }
    }

    public boolean insert(@Nullable T object, int index) {
        synchronized (mLock) {
            if (mData != null) {
                mData.add(index, object);
                notifyDataSetChanged();
                return true;
            } else {
                Log.w(TAG, "MyBaseAdapter.insert:::添加数据失败");
                return false;
            }
        }
    }

    public List<T> getData() {
        return mData;
    }

    public void setDataAndRefresh(List<T> data) {
        if (mData != null) {
            mData.clear();
            mData.addAll(data);
        } else {
            mData = data;
        }
        notifyDataSetChanged();
    }

    public void clearAllData() {
        if (mData != null) {
            mData.clear();
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public T getItem(int position) {
        return mData.get(position);
    }

    @Override
    public int getViewTypeCount() {
        return 1;
        // return super.getViewTypeCount();
    }

    @Override
    public int getItemViewType(int position) {
        return ITEM_TYPE_NORMAL;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        BaseViewHolder holder;
        if (convertView != null) {
            holder = (BaseViewHolder) convertView.getTag();
        } else {
            int itemViewType = getItemViewType(position);
            int itemLayoutId = getItemLayoutId(itemViewType);
            convertView = LayoutInflater.from(parent.getContext()).inflate(itemLayoutId, parent, false);
            //将convertView传进去修改数据
            holder = getViewHolder(itemViewType, convertView);
            convertView.setTag(holder);
            // convertView= holder.convertView;
        }
        holder.refreshViewData(mData.get(position), position);
        return convertView;
    }

    public abstract int getItemLayoutId(int itemViewType);

    public abstract BaseViewHolder<T> getViewHolder(int itemViewType, View convertView);


}

// @Override
// public View getView(int position, View convertView, ViewGroup parent) {
//     ViewHolder holder = null;
//     if (convertView != null) {
//         holder = (ViewHolder) convertView.getTag();
//     } else {
//         convertView = mInflater.inflate(R.layout.lv_item_xxx, parent, false);
//         holder = new ViewHolder(convertView);
//         convertView.setTag(holder);
//     }
//     initViewHolderDatas(holder, position);
//     return convertView;
// }
//
// private void initViewHolderDatas(ViewHolder holder, int position) {
//     //初始化数据
// }

// @Override
// public View getView(int position, View convertView, ViewGroup parent) {
//
//     BaseViewHolder holder;
//     if (convertView != null) {
//         holder = (BaseViewHolder) convertView.getTag();
//     } else {
//         holder = getViewHolder();
//     }
//     holder.refreshViewData(mData.get(position));
//     return holder.convertView;
// }

// public abstract BaseViewHolder getViewHolder();