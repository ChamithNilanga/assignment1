package com.gapstars.cwe.assessment.di;

import com.gapstars.cwe.assessment.BuildConfig;
import com.gapstars.cwe.assessment.api.Service;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {
    @Provides
    @Singleton
    static Service providesService(Retrofit retrofit) {
        return retrofit.create(Service.class);
    }

    @Provides
    @Singleton
    static Gson providesGson() {
        return new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
    }

    @Provides
    @Singleton
    static Retrofit providesRetrofit(Gson gson, String url) {
        return new Retrofit.Builder().baseUrl(url)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @Provides
    @Singleton
    static String providesUrl() {
        return BuildConfig.BASE_URL;
    }
}
