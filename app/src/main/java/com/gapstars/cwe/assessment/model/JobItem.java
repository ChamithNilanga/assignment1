
package com.gapstars.cwe.assessment.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JobItem {

    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("key")
    @Expose
    public String key;
    @SerializedName("date")
    @Expose
    public Date date;
    @SerializedName("location")
    @Expose
    public Location location;
    @SerializedName("distance")
    @Expose
    public int distance;
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("max_possible_earnings_hour")
    @Expose
    public double maxPossibleEarningsHour;
    @SerializedName("max_possible_earnings_total")
    @Expose
    public double maxPossibleEarningsTotal;
    @SerializedName("client")
    @Expose
    public Client client;
    @SerializedName("job_category")
    @Expose
    public JobCategory jobCategory;
    @SerializedName("open_positions")
    @Expose
    public int openPositions;
    @SerializedName("new_matches_count")
    @Expose
    public int newMatchesCount;
    @SerializedName("shifts")
    @Expose
    public List<Shift> shifts = null;

}
