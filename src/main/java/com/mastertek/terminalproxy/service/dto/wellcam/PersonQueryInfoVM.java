package com.mastertek.terminalproxy.service.dto.wellcam;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonQueryInfoVM{
	@JsonProperty("DeviceID")
	String deviceID;
	
	@JsonProperty("BeginTime")
	String beginTime ;
	
	@JsonProperty("EndTime")
	String endTime ;

	public String getDeviceID() {
		return deviceID;
	}

	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	
}
