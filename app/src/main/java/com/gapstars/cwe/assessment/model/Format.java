
package com.gapstars.cwe.assessment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Format {

    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("photo_id")
    @Expose
    public int photoId;
    @SerializedName("format")
    @Expose
    public String format;
    @SerializedName("cdn_url")
    @Expose
    public String cdnUrl;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;

}
