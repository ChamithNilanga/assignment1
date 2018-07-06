
package com.gapstars.cwe.assessment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Date {

    @SerializedName("date")
    @Expose
    public String date;
    @SerializedName("timezone_type")
    @Expose
    public int timezoneType;
    @SerializedName("timezone")
    @Expose
    public String timezone;

}
