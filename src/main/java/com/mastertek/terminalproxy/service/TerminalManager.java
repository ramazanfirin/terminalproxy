package com.mastertek.terminalproxy.service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Base64;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class TerminalManager implements TerminalInterface {
	
	@Autowired
	ObjectMapper objectMapper;
	
	HttpClient client = HttpClientBuilder.create().build();
	
	public HttpPost getHttpPost(String url,String body) {
		HttpPost httpPost = new HttpPost(url);
		StringEntity entity = new StringEntity(body, Charset.forName("UTF-8"));
		httpPost.setEntity(entity);
		
		String encoding = Base64.getEncoder().encodeToString(("admin:admin").getBytes());
		httpPost.setHeader(HttpHeaders.AUTHORIZATION, "Basic " + encoding);
		return httpPost;
	}
	
	public String calculateUrl(String ip, String port,String command) {
		return "http://" + ip + ":"+port+command;
	}
	
	public void checkResponse(HttpResponse response) throws ParseException, IOException {
		
		String result = checkHttpCode(response);
		checkJson(result);
		
	}
	
	public String checkHttpCode(HttpResponse response) throws ParseException, IOException {
		String result = EntityUtils.toString(response.getEntity());
		if (response.getStatusLine().getStatusCode() != 200)
			throw new RuntimeException("error on communication. status code :" + response.getStatusLine().getStatusCode());
		
		return result;
	}
	
	public void checkJson(String result) throws JsonProcessingException, IOException {
		JsonNode actualObj = objectMapper.readTree(result);
		Long code = actualObj.get("code").asLong();
		if(code!=200)
			throw new RuntimeException(result);
	}
}
