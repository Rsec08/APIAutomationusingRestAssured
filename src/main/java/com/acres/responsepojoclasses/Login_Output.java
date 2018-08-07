package com.acres.responsepojoclasses;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Login_Output {

@SerializedName("user")
@Expose
private User user;
@SerializedName("attributes")
@Expose
private Attributes attributes;
@SerializedName("listings")
@Expose
private List<Object> listings = null;
@SerializedName("freeListingCount")
@Expose
private Object freeListingCount;
@SerializedName("subUserData")
@Expose
private List<Object> subUserData = null;
@SerializedName("dealerImageData")
@Expose
private DealerImageData dealerImageData;
@SerializedName("freeListingCountOwner")
@Expose
private String freeListingCountOwner;
@SerializedName("freeListingCountBuilder")
@Expose
private String freeListingCountBuilder;
@SerializedName("freeListingCountBroker")
@Expose
private String freeListingCountBroker;
@SerializedName("SUPER_SUB")
@Expose
private String sUPERSUB;
@SerializedName("verificationStatus")
@Expose
private Object verificationStatus;
@SerializedName("metainfo")
@Expose
private Metainfo metainfo;

public User getUser() {
return user;
}

public void setUser(User user) {
this.user = user;
}

public Attributes getAttributes() {
return attributes;
}

public void setAttributes(Attributes attributes) {
this.attributes = attributes;
}

public List<Object> getListings() {
return listings;
}

public void setListings(List<Object> listings) {
this.listings = listings;
}

public Object getFreeListingCount() {
return freeListingCount;
}

public void setFreeListingCount(Object freeListingCount) {
this.freeListingCount = freeListingCount;
}

public List<Object> getSubUserData() {
return subUserData;
}

public void setSubUserData(List<Object> subUserData) {
this.subUserData = subUserData;
}

public DealerImageData getDealerImageData() {
return dealerImageData;
}

public void setDealerImageData(DealerImageData dealerImageData) {
this.dealerImageData = dealerImageData;
}

public String getFreeListingCountOwner() {
return freeListingCountOwner;
}

public void setFreeListingCountOwner(String freeListingCountOwner) {
this.freeListingCountOwner = freeListingCountOwner;
}

public String getFreeListingCountBuilder() {
return freeListingCountBuilder;
}

public void setFreeListingCountBuilder(String freeListingCountBuilder) {
this.freeListingCountBuilder = freeListingCountBuilder;
}

public String getFreeListingCountBroker() {
return freeListingCountBroker;
}

public void setFreeListingCountBroker(String freeListingCountBroker) {
this.freeListingCountBroker = freeListingCountBroker;
}

public String getSUPERSUB() {
return sUPERSUB;
}

public void setSUPERSUB(String sUPERSUB) {
this.sUPERSUB = sUPERSUB;
}

public Object getVerificationStatus() {
return verificationStatus;
}

public void setVerificationStatus(Object verificationStatus) {
this.verificationStatus = verificationStatus;
}

public Metainfo getMetainfo() {
return metainfo;
}

public void setMetainfo(Metainfo metainfo) {
this.metainfo = metainfo;
}

}