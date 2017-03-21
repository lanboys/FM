package com.bing.lan.inke.yingke.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bing.lan.fm.R;
import com.bing.lan.inke.yingke.Constance;
import com.bing.lan.inke.yingke.bean.LivesBean;
import com.bing.lan.inke.yingke.bean.SearchRecomd;
import com.bing.lan.inke.yingke.bean.SearchUserBean;
import com.bing.lan.inke.yingke.bean.SearchUserWarper;
import com.bing.lan.inke.yingke.util.LoggerUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kay on 16/12/10.
 */

public class SearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //推荐类型
    ArrayList<SearchRecomd> recomds;
    //推荐艺人
    ArrayList<SearchUserWarper> userWarpers;

    static final int TYPE = 0;
    static final int TITLE = 1;
    static final int CONTENT = 2;

    LayoutInflater inflater;
    Context context;
    OnSearchClickListener clickListener;

    public void setOnClickListener(OnSearchClickListener listener) {
        this.clickListener = listener;
    }

    public SearchAdapter(ArrayList<SearchRecomd> recomds, ArrayList<SearchUserWarper> userWarpers, Context context) {
        this.recomds = recomds;
        this.userWarpers = userWarpers;
        inflater = LayoutInflater.from(context);
        this.context = context;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int type) {
        if (type == TYPE) {
            //热门类型

            LinearLayout all = new LinearLayout(context);
            all.setOrientation(LinearLayout.VERTICAL);
            //循环添加
            for (int i = 0; i < recomds.size(); i++) {
                View view = inflater.inflate(R.layout.search_item_type, null);
                view.setTag(i);
                all.addView(view);


            }
            TypeHolder holder = new TypeHolder(all);

            return holder;

        } else if (type == TITLE) {
            //中间标题
            TitleHolder holder = new TitleHolder(inflater.inflate(R.layout.search_item_title, null));
            return holder;

        } else {
            //推荐艺人
            ContentHolder holder = new ContentHolder(inflater.inflate(R.layout.search_item_content, null),clickListener);
            return holder;
        }

    }


    @Override
    public int getItemViewType(int position) {
        LoggerUtil.t("position = "+position);
        if (position == 0) {
            return TYPE;
        } else if (position == 1) {
            return TITLE;
        }
        return CONTENT;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof TypeHolder) {

            initType((TypeHolder) viewHolder,clickListener);

        } else if (viewHolder instanceof ContentHolder) {
            SearchUserWarper warper = userWarpers.get(0);
            List<SearchUserBean> beans = warper.getUsers();

            int position = i-2;
            SearchUserBean bean = beans.get(position);
            initContent((ContentHolder) viewHolder,bean);
        } else {
            initTitle((TitleHolder) viewHolder);
        }
    }




    private void initTitle(TitleHolder titleHolder) {
        titleHolder.title.setText(userWarpers.get(0).getTitle());
    }

    private void initType(TypeHolder holder, final OnSearchClickListener clickListener) {
        View view = holder.itemView;

        for (int i = 0; i < recomds.size(); i++) {
            SearchRecomd rec = recomds.get(i);
            List<LivesBean> lives = rec.getLives();

            View itemView = view.findViewWithTag(i);
            SimpleDraweeView one = (SimpleDraweeView) itemView.findViewById(R.id.one);

            one.setImageURI(Constance.getSearchUrl(lives.get(0).getCreator().getPortrait()));
            one.setTag(lives.get(0));
            one.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LivesBean livesBean=(LivesBean) v.getTag();
                    LoggerUtil.t(livesBean.toString());
                    if(clickListener!=null){
                        clickListener.onClickSearchOnePicture(v,1,livesBean);
                    }
                }
            });
            TextView one_num = (TextView) itemView.findViewById(R.id.one_number);
            one_num.setText(lives.get(0).getOnline_users()+"人");

            SimpleDraweeView two = (SimpleDraweeView) itemView.findViewById(R.id.two);
            two.setImageURI(Constance.getSearchUrl(lives.get(1).getCreator().getPortrait()));
            two.setTag(lives.get(1));
            two.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LivesBean livesBean=(LivesBean) v.getTag();
                    LoggerUtil.t(livesBean.toString());
                    if(clickListener!=null){
                        clickListener.onClickSearchOnePicture(v,2,livesBean);
                    }
                }
            });
            TextView two_num = (TextView) itemView.findViewById(R.id.two_number);
            two_num.setText(lives.get(1).getOnline_users()+"人");

            SimpleDraweeView three = (SimpleDraweeView) itemView.findViewById(R.id.three);
            three.setImageURI(Constance.getSearchUrl(lives.get(2).getCreator().getPortrait()));
            three.setTag(lives.get(1));
            three.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LivesBean mLivesBean= (LivesBean)v.getTag();
                    LoggerUtil.t(mLivesBean.toString());
                    if(clickListener!=null){
                        clickListener.onClickSearchOnePicture(v,3,mLivesBean);
                    }
                }
            });
            TextView three_num = (TextView) itemView.findViewById(R.id.three_number);
            three_num.setText(lives.get(2).getOnline_users()+"人");

            TextView title = (TextView) itemView.findViewById(R.id.title);
            title.setText(rec.getTitle());
            TextView more = (TextView) itemView.findViewById(R.id.more);
            more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    LivesBean mLivesBean= (LivesBean)v.getTag();
//                    LoggerUtil.t(mLivesBean.toString());
                    if(clickListener!=null){
                        clickListener.onClickSearchOnePicture(v,4,null);
                    }
                }
            });
        }
    }


    public void initContent(ContentHolder holder, SearchUserBean bean) {
        holder.name.setText(bean.getUser().getNick());
        holder.icon.setImageURI(Constance.getSearchRecMendUrl(bean.getUser().getPortrait()));
        holder.type.setText(bean.getReason());
        int sex = bean.getUser().getSex()==0?R.drawable.global_icon_male:R.drawable.global_icon_female;
        holder.sex.setImageResource(sex);
        holder.rank.setImageResource(getRank(bean.getUser().getLevel()));
        boolean isLive = TextUtils.isEmpty(bean.getLive_id())?false:true;
        if(isLive){
            holder.live.setVisibility(View.VISIBLE);
        }else{
            holder.live.setVisibility(View.GONE);
        }
    }


    private int getRank(int level) {
        if (level < 0 || level > 127) {
            return R.drawable.rank_1;
        }
        int[] rank = {
                R.drawable.rank_1,
                R.drawable.rank_2,
                R.drawable.rank_3,
                R.drawable.rank_4,
                R.drawable.rank_5,
                R.drawable.rank_6,
                R.drawable.rank_7,
                R.drawable.rank_8,
                R.drawable.rank_9,
                R.drawable.rank_10,
                R.drawable.rank_11,
                R.drawable.rank_12,
                R.drawable.rank_13,
                R.drawable.rank_14,
                R.drawable.rank_15,
                R.drawable.rank_16,
                R.drawable.rank_17,
                R.drawable.rank_18,
                R.drawable.rank_19,
                R.drawable.rank_20,
                R.drawable.rank_21,
                R.drawable.rank_22,
                R.drawable.rank_23,
                R.drawable.rank_24,
                R.drawable.rank_25,
                R.drawable.rank_26,
                R.drawable.rank_27,
                R.drawable.rank_28,
                R.drawable.rank_29,
                R.drawable.rank_30,
                R.drawable.rank_31,
                R.drawable.rank_32,
                R.drawable.rank_33,
                R.drawable.rank_34,
                R.drawable.rank_35,
                R.drawable.rank_36,
                R.drawable.rank_37,
                R.drawable.rank_38,
                R.drawable.rank_39,
                R.drawable.rank_40,
                R.drawable.rank_41,
                R.drawable.rank_42,
                R.drawable.rank_43,
                R.drawable.rank_44,
                R.drawable.rank_45,
                R.drawable.rank_46,
                R.drawable.rank_47,
                R.drawable.rank_48,
                R.drawable.rank_49,
                R.drawable.rank_50,
                R.drawable.rank_51,
                R.drawable.rank_52,
                R.drawable.rank_53,
                R.drawable.rank_54,
                R.drawable.rank_55,
                R.drawable.rank_56,
                R.drawable.rank_57,
                R.drawable.rank_58,
                R.drawable.rank_59,
                R.drawable.rank_60,
                R.drawable.rank_61,
                R.drawable.rank_62,
                R.drawable.rank_63,
                R.drawable.rank_64,
                R.drawable.rank_65,
                R.drawable.rank_66,
                R.drawable.rank_67,
                R.drawable.rank_68,
                R.drawable.rank_69,
                R.drawable.rank_70,
                R.drawable.rank_71,
                R.drawable.rank_72,
                R.drawable.rank_73,
                R.drawable.rank_74,
                R.drawable.rank_75,
                R.drawable.rank_76,
                R.drawable.rank_77,
                R.drawable.rank_78,
                R.drawable.rank_79,
                R.drawable.rank_80,
                R.drawable.rank_81,
                R.drawable.rank_82,
                R.drawable.rank_83,
                R.drawable.rank_84,
                R.drawable.rank_85,
                R.drawable.rank_86,
                R.drawable.rank_87,
                R.drawable.rank_88,
                R.drawable.rank_89,
                R.drawable.rank_90,
                R.drawable.rank_91,
                R.drawable.rank_92,
                R.drawable.rank_93,
                R.drawable.rank_94,
                R.drawable.rank_95,
                R.drawable.rank_96,
                R.drawable.rank_97,
                R.drawable.rank_98,
                R.drawable.rank_99,
                R.drawable.rank_100,
                R.drawable.rank_101,
                R.drawable.rank_102,
                R.drawable.rank_103,
                R.drawable.rank_104,
                R.drawable.rank_105,
                R.drawable.rank_106,
                R.drawable.rank_107,
                R.drawable.rank_108,
                R.drawable.rank_109,
                R.drawable.rank_110,
                R.drawable.rank_111,
                R.drawable.rank_112,
                R.drawable.rank_113,
                R.drawable.rank_114,
                R.drawable.rank_115,
                R.drawable.rank_116,
                R.drawable.rank_117,
                R.drawable.rank_118,
                R.drawable.rank_119,
                R.drawable.rank_120,
                R.drawable.rank_121,
                R.drawable.rank_122,
                R.drawable.rank_123,
                R.drawable.rank_124,
                R.drawable.rank_125,
                R.drawable.rank_126,
                R.drawable.rank_127,
                R.drawable.rank_128
        };
        return rank[level - 1];
    }

    @Override
    public int getItemCount() {
        int size = userWarpers.get(0).getUsers().size()+2;

        return size ;
    }

    static class TypeHolder extends RecyclerView.ViewHolder {

        public TypeHolder(View itemView) {
            super(itemView);
        }
    }

    static class TitleHolder extends RecyclerView.ViewHolder {
        TextView title;

        public TitleHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
        }
    }

    static class ContentHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        SimpleDraweeView icon;
        TextView name;
        TextView type;
        ImageView sex;
        ImageView rank;
        ImageView live;
        OnSearchClickListener listener;

        public ContentHolder(View itemView,  OnSearchClickListener listener) {
            super(itemView);
            this.listener=listener;

            icon = (SimpleDraweeView)itemView.findViewById(R.id.icon);
            name = (TextView)itemView.findViewById(R.id.name);
            type = (TextView)itemView.findViewById(R.id.type);
            sex = (ImageView) itemView.findViewById(R.id.sex);
            rank = (ImageView)itemView.findViewById(R.id.rank);
            live = (ImageView)itemView.findViewById(R.id.live);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(listener!=null){
                listener.onClickSearchItem(getAdapterPosition());
            }
        }
    }

    public  interface   OnSearchClickListener{
        public void onClickSearchItem(int index);
        public void onClickSearchOnePicture(View v, int position, LivesBean lives);
    }

}
