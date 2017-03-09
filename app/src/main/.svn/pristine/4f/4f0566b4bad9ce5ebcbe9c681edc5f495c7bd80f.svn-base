package com.bing.lan.comm.utils.musicplay;

/**
 * @author 蓝兵
 * @time 2017/3/1  22:07
 */
// public final class MusicPlayerHandler extends Handler {
//
//     private final WeakReference<MusicService> mService;
//     private float mCurrentVolume = 1.0f;
//
//     public MusicPlayerHandler(final MusicService service, final Looper looper) {
//         super(looper);
//         mService = new WeakReference< >(service);
//     }
//
//     @Override
//     public void handleMessage(final Message msg) {
//         final MusicService service = mService.get();
//         if (service == null) {
//             return;
//         }
//
//         synchronized (service) {
//             switch (msg.what) {
//                 //降低音量
//                 case MusicServiceCons.FADEDOWN:
//                     mCurrentVolume -= .05f;
//                     if (mCurrentVolume > .2f) {
//                         sendEmptyMessageDelayed(MusicServiceCons.FADEDOWN, 10);
//                     } else {
//                         mCurrentVolume = .2f;
//                     }
//                     service.mPlayer.setVolume(mCurrentVolume);
//                     break;
//                 //提高音量
//                 case MusicServiceCons.FADEUP:
//                     mCurrentVolume += .01f;
//                     if (mCurrentVolume < 1.0f) {
//                         sendEmptyMessageDelayed(MusicServiceCons.FADEUP, 10);
//                     } else {
//                         mCurrentVolume = 1.0f;
//                     }
//                     service.mPlayer.setVolume(mCurrentVolume);
//                     break;
//
//                 case MusicServiceCons.TRACK_ENDED:
//
//                     // service.gotoNext( );
//
//                     break;
//                 case MusicServiceCons.TRACK_WENT_TO_NEXT:
//
//                     service.setNextTrack();
//
//                     break;
//                 case MusicServiceCons.HANDLER_CHANGE_CURR_POS:
//
//                     service.changCurrentPlayPosition();
//
//                     break;
//                 default:
//                     break;
//             }
//         }
//     }
// }