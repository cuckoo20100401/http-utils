package org.cuckoo.http;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

/**
 * HTTP参数构造器
 * @author cuckoo20100401
 * @version 1.0
 */
public class HttpParamBuilder {

	List<NameValuePair> params = new ArrayList<NameValuePair>();
	
	public List<NameValuePair> addParam(String name, String value){
		params.add(new BasicNameValuePair(name, value));
		return params;
	}
	
	public String getQueryString(){
		return URLEncodedUtils.format(params, "UTF-8");
	}
	
	public List<NameValuePair> getParams(){
		return params;
	}
}
