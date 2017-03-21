// package com.bing.lan.comm.utils.EasyImageLoader;
//
// import android.graphics.Bitmap;
// import android.graphics.BitmapFactory;
// import android.util.Base64;
//
// import java.io.ByteArrayOutputStream;
// import java.io.FileDescriptor;
// import java.io.IOException;
// import java.io.OutputStream;
//
// /**
//  * 处理图片的相关类，压缩质量、缩放图片
//  * Created by Wenhuaijun on 2016/4/24 0024.
//  */
// public class BitmapUtils {
//
//     /**
//      * 计算图片的缩放值
//      *
//      * @param options   options.inJustDecodeBounds = true时传入的option
//      * @param reqWidth  需要按需压缩的宽度
//      * @param reqHeight 需要按需压缩的高度
//      * @return 采样率inSampleSize
//      */
//     public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
//         final int height = options.outHeight;
//         final int width = options.outWidth;
//
//         return calculateInSampleSize(height, width, reqWidth, reqHeight);
//     }
//
//     public static int calculateInSampleSize(final int height, final int width, int reqWidth, int reqHeight) {
//
//         int inSampleSize = 1;
//
//         if (height > reqHeight || width > reqWidth) {
//            /* final int heightRatio = Math.round((float) height/ (float) reqHeight);
//             final int widthRatio = Math.round((float) width / (float) reqWidth);
//             inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;*/
//             final int halfHeight = height / 2;
//             final int halfWidth = width / 2;
//             while ((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqWidth) {
//                 inSampleSize *= 2;
//             }
//         }
//         return inSampleSize;
//     }
//
//     /**
//      * @param src
//      * @param dstWidth
//      * @param dstHeight
//      * @return
//      * @description 通过传入的bitmap，进行压缩，得到符合标准的bitmap
//      */
//     public static Bitmap createScaleBitmap(Bitmap src, int dstWidth, int dstHeight, int inSampleSize) {
//         //如果inSampleSize是2的倍数，也就说这个src已经是我们想要的缩略图了，直接返回即可。
//         if (inSampleSize % 2 == 0) {
//             return src;
//         }
//         // 如果是放大图片，filter决定是否平滑，如果是缩小图片，filter无影响，我们这里是缩小图片，所以直接设置为false
//         Bitmap dst = Bitmap.createScaledBitmap(src, dstWidth, dstHeight, false);
//         if (src != dst) { // 如果没有缩放，那么不回收
//             src.recycle(); // 释放Bitmap的native像素数组
//         }
//         return dst;
//     }
//
//     /**
//      * 根据路径获得图片并压缩，返回bitmap用于显示
//      *
//      * @param filePath  file的地址
//      * @param reqWidth  需求宽度
//      * @param reqHeight 需求高度
//      * @return bitmap
//      */
//     public static Bitmap getSmallBitmap(String filePath, int reqWidth, int reqHeight) {
//         final BitmapFactory.Options options = new BitmapFactory.Options();
//         options.inJustDecodeBounds = true;
//         BitmapFactory.decodeFile(filePath, options);
//         // Calculate inSampleSize
//         options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
//
//         // Decode bitmap with inSampleSize set
//         options.inJustDecodeBounds = false;
//         //不需要缩放
//         if (options.inSampleSize <= 1) {
//             return BitmapFactory.decodeFile(filePath);
//         }
//         return BitmapFactory.decodeFile(filePath, options);
//     }
//
//     /**
//      * 根据输入流获取图片并压缩，返回bitmap用于显示
//      *
//      * @param reqWidth  需求宽度
//      * @param reqHeight 需求高度
//      * @return bitmap
//      */
//     public static Bitmap getSmallBitmap(FileDescriptor fileDescriptor, int reqWidth, int reqHeight) {
//         final BitmapFactory.Options options = new BitmapFactory.Options();
//         options.inJustDecodeBounds = true;
//         BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
//         // Calculate inSampleSize
//         options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
//         options.inJustDecodeBounds = false;
//         //不需要缩放
//         if (options.inSampleSize <= 1) {
//             return BitmapFactory.decodeFileDescriptor(fileDescriptor);
//         }
//         //inSampleSize！=1进行缩放
//         return BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
//     }
//
//     //
//
//     /**
//      * 把bitmap转换成String
//      *
//      * @param filePath  图片的地址
//      * @param reqWidth  需求宽度
//      * @param reqHeight 需求高度
//      * @return
//      */
//     public static String bitmapToString(String filePath, int reqWidth, int reqHeight) {
//
//         Bitmap bm = getSmallBitmap(filePath, reqWidth, reqHeight);
//         ByteArrayOutputStream baos = new ByteArrayOutputStream();
//         bm.compress(Bitmap.CompressFormat.JPEG, 40, baos);
//         byte[] b = baos.toByteArray();
//         return Base64.encodeToString(b, Base64.DEFAULT);
//     }
//     //压缩图片质量，一般用于保存图片到本地或者上传图片到服务器时对图片质量进行压缩
//
//     /**
//      * @param outputStream 压缩后bitmap写入的输入流
//      * @param bitmap       需要压缩的bitmap
//      * @param rate         质量率，传入30，则压缩率为70%
//      */
//     public static void saveBitmapToOutputStream(OutputStream outputStream, Bitmap bitmap, int rate) {
//         //使用此流读取
//         ByteArrayOutputStream baos = new ByteArrayOutputStream();
//         //第二个参数影响的是图片的质量，但是图片的宽度与高度是不会受影响滴
//         bitmap.compress(Bitmap.CompressFormat.JPEG, rate, baos);
//         //这个函数能够设定图片的宽度与高度
//         //Bitmap map = Bitmap.createScaledBitmap(bitmap, 400, 400, true);
//         //把数据转为为字节数组
//         byte[] byteArray = baos.toByteArray();
//         try {
//             outputStream.write(byteArray);
//         } catch (IOException e) {
//             e.printStackTrace();
//         } finally {
//             if (outputStream != null) {
//                 try {
//                     outputStream.close();
//                 } catch (IOException e) {
//                     e.printStackTrace();
//                 }
//             }
//         }
//     }
// }
