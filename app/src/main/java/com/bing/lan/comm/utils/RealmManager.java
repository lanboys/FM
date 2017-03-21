package com.bing.lan.comm.utils;

import android.content.Context;

import com.bing.lan.comm.config.AppConfig;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RealmManager {
    private static RealmConfiguration sConfig;
    private static RealmManager instance;
    private static int code;//只在初始化中生成

    private Realm mRealm;//必须是非静态的

    private RealmManager() {
        mRealm = Realm.getInstance(sConfig);
    }

    /**
     * 初始化Realm配置,将 hashCode 保存起来
     * <p>
     * RealmConfiguration 支持的方法：
     * <p>
     * Builder.name : 指定数据库的名称。如不指定默认名为default。
     * <p>
     * Builder.schemaVersion : 指定数据库的版本号。
     * <p>
     * Builder.encryptionKey : 指定数据库的密钥。
     * <p>
     * Builder.migration : 指定迁移操作的迁移类。
     * <p>
     * Builder.deleteRealmIfMigrationNeeded : 声明版本冲突时自动删除原数据库。
     * <p>
     * Builder.inMemory : 声明数据库只在内存中持久化。
     * <p>
     * build : 完成配置构建。
     *
     * @param appContext
     */
    public static void initRealm(Context appContext) {
        Realm.init(appContext);
        sConfig = new RealmConfiguration.Builder()
                .name(AppConfig.DB_NAME)   //文件名
                .deleteRealmIfMigrationNeeded()
                .schemaVersion(0)
                // .migration(new RealmMigration() {
                //     @Override
                //     public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
                //
                //     }
                // })
                .build();
        RealmManager instance = getMainRealmManager();
        code = instance.mRealm.hashCode();
    }

    /**
     * 获得主线程查询实例
     *
     * @return
     */
    public static RealmManager getMainRealmManager() {
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
        if (mRealm.hashCode() == code) {
            throw new RuntimeException("主线程实例不能关闭");
        }

        if (mRealm != null && !mRealm.isClosed()) {
            mRealm.close();
            mRealm = null;//todo 不知是否会报错,暂时未测试
        }
    }

    public Realm getRealmInstance() {
        return mRealm;
    }

    public String getRealmDBFilePath() {
        return mRealm.getPath();
    }

    /**
     * 创建一个叫做 default.realm 的Realm文件，
     * 一般来说，这个文件位于/data/data/包名/files/。
     * 通过 realm.getPath() 来获得该Realm的绝对路径
     */

    //
    //    public boolean isRead(long id) {
    //        RealmResults<ReadStateBean> results = mRealm
    //                .where(ReadStateBean.class).equalTo("id", id)
    //                .equalTo("isRead", true).findAll();
    //        return results.size() > 0;
    //    }
}
