package com.bing.lan.inke.yingke;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.recorder.api.LiveConfig;
import com.baidu.recorder.api.LiveSession;
import com.baidu.recorder.api.LiveSessionHW;
import com.baidu.recorder.api.LiveSessionSW;
import com.baidu.recorder.api.SessionStateListener;
import com.bing.lan.fm.R;

import java.util.TimerTask;



public class StreamingActivity extends AppCompatActivity
        implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {
    private String TAG="StreamingActivity";

    /**直播view*/
    private SurfaceView cameraView;
    /**显示直播状态*/
    private TextView mTextAction;
    /**开始闪光灯按钮*/
    private Button mFlashStateButton;
    /**开始美颜按钮*/
    private Button mBeautyEffectStateButton;
    /**聚焦图片*/
    private ImageView mFocusIcon;

    /***当前摄像头方向*/
    private int mCurrentCamera;
    /**推流核心类*/
    private LiveSession mLiveSession = null;
    /*** 设置摄像头为竖向*/
    private int orientation = LiveConfig.ORIENTATION_PORTRAIT;// LiveConfig.ORIENTATION_LANDSCAPE
    /**设置推流视频宽度*/
    private int mVideoWidth=1280;//1920,  1280, 640, 480
    /**设置推流视频高度*/
    private int mVideoHeight=720;//1080,  720,  480, 360
    /**设置视频帧率*/
    private int mFrameRate=15;   //18,    15,   15,  15
    /**设置视频码率*/
    private int mBitrate=1200;   //2000,  1200, 800, 600

    /**是否开始直播*/
    private boolean isSessionStarted=false;
    /**推流地址*/
    private String mStreamingUrl="rtmp://push.bcelive.com/live/trtsifeoo14ik8bsd0";

    /**推流状态的监听*/
    private SessionStateListener mStateListener;

    /**初始闪光灯为关闭*/
    private boolean isFlashOn = false;
    /**默认是没有启用美颜功能*/
    private boolean hasBueatyEffect = false;
    /**手势监听核心类*/
    private GestureDetectorCompat mDetector;
    /**会话是否已经准备好了*/
    private boolean isSessionReady;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**如果是竖屏*/
        if(orientation== LiveConfig.ORIENTATION_PORTRAIT){
            setContentView(R.layout.activity_streaming_portrait);
            /**如果是横屏*/
        }else{
            setContentView(R.layout.activity_streaming_landscape);
        }
        cameraView = (SurfaceView) findViewById(R.id.sv_camera_preview);
        mTextAction=(TextView)findViewById(R.id.tv_streaming_action);
        mFlashStateButton=(Button)findViewById(R.id.iv_flash_state);
        mBeautyEffectStateButton=(Button)findViewById(R.id.iv_effect_state);
        mFocusIcon=(ImageView)findViewById(R.id.iv_ico_focus);
        /**推流状态的监听*/
        initStateListener();
        /**初始化会话*/
        initRTMPSession(cameraView.getHolder());

    }

    /***
     * 初始化会话
     * @param sh
     */
    private void initRTMPSession(SurfaceHolder sh){
        /**初始化当前摄像头为前置摄像头*/
        mCurrentCamera= LiveConfig.CAMERA_FACING_FRONT;
        LiveConfig liveConfig = new LiveConfig.Builder()
                .setCameraId(mCurrentCamera) // 选择摄像头为前置摄像头
                .setCameraOrientation(orientation) // 设置摄像头为竖向
                .setVideoWidth(mVideoWidth) // 设置推流视频宽度, 需传入长的一边
                .setVideoHeight(mVideoHeight) // 设置推流视频高度，需传入短的一边
                .setVideoFPS(mFrameRate) // 设置视频帧率
                .setInitVideoBitrate(mBitrate) // 设置视频码率，单位为bit per seconds
                .setAudioBitrate(64 * 1000) // 设置音频码率，单位为bit per seconds
                .setAudioSampleRate(LiveConfig.AUDIO_SAMPLE_RATE_44100) // 设置音频采样率
                .setGopLengthInSeconds(2) // 设置I帧间隔，单位为秒
                .setQosEnabled(true) // 开启码率自适应，默认为true，即默认开启
                .setMinVideoBitrate(200 * 1000) // 码率自适应，最低码率
                .setMaxVideoBitrate(1024 * 1000) // 码率自适应，最高码率
                .setQosSensitivity(5) // 码率自适应，调整的灵敏度，单位为秒，可接受[5, 10]之间的整数值
                .build();
        Log.d(TAG, "Calling initRTMPSession..." + liveConfig.toString());
        /**如果sdk版本大于18*/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            mLiveSession = new LiveSessionHW(this, liveConfig);
        } else {
            mLiveSession = new LiveSessionSW(this, liveConfig);
        }
        /**推流状态的监听*/
        mLiveSession.setStateListener(mStateListener);

        /**会话绑定预览控件*/
        mLiveSession.bindPreviewDisplay(sh);
        /**启动音视频采集设备（即相机和 MIC）*/
        mLiveSession.prepareSessionAsync();

        /**手势监听器*/
        mDetector = new GestureDetectorCompat(this, this);
        mDetector.setOnDoubleTapListener(this);

    }


    /**
     * 开始直播（会话）：mLiveSession.startRtmpSession(mStreamingUrl)
     * 停止直播（会话）：mLiveSession.stopRtmpSession()
     * @param v
     */
    public void onClickStreamingButton(View v) {

        if (!isSessionStarted && !TextUtils.isEmpty(mStreamingUrl)) {
            if (mLiveSession.startRtmpSession(mStreamingUrl)) {
                Log.i(TAG, "Starting Streaming in right state!");
            } else {
                Log.e(TAG, "Starting Streaming in wrong state!");
            }
            isSessionStarted=true;
            mTextAction.setText("停止直播");
        } else {
            if (mLiveSession.stopRtmpSession()) {
                Log.i(TAG, "Stopping Streaming in right state!");
            } else {
                Log.e(TAG, "Stopping Streaming in wrong state!");
            }
            isSessionStarted=false;
            mTextAction.setText("开始直播");
        }
    }




    /**
     * 初始化流监听事件
     */
    private void initStateListener() {
        mStateListener = new SessionStateListener() {
            /**
             * 录制设备准备完毕
             * @param code 固定为RESULT_CODE_OF_OPERATION_SUCCEEDED
             */
            @Override
            public void onSessionPrepared(int code) {
                if (code == SessionStateListener.RESULT_CODE_OF_OPERATION_SUCCEEDED) {
                    Log.v(TAG,"录制设备准备完毕");
                    isSessionReady=true;
                }
            }
            /**
             * 推流开始后的回调
             * @param code 固定为RESULT_CODE_OF_OPERATION_SUCCEEDED
             */
            @Override
            public void onSessionStarted(int code) {
                if (code == SessionStateListener.RESULT_CODE_OF_OPERATION_SUCCEEDED) {
                    Log.v(TAG,"推流开始后的回调");
                } else {
                    Log.e(TAG, "Starting Streaming failed!");
                }
            }

            /**
             * 推流结束后的回调
             * @param code 固定为RESULT_CODE_OF_OPERATION_SUCCEEDED
             */
            @Override
            public void onSessionStopped(int code) {
                if (code == SessionStateListener.RESULT_CODE_OF_OPERATION_SUCCEEDED) {
                    Log.v(TAG,"推流结束后的回调");
                } else {
                    Log.e(TAG, "Stopping Streaming failed!");
                }
            }

            /**
             * 推流 SDK 出错后的回调
             * @param code 错误类型如下：
             *                ERROR_CODE_OF_OPEN_MIC_FAILED
             *                ERROR_CODE_OF_OPEN_CAMERA_FAILED
             *                ERROR_CODE_OF_PREPARE_SESSION_FAILED
             *                ERROR_CODE_OF_CONNECT_TO_SERVER_FAILED
             *                ERROR_CODE_OF_DISCONNECT_FROM_SERVER_FAILED
             *                ERROR_CODE_OF_UNKNOWN_STREAMING_ERROR
             *                ERROR_CODE_OF_WEAK_CONNECTION_ERROR
             *                ERROR_CODE_OF_SERVER_INTERNAL_ERROR
             *                ERROR_CODE_OF_LOCAL_NETWORK_ERROR
             */
            @Override
            public void onSessionError(int code) {
                switch (code) {
                    case SessionStateListener.ERROR_CODE_OF_OPEN_MIC_FAILED:
                        Log.e(TAG, "Error occurred while opening MIC!");
                        break;
                    case SessionStateListener.ERROR_CODE_OF_OPEN_CAMERA_FAILED:
                        Log.e(TAG, "Error occurred while opening Camera!");
                        break;
                    case SessionStateListener.ERROR_CODE_OF_PREPARE_SESSION_FAILED:
                        Log.e(TAG, "Error occurred while preparing recorder!");
                        isSessionReady=false;
                        break;
                    case SessionStateListener.ERROR_CODE_OF_CONNECT_TO_SERVER_FAILED:
                        Log.e(TAG, "Error occurred while connecting to server!");
                        break;
                    case SessionStateListener.ERROR_CODE_OF_DISCONNECT_FROM_SERVER_FAILED:
                        Log.e(TAG, "Error occurred while disconnecting from server!");
                        break;
                    default:
                        break;
                }
            }
        };
    }


    /**
     * 切换摄像头:mLiveSession.switchCamera(mCurrentCamera);
     * @param v
     */
    public void onClickSwitchCamera(View v) {
        if (mLiveSession.canSwitchCamera()) {
            if (mCurrentCamera == LiveConfig.CAMERA_FACING_BACK) {
                mCurrentCamera = LiveConfig.CAMERA_FACING_FRONT;
                mLiveSession.switchCamera(mCurrentCamera);
            } else {
                mCurrentCamera = LiveConfig.CAMERA_FACING_BACK;
                mLiveSession.switchCamera(mCurrentCamera);
            }
        } else {
            Toast.makeText(this, "抱歉！该分辨率下不支持切换摄像头！", Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * 切换闪光灯:mLiveSession.toggleFlash(!isFlashOn);
     * @param v
     */
    public void onClickSwitchFlash(View v) {
        /**如果是后摄像头*/
        if (mCurrentCamera == LiveConfig.CAMERA_FACING_BACK) {
            mLiveSession.toggleFlash(!isFlashOn);
            isFlashOn = !isFlashOn;
            if (isFlashOn) {
                mFlashStateButton.setBackgroundResource(R.mipmap.btn_flash_on);
            } else {
                mFlashStateButton.setBackgroundResource(R.mipmap.btn_flash_off);
            }
        }
    }

    /**
     * 切换美图效果：mLiveSession.enableDefaultBeautyEffect
     * @param v
     */
    public void onClickSwitchBeautyEffect(View v) {
        hasBueatyEffect = !hasBueatyEffect;
        mLiveSession.enableDefaultBeautyEffect(hasBueatyEffect);
        mBeautyEffectStateButton
                .setBackgroundResource(hasBueatyEffect ? R.mipmap.btn_effect_on : R.mipmap.btn_effect_off);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (this.mDetector.onTouchEvent(event)) {
            return true;
        }
        // Be sure to call the superclass implementation
        return super.onTouchEvent(event);
    }

    /**
     * 点击返回
     * @param v
     */
    public void onClickQuit(View v) {
        this.finish();
    }

    @Override
    protected void onDestroy() {
        recoveryResources();
        super.onDestroy();
    }

    /**
     * 回收资源
     */
    private  void recoveryResources(){
        /**如果还在直播，就停止*/
        if (isSessionStarted) {
            mLiveSession.stopRtmpSession();
            isSessionStarted = false;
        }
        /**如果会话已初始化完毕，就终止会话*/
        if (isSessionReady) {
            mLiveSession.destroyRtmpSession();
            mLiveSession = null;
            mStateListener = null;
            isSessionReady = false;
        }
    }

    /**
     * 单击触发自动对焦
     * @param arg0
     * @return
     */
    @Override
    public boolean onSingleTapConfirmed(MotionEvent arg0) {
        if (mLiveSession != null) {
            mLiveSession.focusToPosition((int) arg0.getX(), (int) arg0.getY());
            Log.d(TAG,"x="+arg0.getX()+"  y="+arg0.getY());
            mFocusIcon.setX(arg0.getX() - mFocusIcon.getWidth() / 2);
            mFocusIcon.setY(arg0.getY() - mFocusIcon.getHeight()/2);
            mFocusIcon.setVisibility(View.VISIBLE);
            new Handler().postDelayed(new TimerTask() {
                @Override
                public void run() {
                    mFocusIcon.setVisibility(View.GONE);
                }
            }, 1000);
        }
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
}
