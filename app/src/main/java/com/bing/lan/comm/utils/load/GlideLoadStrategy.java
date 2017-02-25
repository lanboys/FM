package com.bing.lan.comm.utils.load;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.facebook.drawee.controller.ControllerListener;
import com.facebook.fresco.helper.listener.IResult;
import com.facebook.imagepipeline.image.ImageInfo;

/**
 * @author 蓝兵
 * @time 2017/2/23  22:43
 */
public class GlideLoadStrategy implements IBaseLoaderStrategy {

    @Override
    public void loadImage(Context ctx, ImageView imageView, String url) {

    }

    @Override
    public void loadImage(Context ctx, ImageView imageView, String url, int reqWidth, int reqHeight) {

    }

    @Override
    public void loadImage(Context context, String url, int reqWidth, int reqHeight, IResult<Bitmap> loadImageResult) {

    }

    @Override
    public void loadImage(Context ctx, ImageView imageView, String url, ControllerListener<ImageInfo> controllerListener) {

    }

    @Override
    public void loadSmallImage(Context ctx, ImageView imageView, String url) {

    }

    @Override
    public void loadBigImage(Context ctx, ImageView imageView, String url) {




   }

    // private void loadImageByLoad(ViewHolder holder, String imageUrl, int position) {
    //
    //     // String imagePath = new File(AppUtil.getAppContext().getFilesDir(), position + "haha.jpeg").getAbsolutePath();
    //     //
    //     // final int imageViewSize = AppUtil.getScreenW() / GRIL_COLUMN;
    //     // final ImageView imageView = holder.getView(R.id.iv_girl);
    //     //
    //     // manager.loadImage(imageUrl, imagePath, new IAsyncImageLoadedCallBack() {
    //     //     @Override
    //     //     public void imageLoaded(Bitmap imageBitmap, String imgUrl) {
    //     //         int width = imageBitmap.getWidth();
    //     //         int height = imageBitmap.getHeight();
    //     //
    //     //         // imageView1.setRelative(width / height);
    //     //         // imageView.setImageBitmap(imageBitmap);
    //     //
    //     //         //计算高宽比
    //     //         int finalHeight = (imageViewSize) * height / width;
    //     //
    //     //         ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
    //     //         layoutParams.height = finalHeight;
    //     //         imageView.setImageBitmap(imageBitmap);
    //     //     }
    //     // });
    // }
    //
    // private void loadImageByPicasso(final ViewHolder holder, String imageUrl) {
    //     // final ImageView imageView = holder.getView(R.id.iv_girl);
    //     // ImagePicassoUtil.loadBigImage(imageView, imageUrl);
    // }
    //
    // private void loadImageByGlide(final ViewHolder holder, final String imageUrl) {
    //     // final int imageViewWidth = AppUtil.getScreenW() / GRIL_COLUMN;
    //     // final ImageView imageView = holder.getView(R.id.iv_girl);
    //     // final ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
    //     // //
    //     // Glide.with(getContext())
    //     //         .load(imageUrl)
    //     //         .asBitmap()
    //     //         // .Transformation()
    //     //         // .thumbnail(0.1f)
    //     //         // .fitCenter()
    //     //         // .centerCrop() Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL
    //     //         // .placeholder(R.drawable.image_default_202)
    //     //         .diskCacheStrategy(DiskCacheStrategy.SOURCE)
    //     //         .into(new SimpleTarget<Bitmap>() {
    //     //                   @Override
    //     //                   public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
    //     //
    //     //                       int width = bitmap.getWidth();
    //     //                       int height = bitmap.getHeight();
    //     //                       log.d("onResourceReady(): imageUrl:  " + imageUrl);
    //     //                       log.d("onResourceReady(): width:  " + width + ", height: " + height + "   " + imageUrl);
    //     //
    //     //                       // imageView1.setRelative(width / height);
    //     //                       // imageView.setImageBitmap(bitmap);
    //     //
    //     //                       //计算控件高宽比
    //     //                       int finalHeight = (int) (imageViewWidth * height / width + 0.5f);
    //     //                       layoutParams.height = finalHeight;
    //     //
    //     //                       log.d("onResourceReady(): imageViewWidth: " + imageViewWidth
    //     //                               + " imageViewHeight: " + finalHeight);
    //     //
    //     //                       // //计算inSampleSize
    //     //                       // float scaleW = (width * 1.0f) / imageViewWidth;
    //     //                       // float scaleH = (height * 1.0f) / layoutParams.height;
    //     //                       // //取大的
    //     //                       // float inSampleSize = Math.max(scaleW, scaleH);
    //     //
    //     //                       // Bitmap scaleBitmap = BitmapUtils.createScaleBitmap(bitmap, Math.abs(imageViewWidth), Math.abs(finalHeight), (int) inSampleSize);
    //     //
    //     //                       // imageView.setImageBitmap(bitmap);
    //     //                       bitmap.recycle();
    //     //
    //     //                       Glide.with(getContext())
    //     //                               .load(imageUrl)
    //     //                               .asBitmap()
    //     //                               // .Transformation()
    //     //                               .thumbnail(0.1f)
    //     //                               .override(imageViewWidth, finalHeight)
    //     //                               // .placeholder(R.drawable.image_default_202)
    //     //                               .error(R.drawable.image_default_202)
    //     //                               .diskCacheStrategy(DiskCacheStrategy.RESULT)
    //     //                               .into(imageView);
    //     //                   }
    //     //               }
    //     //         );
    // }
}
