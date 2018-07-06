
package com.gapstars.cwe.assessment.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Photo {

    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("owned_by_id")
    @Expose
    public int ownedById;
    @SerializedName("owned_by_type")
    @Expose
    public String ownedByType;
    @SerializedName("filetype")
    @Expose
    public String filetype;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;
    @SerializedName("formats")
    @Expose
    public List<Format> formats = null;

}
