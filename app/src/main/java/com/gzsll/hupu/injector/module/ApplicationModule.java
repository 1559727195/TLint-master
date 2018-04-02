package com.gzsll.hupu.injector.module;

import android.app.NotificationManager;
import android.content.Context;
import android.view.LayoutInflater;

import com.gzsll.hupu.components.okhttp.CookieInterceptor;
import com.gzsll.hupu.components.okhttp.HttpLoggingInterceptor;
import com.gzsll.hupu.components.okhttp.OkHttpHelper;
import com.gzsll.hupu.components.retrofit.RequestHelper;
import com.gzsll.hupu.components.storage.UserStorage;
import com.squareup.otto.Bus;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * @author gzsll
 */
@Module
public class ApplicationModule {

    private final Context context;

    public ApplicationModule(Context context) {//第0步
        this.context = context;
    }

    @Provides
    @Singleton
    public Context provideApplicationContext() {//第一步
        return context.getApplicationContext();
    }

    @Provides
    @Singleton
    public Bus provideBusEvent() {
        return new Bus();
    }

    @Provides
    @Singleton
    @Named("api")
    OkHttpClient provideApiOkHttpClient( //第四步  //@Named("api") ->OkHttpClient provideApiOkHttpClient
            CookieInterceptor mCookieInterceptor) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS);
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(logging);
        builder.addInterceptor(mCookieInterceptor);
        return builder.build();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(@Named("api") OkHttpClient mOkHttpClient) {
        OkHttpClient.Builder builder = mOkHttpClient.newBuilder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);
        builder.interceptors().clear();
        return builder.build();
    }

    @Provides
    @Singleton
    LayoutInflater provideLayoutInflater(Context context) {
        return (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Provides
    @Singleton
    NotificationManager provideNotificationManager(Context mContext) {
        return (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @Provides
    @Singleton
    CookieInterceptor provideCookieInterceptor(UserStorage mUserStorage) {//第三步到这里来
        return new CookieInterceptor(mUserStorage);
    }

//    @Provides
//    @Singleton
//    UserStorage provideUserStorage(Context mContext) {//第二步,@Inject或者在ApplicationComponent是要把Module的数据送出去
//        return new UserStorage(mContext);
//    }
    @Provides
    @Singleton
    UserStorage provideUserStorage (Context mContext) {
        return new UserStorage(mContext);
    }

    @Provides
    @Singleton
    RequestHelper provideRequestHelper(Context mContext,
                                       UserStorage mUserStorage) {
        return new RequestHelper(mContext, mUserStorage);
    }

    @Provides
    @Singleton
    OkHttpHelper provideOkHttpHelper(OkHttpClient mOkHttpClient) {// 第五步//提供provideokHttpHelper(OkHttpClient
        //mOkHttpClient);
        return new OkHttpHelper(mOkHttpClient);
    }

    @Provides
    @Singleton
    int provideInt () {
        return 3;
    }

    @Provides
    @Singleton
    @Named("string")
    String provideString () {
        return "hello";
    }
}
