package com.mastertek.terminalproxy.service;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.mastertek.terminalproxy.service.dto.wellcam.PersonDeleteVM;
import com.mastertek.terminalproxy.service.dto.wellcam.PersonInsertVM;
import com.mastertek.terminalproxy.service.dto.wellcam.PersonQueryVM;
import com.mastertek.terminalproxy.service.dto.wellcam.PersonUpdateVM;
import com.mastertek.terminalproxy.service.dto.wellcam.WellcamUtil;
import com.mastertek.terminalproxy.web.rest.vm.AddPersonVM;
import com.mastertek.terminalproxy.web.rest.vm.DeletePersonVM;
import com.mastertek.terminalproxy.web.rest.vm.EditPersonVM;
import com.mastertek.terminalproxy.web.rest.vm.QueryVM;


@Service
public class WellcamDeviceService extends TerminalManager{

	String addPersonCommand = "/action/AddPerson";
	String editPersonCommand = "/action/EditPerson";
	String deletePersonCommand = "/action/DeletePerson";
	String queryPersonCommand = "/action/SearchControl";
	
	@Override
	public void addPerson(AddPersonVM addPersonVM) throws Exception {
	
		PersonInsertVM personInsertVM = WellcamUtil.preparePersonImport(addPersonVM);
		
		HttpPost httpPost = getHttpPost(calculateUrl(addPersonVM.getIp(), addPersonVM.getPort(), addPersonCommand), objectMapper.writeValueAsString(personInsertVM));
		HttpResponse response = client.execute(httpPost);
		
		checkResponse(response);
	}

	@Override
	public void editPerson(EditPersonVM addPersonVM) throws Exception{
		PersonUpdateVM personInsertVM = WellcamUtil.preparePersonImport(addPersonVM);
		
		HttpPost httpPost = getHttpPost(calculateUrl(addPersonVM.getIp(), addPersonVM.getPort(), editPersonCommand), objectMapper.writeValueAsString(personInsertVM));
		HttpResponse response = client.execute(httpPost);

		checkResponse(response);

	}

	@Override
	public void deletePerson(DeletePersonVM deletePersonVM) throws Exception {
		PersonDeleteVM personInsertVM = WellcamUtil.preparePersonImport(deletePersonVM);
		
		HttpPost httpPost = getHttpPost(calculateUrl(deletePersonVM.getIp(), deletePersonVM.getPort(), deletePersonCommand), objectMapper.writeValueAsString(personInsertVM));
		HttpResponse response = client.execute(httpPost);
		
		checkResponse(response);

	}

	@Override
	public String query(QueryVM queryVM) throws Exception{
		PersonQueryVM personInsertVM = WellcamUtil.preparePersonImport(queryVM);
		
		HttpPost httpPost = getHttpPost(calculateUrl(queryVM.getIp(), queryVM.getPort(), queryPersonCommand), objectMapper.writeValueAsString(personInsertVM));
		HttpResponse response = client.execute(httpPost);
		
		String result = checkHttpCode(response);
		
		JsonNode actualObj = objectMapper.readTree(result);
		JsonNode code = actualObj.get("code");
		if(code!=null) {
			Long codeValue = code.asLong();
			if(codeValue==468) {
				return new String();
			}else
				throw new RuntimeException(result);
		}
		return result;
	}

}
