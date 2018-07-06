
package com.gapstars.cwe.assessment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Shift {

    @SerializedName("tempers_needed")
    @Expose
    public int tempersNeeded;
    @SerializedName("earnings_per_hour")
    @Expose
    public double earningsPerHour;
    @SerializedName("duration")
    @Expose
    public int duration;
    @SerializedName("currency")
    @Expose
    public String currency;
    @SerializedName("start_date")
    @Expose
    public String startDate;
    @SerializedName("start_time")
    @Expose
    public String startTime;
    @SerializedName("start_datetime")
    @Expose
    public String startDatetime;
    @SerializedName("end_time")
    @Expose
    public String endTime;
    @SerializedName("end_datetime")
    @Expose
    public String endDatetime;
    @SerializedName("is_auto_accepted_when_applied_for")
    @Expose
    public int isAutoAcceptedWhenAppliedFor;

}
