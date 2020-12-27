package com.mastertek.terminalproxy.service.dto.wellcam;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonInfoVM{
	@JsonProperty("DeviceID")
	String deviceID;
	
	@JsonProperty("PersonType")
	Long personType=0l;//0:	Whitelist 	1: blacklist
	
	@JsonProperty("CardType")
	Long cardType = 0l;
	
	@JsonProperty("Tempvalid")
	Long tempvalid=0l;
	
	@JsonProperty("IdType")
	Long idType = 0l;

	@JsonProperty("Name")
	String name;
	
	@JsonProperty("CustomizeID")
	String customizeID;
	
	@JsonProperty("IdCard")
	String idCardNo;
	
	public String getDeviceID() {
		return deviceID;
	}

	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}

	public Long getPersonType() {
		return personType;
	}

	public void setPersonType(Long personType) {
		this.personType = personType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCardType() {
		return cardType;
	}

	public void setCardType(Long cardType) {
		this.cardType = cardType;
	}

	public Long getTempvalid() {
		return tempvalid;
	}

	public void setTempvalid(Long tempvalid) {
		this.tempvalid = tempvalid;
	}

	public String getCustomizeID() {
		return customizeID;
	}

	public void setCustomizeID(String customizeID) {
		this.customizeID = customizeID;
	}

	public Long getIdType() {
		return idType;
	}

	public void setIdType(Long idType) {
		this.idType = idType;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	
}
