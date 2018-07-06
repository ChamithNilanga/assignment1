package com.gapstars.cwe.assessment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cwe on 7/5/2018.
 */

public class Data {
    @SerializedName("data")
    @Expose
    private Map<String, ArrayList<Example>> elemDetails = new HashMap<>();

    public Map<String, ArrayList<Example>> getElemDetails() {
        return elemDetails;
    }

    public void setElemDetails(Map<String, ArrayList<Example>> elemDetails) {
        this.elemDetails = elemDetails;
    }

    public Collection<ArrayList<Example>> getValues(){
        return elemDetails.values();
    }
}
