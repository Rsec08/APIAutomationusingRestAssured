package com.acres.responsepojoclasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DealerImageData {

@SerializedName("IMAGE_URL")
@Expose
private String iMAGEURL;
@SerializedName("LOGO_URL")
@Expose
private String lOGOURL;

public String getIMAGEURL() {
return iMAGEURL;
}

public void setIMAGEURL(String iMAGEURL) {
this.iMAGEURL = iMAGEURL;
}

public String getLOGOURL() {
return lOGOURL;
}

public void setLOGOURL(String lOGOURL) {
this.lOGOURL = lOGOURL;
}

}
