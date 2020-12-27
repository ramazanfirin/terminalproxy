package com.mastertek.terminalproxy.service.dto.wellcam;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Base64;

import javax.imageio.ImageIO;

import com.mastertek.terminalproxy.web.rest.vm.AddPersonVM;
import com.mastertek.terminalproxy.web.rest.vm.DeletePersonVM;
import com.mastertek.terminalproxy.web.rest.vm.EditPersonVM;
import com.mastertek.terminalproxy.web.rest.vm.QueryVM;



public class WellcamUtil {

	public static PersonInsertVM preparePersonImport(String deviceId,String name,String personId,String cardNo,String picinfo) {
		PersonInsertVM result = new PersonInsertVM(deviceId);
		result.getInfo().setName(name);
		result.getInfo().setCustomizeID(personId);
		result.getInfo().setIdCardNo(cardNo);
		result.getInfo().setDeviceID(deviceId);
		result.setPicinfo(picinfo);
		
		return result;
	}
	
	public static PersonInsertVM preparePersonImport(AddPersonVM addPersonVM) {
		PersonInsertVM result = new PersonInsertVM(addPersonVM.getDeviceId());
		result.getInfo().setName(addPersonVM.getPersonName());
		result.getInfo().setCustomizeID(addPersonVM.getPersonId());
		result.getInfo().setIdCardNo(addPersonVM.getIdCardNo());
		result.getInfo().setDeviceID(addPersonVM.getDeviceId());
		result.setPicinfo(addPersonVM.getPicture());
		
		return result;
	}
	
	public static PersonUpdateVM preparePersonImport(EditPersonVM addPersonVM) {
		PersonUpdateVM result = new PersonUpdateVM(addPersonVM.getDeviceId());
		result.getInfo().setName(addPersonVM.getPersonName());
		result.getInfo().setCustomizeID(addPersonVM.getPersonId());
		result.getInfo().setIdCardNo(addPersonVM.getIdCardNo());
		result.getInfo().setDeviceID(addPersonVM.getDeviceId());
		result.setPicinfo(addPersonVM.getPicture());
		
		return result;
	}
	
	public static PersonDeleteVM preparePersonImport(DeletePersonVM addPersonVM) {
		PersonDeleteVM result = new PersonDeleteVM();
		result.getInfo().setDeviceID(addPersonVM.getDeviceId());
		result.getInfo().getCustomizeID()[0]=addPersonVM.getPersonId();
		
		return result;
	}
	
	public static PersonQueryVM preparePersonImport(QueryVM queryVM) {
		PersonQueryVM result = new PersonQueryVM();
		result.getInfo().setDeviceID(queryVM.getDeviceId());
		result.getInfo().setBeginTime(queryVM.getStartDate());
		result.getInfo().setEndTime(queryVM.getEndDate());
		
		return result;
	}
	
	public static String encodeFileToBase64Binary( byte[] bytes) {
    	return "data:image/jpeg;base64,"+Base64.getEncoder().encodeToString(bytes);
    }
	
	public static byte[]  imageToByteArray(String path) throws Exception{
	      BufferedImage bImage = ImageIO.read(new File(path));
	      ByteArrayOutputStream bos = new ByteArrayOutputStream();
	      ImageIO.write(bImage, "jpg", bos );
	      byte [] data = bos.toByteArray();
	      return data;
	   }
}
