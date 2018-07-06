
package com.gapstars.cwe.assessment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rating {

    @SerializedName("average")
    @Expose
    public double average;
    @SerializedName("count")
    @Expose
    public int count;

}
