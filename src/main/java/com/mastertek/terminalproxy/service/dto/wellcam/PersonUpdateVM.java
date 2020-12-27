package com.mastertek.terminalproxy.service.dto.wellcam;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonUpdateVM {

	String operator= "EditPerson";
	PersonInfoVM info = new PersonInfoVM();
	
	@JsonProperty("picinfo")
	String picinfo;
	
	
	
	public PersonUpdateVM(String deviceID) {
		super();
		info.setDeviceID(deviceID);
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public PersonInfoVM getInfo() {
		return info;
	}
	public void setInfo(PersonInfoVM info) {
		this.info = info;
	}
	public String getPicinfo() {
		return picinfo;
	}
	public void setPicinfo(String picinfo) {
		this.picinfo = picinfo;
	}	
	
}

