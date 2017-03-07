package com.bing.lan.comm.utils;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;


public class RealmManager {
    private static final String DB_NAME = "fm.db";
    private static RealmConfiguration sConfig;
    private static RealmManager instance;
    private static int code;

    private Realm mainRealm;

    private RealmManager() {
        mainRealm = Realm.getInstance(sConfig);
    }

    /**
     * 初始化Realm配置
     *
     * @param appContext
     */
    public static void initRealm(Context appContext) {
        Realm.init(appContext);
        sConfig = new RealmConfiguration.Builder()
                .name(DB_NAME)
               .deleteRealmIfMigrationNeeded()
//                .schemaVersion(3)
                .build();
        RealmManager instance = getInstance();
        code = instance.mainRealm.hashCode();
    }

    /**
     * 获得主线程查询实例
     *
     * @return
     */
    public static RealmManager getInstance() {
        if (instance == null) {
            synchronized (RealmManager.class) {
                if (instance == null)
                    instance = new RealmManager();
            }
        }
        return instance;
    }

    /**
     * The async instance must call {@code close()} method release memory after operation.
     *
     * @return
     */
    public static RealmManager getAsyncInstance() {
        return new RealmManager();
    }

    /**
     * 关闭异步线程的Realm实例
     */
    public void close() {
        if (mainRealm.hashCode() == code) {
            throw new RuntimeException("主线程实例不能关闭");
        }
        mainRealm.close();
    }


    public Realm getMainRealm() {
        return mainRealm;
    }


    public String getRealmDBFilePath() {
        return mainRealm.getPath();
    }
//
//    public boolean isRead(long id) {
//        RealmResults<ReadStateBean> results = mainRealm
//                .where(ReadStateBean.class).equalTo("id", id)
//                .equalTo("isRead", true).findAll();
//        return results.size() > 0;
//    }

}
