package com.bing.lan.comm.base;

/**
 * Created by 520 on 2017/1/11.
 */

public class Test {

    static abstract class Father<T> {

        T t;

        Father(T t) {
            this.t = t;
        }

        public abstract void fatherPrint(T t);
    }

    static class Son12<K, T> extends Father<String> {

        Son12(String s) {
            super(s);
        }

        @Override
        public void fatherPrint(String s) {

        }
    }

    static class Son13<V> extends Father {

        Son13(Object o) {
            super(o);
        }

        @Override
        public void fatherPrint(Object o) {

        }
    }

    static class Son1<V, T> extends Father<T> {

        Son1(T t) {
            super(t);
        }

        @Override
        public void fatherPrint(T t) {

        }

        public void fatherPrint12(T t, V v) {

        }
    }

    static class Son15 extends Son1<String, Integer> {

        Son15(Integer integer) {
            super(integer);
        }

        @Override
        public void fatherPrint12(Integer integer, String s) {
            super.fatherPrint12(integer, s);
        }
    }

    static class Son123<T> extends Father<T> {

        Son123(T t) {
            super(t);
        }

        @Override
        public void fatherPrint(T t) {

        }
    }
    }
