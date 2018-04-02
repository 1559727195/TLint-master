package com.gzsll.hupu.ui.splash;

import android.content.Context;

import com.gzsll.hupu.components.okhttp.OkHttpHelper;
import com.gzsll.hupu.injector.PerActivity;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Subscription;

/**
 * Created by zhu on 2017/9/25.
 */
@PerActivity
public class SplashNewPresenter implements SplashInterface {
    private final int str;
    private final String ss;
    private SplashContract.View mSplashView;
    private Subscription mSubscription;
    private Context mContext;
    private OkHttpHelper mOkHttpHelper;

    @Inject
    public SplashNewPresenter (Context mContext, OkHttpHelper mOkHttpHelper, int str, @Named("string")String ss) {
        this.mContext = mContext;//MyApplication
        this.mOkHttpHelper = mOkHttpHelper;//component.oKHttpHelper
        this.str = str;
        this.ss = ss;
    }


    @Override
    public void splash_interface() {

    }
}
