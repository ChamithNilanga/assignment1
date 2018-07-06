package com.gapstars.cwe.assessment.api;

import com.gapstars.cwe.assessment.model.Data;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by cwe on 3/6/2018.
 */

public class Api {

    public static void getData(Callback<Data> callback) throws IOException {
        Service service = ServiceGenerator.createService(Service.class);
        //Call<Data> call = service.getData();
        //call.enqueue(callback);
    }
}
