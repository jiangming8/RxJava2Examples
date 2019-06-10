package com.nanchen.rxjava2examples.module.rxjava2.operators.item;

import android.util.Log;

import com.nanchen.rxjava2examples.R;

import io.reactivex.Notification;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * 生命周期测试
 */

public class RxDoAllActivity extends RxOperatorBaseActivity {

    private static final String TAG = "RxDoAllActivity";

    @Override
    protected String getSubTitle() {
        return getString(R.string.rx_buffer);
    }

    @Override
    protected void doSomething() {
        Observable.just("Hello")
//        Observable.just(1, 2, 3, 4, 5)
//                .buffer(3, 4)
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.e(TAG,"doOnNext");
                    }
                })
                .doAfterNext(new Consumer<String>() {
                    @Override
                    public void accept(String integers) throws Exception {
                        Log.e(TAG,"doAfterNext");
                    }
                })
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        Log.e(TAG,"doOnSubscribe");
                    }
                })
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.e(TAG,"doOnComplete");
                    }
                })
                .doOnEach((Consumer<? super Notification<String>>) new Consumer<Notification<String>>() {
                    @Override
                    public void accept(Notification<String> listNotification) throws Exception {
                        Log.e(TAG,"doOnEach");
                    }
                })
                .doAfterTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.e(TAG,"doAfterTerminate");
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.e(TAG,"doFinally");
                    }
                })
                .doOnLifecycle(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        Log.e(TAG,"doOnLifecycle--Consumer");
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.e(TAG,"doOnLifecycle--Action");
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String integers) throws Exception {
                        Log.e(TAG,"subscribe--Consumer--onNext");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(TAG,"subscribe--Consumer--onError");
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.e(TAG,"subscribe--Consumer--onComplete");
                    }
                }, new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        Log.e(TAG,"subscribe--Consumer--onSubscribe");
                    }
                });
    }
}
