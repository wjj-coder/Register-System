package com.sys.util;

import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class HttpPostUtils { 
    
    public static String httpPost(String urlAddress, Map<String, String> dataMap){ 
    	
    	String response = "";
    	if (dataMap == null || dataMap.isEmpty()) {
    		return response;
    	}
    	HttpClient httpClient = new HttpClient();
    	
    	PostMethod method = new PostMethod(urlAddress);
    	method.addRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8");
    	
    	Set<String> keys = dataMap.keySet();
    	NameValuePair[] body = new NameValuePair[keys.size()];
    	int i = 0;
    	for (String key : keys) {
    		NameValuePair data = new NameValuePair(key, dataMap.get(key));
    		body[i++] = data;
		}
    	method.setRequestBody(body);
    	try {
			httpClient.executeMethod(method);
			response = method.getResponseBodyAsString();
		} catch (Exception e) {
			response = e.getMessage();
		} finally {
			if (method != null) {
				method.releaseConnection();
			}
		}
    	
    	return response;
    } 
    public static String httpGet(String urlAddress, Map<String, String> dataMap) {

		String response = "";
		if (dataMap == null || dataMap.isEmpty()) {
			return response;
		}
		HttpClient httpClient = new HttpClient();

		GetMethod method = new GetMethod(urlAddress);
		method.addRequestHeader("Content-type",
				"application/x-www-form-urlencoded; charset=UTF-8");
		method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
		try {
			httpClient.executeMethod(method);
			response = method.getResponseBodyAsString();
		} catch (Exception e) {
			response = e.getMessage();
		} finally {
			if (method != null) {
				method.releaseConnection();
			}
		}

		return response;
	}
public static String httpGethttpGet(String urlAddress){ 
    	
    	String response = "";
    	
    	HttpClient httpClient = new HttpClient();
    	
    	GetMethod method = new GetMethod(urlAddress);
    	method.addRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8");
    	method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT,  5000 );   
    	try {
			httpClient.executeMethod(method);
			response = method.getResponseBodyAsString();
		} catch (Exception e) {
			response = e.getMessage();
		} finally {
			if (method != null) {
				method.releaseConnection();
			}
		}
    	
    	return response;
    } 
}