package com.gapstars.cwe.assessment.api;

import com.gapstars.cwe.assessment.model.Data;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by cwe on 7/5/2018.
 */

public class ServiceClient {

    private static final String BASE_URL = "https://raw.githubusercontent.com/ChamithNilanga/Gapstars/master/";

    private static ServiceClient instance;
    private Service gitHubService;

    private ServiceClient() {
        final Gson gson =
                new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        gitHubService = retrofit.create(Service.class);
    }

    public static ServiceClient getInstance() {
        if (instance == null) {
            instance = new ServiceClient();
        }
        return instance;
    }

    public Observable<Data> getStarredRepos() {
        return gitHubService.getData();
    }
}