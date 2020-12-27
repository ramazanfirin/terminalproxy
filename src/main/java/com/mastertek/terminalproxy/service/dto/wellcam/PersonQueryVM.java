package com.mastertek.terminalproxy.service.dto.wellcam;

public class PersonQueryVM {

	String operator= "SearchControl";
	PersonQueryInfoVM info = new PersonQueryInfoVM();
	
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public PersonQueryInfoVM getInfo() {
		return info;
	}
	public void setInfo(PersonQueryInfoVM info) {
		this.info = info;
	}
	

	
}
