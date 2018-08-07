package com.acres.responsepojoclasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attributes {

@SerializedName("profileID")
@Expose
private String profileID;

public String getProfileID() {
return profileID;
}

public void setProfileID(String profileID) {
this.profileID = profileID;
}

}
