package com.mastertek.terminalproxy.service;

import com.mastertek.terminalproxy.web.rest.vm.AddPersonVM;
import com.mastertek.terminalproxy.web.rest.vm.DeletePersonVM;
import com.mastertek.terminalproxy.web.rest.vm.EditPersonVM;
import com.mastertek.terminalproxy.web.rest.vm.QueryVM;

public interface TerminalInterface {

	public void addPerson(AddPersonVM addPersonVM) throws Exception;

	public void editPerson(EditPersonVM editPersonVM) throws Exception;
	
	public void deletePerson(DeletePersonVM deletePersonVM) throws Exception;
	
	public String query(QueryVM queryVM) throws Exception;
}
