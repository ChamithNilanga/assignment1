package com.gapstars.cwe.assessment.api;


import com.gapstars.cwe.assessment.model.Data;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by cwe on 3/6/2018.
 */

public interface Service {

    @GET("jobsearch.json")
    Observable<Data> getData();
}
