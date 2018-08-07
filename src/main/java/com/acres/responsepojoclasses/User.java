package com.acres.responsepojoclasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

@SerializedName("name")
@Expose
private String name;
@SerializedName("email")
@Expose
private String email;
@SerializedName("primaryMobile")
@Expose
private String primaryMobile;
@SerializedName("primaryLandline")
@Expose
private String primaryLandline;
@SerializedName("secondaryMobiles")
@Expose
private String secondaryMobiles;
@SerializedName("secondaryLandlines")
@Expose
private String secondaryLandlines;
@SerializedName("countryCode")
@Expose
private String countryCode;
@SerializedName("cityCode")
@Expose
private String cityCode;
@SerializedName("altCountryCode")
@Expose
private String altCountryCode;
@SerializedName("altCityCode")
@Expose
private String altCityCode;
@SerializedName("class")
@Expose
private String _class;
@SerializedName("superProfileId")
@Expose
private String superProfileId;

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getEmail() {
return email;
}

public void setEmail(String email) {
this.email = email;
}

public String getPrimaryMobile() {
return primaryMobile;
}

public void setPrimaryMobile(String primaryMobile) {
this.primaryMobile = primaryMobile;
}

public String getPrimaryLandline() {
return primaryLandline;
}

public void setPrimaryLandline(String primaryLandline) {
this.primaryLandline = primaryLandline;
}

public String getSecondaryMobiles() {
return secondaryMobiles;
}

public void setSecondaryMobiles(String secondaryMobiles) {
this.secondaryMobiles = secondaryMobiles;
}

public String getSecondaryLandlines() {
return secondaryLandlines;
}

public void setSecondaryLandlines(String secondaryLandlines) {
this.secondaryLandlines = secondaryLandlines;
}

public String getCountryCode() {
return countryCode;
}

public void setCountryCode(String countryCode) {
this.countryCode = countryCode;
}

public String getCityCode() {
return cityCode;
}

public void setCityCode(String cityCode) {
this.cityCode = cityCode;
}

public String getAltCountryCode() {
return altCountryCode;
}

public void setAltCountryCode(String altCountryCode) {
this.altCountryCode = altCountryCode;
}

public String getAltCityCode() {
return altCityCode;
}

public void setAltCityCode(String altCityCode) {
this.altCityCode = altCityCode;
}

public String getClass_() {
return _class;
}

public void setClass_(String _class) {
this._class = _class;
}

public String getSuperProfileId() {
return superProfileId;
}

public void setSuperProfileId(String superProfileId) {
this.superProfileId = superProfileId;
}

}