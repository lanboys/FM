package com.bing.lan.comm.utils;

import rx.Subscription;

/**
 * @author 赵坤
 * @email artzok@163.com
 */
public class RxJavaUtils {
    public static void releaseSubscribe(Subscription subscribe) {
        if(subscribe != null && !subscribe.isUnsubscribed()) {
            subscribe.unsubscribe();
        }
    }
}
