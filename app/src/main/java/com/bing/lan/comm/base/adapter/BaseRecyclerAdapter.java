package com.bing.lan.comm.base.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author 赵坤
 * @email artzok@163.com
 */
public abstract class BaseRecyclerAdapter<T> extends
        RecyclerView.Adapter<BaseRecyclerViewHolder<T>> {

    /*数据集合*/
    private List<T> data;

    /*单击事件*/
    private OnItemClickListener mListener;

    /**
     * 返回item的布局资源id
     */
    abstract int getItemLayoutResId();

    /**
     * 返回BaseViewHolder的子类
     */
    abstract BaseRecyclerViewHolder<T> createViewHolder(View itemView, int type);

    /**
     * 设置事件单击监听器
     */
    public void setItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View itemView = inflater.inflate(getItemLayoutResId(), parent, false);
        final BaseRecyclerViewHolder<T> holder = createViewHolder(itemView, viewType);
        if (mListener != null) {
            itemView.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mListener.onClick(itemView, holder.getLayoutPosition());
                        }
                    });
        }
        return holder;
    }

    @Override
    public int getItemViewType(int position) {
        return ITEM_TYPE_NORMAL;

    }
    public static final int ITEM_TYPE_NORMAL = 0;
    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        holder.fillData(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public void setDataAndRefresh(List<T> data) {
        this.data = data;
        notifyDataSetChanged();
        notifyItemRangeChanged(0, getItemCount() - 1);
    }

    public T getItem(int position) {
        return data.get(position);
    }

    /*事件监听接口*/
    public interface OnItemClickListener {

        void onClick(View v, int position);
    }
}

