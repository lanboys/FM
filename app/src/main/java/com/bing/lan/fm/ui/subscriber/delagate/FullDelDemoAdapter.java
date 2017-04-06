package com.bing.lan.fm.ui.subscriber.delagate;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bing.lan.comm.utils.musicplay.Music;
import com.bing.lan.fm.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;

import java.util.List;

public class FullDelDemoAdapter extends RecyclerView.Adapter<FullDelDemoAdapter.FullDelDemoVH> {

    private Context mContext;
    private LayoutInflater mInfalter;
    private List<Music> mDatas;
    private onSwipeListener mOnSwipeListener;

    public FullDelDemoAdapter(Context context, List<Music> mDatas) {
        mContext = context;
        mInfalter = LayoutInflater.from(context);
        this.mDatas = mDatas;
    }

    @Override
    public FullDelDemoVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FullDelDemoVH(mInfalter.inflate(R.layout.subscrilber_item_adapter, parent, false));
    }

    @Override
    public void onBindViewHolder(final FullDelDemoVH holder, final int position) {
        //这句话关掉IOS阻塞式交互效果 并依次打开左滑右滑
        ((SwipeMenuLayout) holder.itemView).setIos(false).setLeftSwipe(true);

        holder.ivCoverImage.setImageURI(mDatas.get(position).getCoverSmall());
        holder.tvTrackTitleName.setText(mDatas.get(position).getTitle());
        holder.tvTrackSubtitle.setText(mDatas.get(position).getNickname());
        holder.tvTracksCounts.setText("播放时间" + mDatas.get(position).getLastPlayPosition());

        //验证长按
        holder.llChildContainer.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(mContext, "longclig", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mOnSwipeListener) {
                    //如果删除时，不使用mAdapter.notifyItemRemoved(pos)，则删除没有动画效果，
                    //且如果想让侧滑菜单同时关闭，需要同时调用 ((CstSwipeDelMenu) holder.itemView).quickClose();
                    //((CstSwipeDelMenu) holder.itemView).quickClose();
                    mOnSwipeListener.onDel(position);
                    //                    mOnSwipeListener.onDel(holder.getAdapterPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return null != mDatas ? mDatas.size() : 0;
    }

    public onSwipeListener getOnDelListener() {
        return mOnSwipeListener;
    }

    public void setOnDelListener(onSwipeListener mOnDelListener) {
        this.mOnSwipeListener = mOnDelListener;
    }

    /**
     * 和Activity通信的接口
     */
    public interface onSwipeListener {

        void onDel(int pos);
    }

    class FullDelDemoVH extends RecyclerView.ViewHolder {

        private LinearLayout llChildContainer;

        private SimpleDraweeView ivCoverImage;
        private TextView tvTrackTitleName;
        private TextView tvTrackSubtitle;
        private TextView tvTracksCounts;

        private TextView btnDelete;

        public FullDelDemoVH(View itemView) {
            super(itemView);
            llChildContainer = (LinearLayout) itemView.findViewById(R.id.ll_child_container);
            ivCoverImage = (SimpleDraweeView) itemView.findViewById(R.id.iv_cover_image);
            tvTrackTitleName = (TextView) itemView.findViewById(R.id.tv_track_Title_name);
            tvTrackSubtitle = (TextView) itemView.findViewById(R.id.tv_track_subtitle);
            tvTracksCounts = (TextView) itemView.findViewById(R.id.tv_tracksCounts);
            btnDelete = (TextView) itemView.findViewById(R.id.btnDelete);
        }
    }
}

