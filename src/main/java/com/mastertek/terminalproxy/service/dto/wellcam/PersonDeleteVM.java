package com.mastertek.terminalproxy.service.dto.wellcam;

public class PersonDeleteVM {

	String operator= "DeletePerson";
	PersonDeleteInfoVM info = new PersonDeleteInfoVM();
	
	
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public PersonDeleteInfoVM getInfo() {
		return info;
	}
	public void setInfo(PersonDeleteInfoVM info) {
		this.info = info;
	}
	
	
}
