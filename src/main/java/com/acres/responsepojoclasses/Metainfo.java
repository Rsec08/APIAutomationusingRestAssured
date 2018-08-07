package com.acres.responsepojoclasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Metainfo {

@SerializedName("statusCode")
@Expose
private String statusCode;
@SerializedName("statusMessage")
@Expose
private String statusMessage;
@SerializedName("processing_time")
@Expose
private Double processingTime;
@SerializedName("99_ab")
@Expose
private String _99Ab;
@SerializedName("session_id")
@Expose
private Object sessionId;
@SerializedName("X-REQUEST-ID")
@Expose
private String xREQUESTID;

public String getStatusCode() {
return statusCode;
}

public void setStatusCode(String statusCode) {
this.statusCode = statusCode;
}

public String getStatusMessage() {
return statusMessage;
}

public void setStatusMessage(String statusMessage) {
this.statusMessage = statusMessage;
}

public Double getProcessingTime() {
return processingTime;
}

public void setProcessingTime(Double processingTime) {
this.processingTime = processingTime;
}

public String get99Ab() {
return _99Ab;
}

public void set99Ab(String _99Ab) {
this._99Ab = _99Ab;
}

public Object getSessionId() {
return sessionId;
}

public void setSessionId(Object sessionId) {
this.sessionId = sessionId;
}

public String getXREQUESTID() {
return xREQUESTID;
}

public void setXREQUESTID(String xREQUESTID) {
this.xREQUESTID = xREQUESTID;
}

}