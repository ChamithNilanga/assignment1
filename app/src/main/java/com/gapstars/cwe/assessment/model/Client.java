
package com.gapstars.cwe.assessment.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Client {

    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("rating")
    @Expose
    public Rating rating;
    @SerializedName("photos")
    @Expose
    public List<Photo> photos = null;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("avg_response_time_in_hours")
    @Expose
    public int avgResponseTimeInHours;

}
