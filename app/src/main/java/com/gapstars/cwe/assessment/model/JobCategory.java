
package com.gapstars.cwe.assessment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JobCategory {

    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("icon_path")
    @Expose
    public String iconPath;
    @SerializedName("slug")
    @Expose
    public String slug;

}
