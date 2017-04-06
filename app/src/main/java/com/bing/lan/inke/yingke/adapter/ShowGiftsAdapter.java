package com.bing.lan.inke.yingke.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bing.lan.fm.R;
import com.bing.lan.inke.yingke.Constance;
import com.bing.lan.inke.yingke.bean.GiftReceive;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;


public class ShowGiftsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "ShowGiftsAdapter";
    private final LayoutInflater inflater;
    private List<GiftReceive> dates;


    public ShowGiftsAdapter(Context context) {
        this.dates = new ArrayList<>();
         inflater = LayoutInflater.from(context);
    }

    public void addData(List<GiftReceive> dates){
        for(GiftReceive gift:dates){
            this.dates.add(gift);
            notifyItemInserted(dates.size()-1);
        }
    }

    public void removeData(int index){
        this.dates.remove(index);
        notifyItemRemoved(index);
    }

    /**
     * 更新dates中的数据
     */
    public void updata() {
        long currentTimeMillis = System.currentTimeMillis();
        int size = this.dates.size();
        if(size>0) {
            if (currentTimeMillis -this.dates.get(0).getTimeTemp() > 3000){
                this.dates.remove(0);
                notifyItemRemoved(0);
            }

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GiftItemViewHolder(inflater.inflate(R.layout.item_gifts,null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        GiftItemViewHolder giftHolder=(GiftItemViewHolder)holder;
        GiftReceive gift=dates.get(position);
        if(gift!=null){

            String iconName = gift.getIcon();
            String[] imageNameAndServer = Constance.getImageNameAndServer(iconName);
            if(imageNameAndServer!=null&&imageNameAndServer.length==2){
                String imgUrl = Constance.getHotSmallUrl(imageNameAndServer[0], imageNameAndServer[1]);
                giftHolder.img_icon.setImageURI(imgUrl);
            }else{
                /**设计默认图片*/
//                inputViewHolder.imgCreator.setImageURI(imgUrl);
            }

            String imgName = gift.getImage();
            String[] imageNameAndServer2 = Constance.getImageNameAndServer(imgName);
            if(imageNameAndServer2!=null&&imageNameAndServer2.length==2){
                String imgUrl = Constance.getHotSmallUrl(imageNameAndServer2[0], imageNameAndServer2[1]);
                giftHolder.img_gift.setImageURI(imgUrl);
            }else{
                /**设计默认图片*/
//                inputViewHolder.imgCreator.setImageURI(imgUrl);
            }
            Log.d(TAG,"iconName="+iconName);
            Log.d(TAG,"getImage="+gift.getImage());

            giftHolder.tv_name.setText(gift.getUser());
            giftHolder.tv_comment.setText(gift.getName());
            giftHolder.tv_num.setText("X"+gift.getCounts());
        }
    }

    @Override
    public int getItemCount() {
        return dates.size();
    }




    public Object getDataByIndex(int index){
        GiftReceive bean =  dates.get(index);
        return bean;
    }



   static class  GiftItemViewHolder extends RecyclerView.ViewHolder{

       TextView tv_name,tv_comment,tv_num;
       SimpleDraweeView img_icon,img_gift;
        public GiftItemViewHolder(View itemView) {
            super(itemView);
            img_icon=(SimpleDraweeView)itemView.findViewById(R.id.img_icon);
            tv_name=(TextView)itemView.findViewById(R.id.tv_name);
            tv_comment=(TextView)itemView.findViewById(R.id.tv_comment);
            img_gift=(SimpleDraweeView)itemView.findViewById(R.id.img_gift);
            tv_num=(TextView)itemView.findViewById(R.id.tv_num);
        }
   }


}
