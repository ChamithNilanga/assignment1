package com.gapstars.cwe.assessment.api;


import com.gapstars.cwe.assessment.model.Data;

import retrofit2.http.GET;
import rx.Observable;

public interface Service {

    @GET("jobsearch.json")
    Observable<Data> getData();
}
