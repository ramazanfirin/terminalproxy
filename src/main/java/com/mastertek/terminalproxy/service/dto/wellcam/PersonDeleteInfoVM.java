package com.mastertek.terminalproxy.service.dto.wellcam;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonDeleteInfoVM{
	@JsonProperty("DeviceID")
	String deviceID;
	
	@JsonProperty("IdType")
	Long idType = 0l;
	
	@JsonProperty("TotalNum")
	Long totalNum =1l;

	@JsonProperty("CustomizeID")
	String[] customizeID = new String[1];

	public String getDeviceID() {
		return deviceID;
	}

	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}

	public Long getIdType() {
		return idType;
	}

	public void setIdType(Long idType) {
		this.idType = idType;
	}

	public Long getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Long totalNum) {
		this.totalNum = totalNum;
	}

	public String[] getCustomizeID() {
		return customizeID;
	}

	public void setCustomizeID(String[] customizeID) {
		this.customizeID = customizeID;
	}
	
	
		
}
