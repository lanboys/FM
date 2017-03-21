package com.bing.lan.comm.utils.musicplay;

import com.bing.lan.comm.utils.RealmManager;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author 蓝兵
 * @time 2017/3/7  23:39
 */
public class MusicPlayDao {

    private static RealmManager sAsyncRealmInstance;

    private static Realm getMainRealmInstance() {
        return RealmManager.getMainRealmManager().getRealmInstance();
    }

    /**
     * 异步的用完后必须记得关闭
     */
    private static Realm getAsyncRealmInstance() {

        if (sAsyncRealmInstance == null) {
            sAsyncRealmInstance = RealmManager.getAsyncInstance();
        }
        return sAsyncRealmInstance.getRealmInstance();
    }

    /**
     * 异步的用完后必须记得关闭
     */
    public static void closeAsyncRealm() {
        if (sAsyncRealmInstance != null) {
            sAsyncRealmInstance.close();
            sAsyncRealmInstance = null;
        }
    }

    private static void closeByThread(boolean isMainThread) {
        if (isMainThread) {
            closeAsyncRealm();
        }
    }

    private static Realm getRealmByThread(boolean isMainThread) {
        Realm mainRealm;
        if (isMainThread) {
            mainRealm = getMainRealmInstance();
        } else {
            mainRealm = getAsyncRealmInstance();
        }
        return mainRealm;
    }

    public static void saveMusicInfo(Music music) {
        saveMusicInfo(true, music);
    }

    // TODO: 2017/3/19 其他方法还需要封装
    public static void saveMusicInfo(boolean isMainThread, Music music) {

        Realm mainRealm = getRealmByThread(isMainThread);

        mainRealm.beginTransaction();
        mainRealm.copyToRealmOrUpdate(music);
        mainRealm.commitTransaction();

        closeByThread(isMainThread);
    }

    public static void deleteMusicInfo(long albumId) {

        Music music = queryMusicInfoByAlbumId(albumId);
        Realm mainRealm = getMainRealmInstance();
        mainRealm.beginTransaction();
        music.deleteFromRealm();
        mainRealm.commitTransaction();
    }

    // mainRealm.executeTransaction(new Realm.Transaction() {
    //     @Override
    //     public void execute(Realm realm) {
    //
    //     }
    // });

    /**
     * 注意: ：如果当 Acitivity 或 Fragment 被销毁时，在 OnSuccess 或 OnError
     * 中执行UI操作，将导致程序奔溃 。用 RealmAsyncTask .cancel();可以取消事务
     * 在 onStop 中调用，避免 crash。
     */
    //
    // RealmAsyncTask realmAsyncTask = mainRealm.executeTransactionAsync(new Realm.Transaction() {
    //     @Override
    //     public void execute(Realm realm) {
    //        realm.copyToRealmOrUpdate(music);
    //     }
    // }, new Realm.Transaction.OnSuccess() {
    //     @Override
    //     public void onSuccess() {
    //
    //     }
    // }, new Realm.Transaction.OnError() {
    //     @Override
    //     public void onError(Throwable error) {
    //
    //     }
    // });
    // }
    public static Music queryMusicInfoByUrl(String url) {
        return getMainRealmInstance()
                .where(Music.class)
                .equalTo("url", url)
                .findFirst();
    }

    public static Music queryMusicInfoByAlbumId(long albumId) {
        return getMainRealmInstance()
                .where(Music.class)
                .equalTo("albumId", albumId)
                .findFirst();
    }

    // public static void queryMusicInfoByUrl12(String url, long albumId) {
    //     RealmResults<Music> url1 = getMainRealmInstance()
    //             .where(Music.class)
    //             .equalTo("url", url)
    //             .equalTo("albumId", albumId)
    //             .findAll();
    // }

    // RealmResults<Music> musics = MusicPlayDao.queryAllMusicInfo();
    //
    // for (Music music : musics) {
    //     log.d("queryMusic(): 数据库查询到的数据总条数:  " + musics.size() + ", 音乐信息: " + music.toString() + "\n");
    // }

    /**
     * 用这个方法查询
     *
     * @return
     */
    public static RealmResults<Music> queryAllMusicInfo() {
        return getMainRealmInstance()
                .where(Music.class)
                .findAll();
    }

    public static void queryAllMusicInfoAsync(final OnMusicQueryListener listener) {
        Observable<RealmResults<Music>> observable = getMainRealmInstance()
                .where(Music.class)
                .findAllAsync().asObservable();

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RealmResults<Music>>() {
                    @Override
                    public void onCompleted() {
                        if (listener != null) {
                            listener.onCompleted();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (listener != null) {
                            listener.onError(e);
                        }
                    }

                    @Override
                    public void onNext(RealmResults<Music> musics) {
                        if (listener != null) {
                            listener.onSuccess(musics);
                        }
                    }
                });
    }

    public interface OnMusicQueryListener {

        void onSuccess(RealmResults<Music> musics);

        void onError(Throwable e);

        void onCompleted();
    }
}
