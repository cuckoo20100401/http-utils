package org.cuckoo.http;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

/**
 * HTTP头部构造器
 * @author cuckoo20100401
 */
public class HttpHeaderBuilder {

	List<Header> headers = new ArrayList<Header>();
	
	public List<Header> addHeader(String name, String value){
		headers.add(new BasicHeader(name, value));
		return headers;
	}
	
	public Header[] getHeaders(){
		Header[] eg = new Header[]{};
		return headers.toArray(eg);
	}
}
