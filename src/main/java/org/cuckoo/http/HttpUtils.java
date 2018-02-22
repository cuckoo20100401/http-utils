package org.cuckoo.http;

import java.io.IOException;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

/**
 * HTTP工具类
 * 此类是对Apache HttpComponents的封装，详情查看官网
 * 参考1：http://blog.csdn.net/jdluojing/article/details/7300428
 * 参考2：http://www.oschina.net/question/167679_158196?sort=time
 * @author cuckoo20100401
 */
public class HttpUtils {
	
	private static PoolingHttpClientConnectionManager connManager;
	
	private RequestConfig requestConfig;
	private boolean debug = false;
	
	static {
		connManager = new PoolingHttpClientConnectionManager();
		connManager.setMaxTotal(10000); //max connections
	}
	
	/**
	 * 构造函数：产品模式
	 */
	public HttpUtils(){}
	public HttpUtils(int timeout){
		requestConfig = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout).setConnectionRequestTimeout(timeout).build();
	}
	
	/**
	 * 构造函数：调试模式
	 * @param debug
	 */
	public HttpUtils(boolean debug){
		this.debug = debug;
	}
	public HttpUtils(boolean debug, int timeout){
		this.debug = debug;
		requestConfig = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout).setConnectionRequestTimeout(timeout).build();
	}
	
	/**
	 * 从池中获取HttpClient
	 * @return
	 */
	public CloseableHttpClient getHttpClient(){
		return HttpClients.custom().setConnectionManager(connManager).build();
	}
	
	/**
	 * 处理HTTP响应对象，获取message
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public String processHttpResponse(CloseableHttpResponse response) throws IOException{
		String message = null;
		HttpEntity httpEntity = response.getEntity();
		StatusLine responseStatusLine = response.getStatusLine();
		int responseStatusCode = responseStatusLine.getStatusCode();
		if(responseStatusCode >= 300){
			throw new HttpResponseException(responseStatusCode, responseStatusLine.getReasonPhrase());
		}
		if(httpEntity == null){
			throw new ClientProtocolException("Response contains no content");
		}
		message = EntityUtils.toString(httpEntity, "UTF-8");
		EntityUtils.consume(httpEntity);
		return message;
	}
	
	/**
	 * 通过GET方法请求服务器
	 * @param url
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public String reqServerByGet(String url) throws ClientProtocolException, IOException {
		String message = null;
		CloseableHttpResponse response = null;
		try {
			HttpGet httpGet = new HttpGet(url);
			httpGet.setConfig(requestConfig);
			response = (CloseableHttpResponse) this.getHttpClient().execute(httpGet);
			message = processHttpResponse(response);
		} finally {
			if(response != null) response.close();
		}
		return message;
	}
	
	/**
	 * 通过GET方法请求服务器
	 * @param url
	 * @param httpParamBuilder
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public String reqServerByGet(String url, HttpParamBuilder httpParamBuilder) throws ClientProtocolException, IOException {
		String message = null;
		CloseableHttpResponse response = null;
		try {
			HttpGet httpGet = new HttpGet(url+"?"+httpParamBuilder.getQueryString());
			httpGet.setConfig(requestConfig);
			response = (CloseableHttpResponse) this.getHttpClient().execute(httpGet);
			message = processHttpResponse(response);
		} finally {
			if(response != null) response.close();
		}
		return message;
	}
	
	/**
	 * 通过GET方法请求服务器
	 * @param url
	 * @param httpHeaderBuilder
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public String reqServerByGet(String url, HttpHeaderBuilder httpHeaderBuilder) throws ClientProtocolException, IOException {
		String message = null;
		CloseableHttpResponse response = null;
		try {
			HttpGet httpGet = new HttpGet(url);
			httpGet.setConfig(requestConfig);
			httpGet.setHeaders(httpHeaderBuilder.getHeaders());
			response = (CloseableHttpResponse) this.getHttpClient().execute(httpGet);
			message = processHttpResponse(response);
		} finally {
			if(response != null) response.close();
		}
		return message;
	}
	
	/**
	 * 通过GET方法请求服务器
	 * @param url
	 * @param httpParamBuilder
	 * @param httpHeaderBuilder
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public String reqServerByGet(String url, HttpParamBuilder httpParamBuilder, HttpHeaderBuilder httpHeaderBuilder) throws ClientProtocolException, IOException {
		String message = null;
		CloseableHttpResponse response = null;
		try {
			HttpGet httpGet = new HttpGet(url+"?"+httpParamBuilder.getQueryString());
			httpGet.setConfig(requestConfig);
			httpGet.setHeaders(httpHeaderBuilder.getHeaders());
			response = (CloseableHttpResponse) this.getHttpClient().execute(httpGet);
			message = processHttpResponse(response);
		} finally {
			if(response != null) response.close();
		}
		return message;
	}
	
	/**
	 * 通过POST方法请求服务器
	 * @param url
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public String reqServerByPost(String url) throws ClientProtocolException, IOException {
		String message = null;
		CloseableHttpResponse response = null;
		try {
			HttpPost httpPost = new HttpPost(url);
			httpPost.setConfig(requestConfig);
			response = (CloseableHttpResponse) this.getHttpClient().execute(httpPost);
			message = processHttpResponse(response);
		} finally {
			if(response != null) response.close();
		}
		return message;
	}
	
	/**
	 * 通过POST方法请求服务器
	 * @param url
	 * @param httpParamBuilder
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public String reqServerByPost(String url, HttpParamBuilder httpParamBuilder) throws ClientProtocolException, IOException {
		String message = null;
		CloseableHttpResponse response = null;
		try {
			HttpPost httpPost = new HttpPost(url);
			httpPost.setConfig(requestConfig);
			httpPost.setEntity(new UrlEncodedFormEntity(httpParamBuilder.getParams(), "UTF-8"));
			response = (CloseableHttpResponse) this.getHttpClient().execute(httpPost);
			message = processHttpResponse(response);
		} finally {
			if(response != null) response.close();
		}
		return message;
	}
	
	/**
	 * 通过POST方法请求服务器
	 * @param url
	 * @param httpHeaderBuilder
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public String reqServerByPost(String url, HttpHeaderBuilder httpHeaderBuilder) throws ClientProtocolException, IOException {
		String message = null;
		CloseableHttpResponse response = null;
		try {
			HttpPost httpPost = new HttpPost(url);
			httpPost.setConfig(requestConfig);
			httpPost.setHeaders(httpHeaderBuilder.getHeaders());
			response = (CloseableHttpResponse) this.getHttpClient().execute(httpPost);
			message = processHttpResponse(response);
			//debug code start
			if(this.debug){
				System.out.println("################### Request Header start ###################");
				Header[] headers = httpPost.getAllHeaders();
				for(Header header: headers){
					System.out.println(header.getName()+": "+header.getValue());
				}
				System.out.println("################### Request Header end ###################");
				System.out.println("################### Response Header start ###################");
				headers = response.getAllHeaders();
				for(Header header: headers){
					System.out.println(header.getName()+": "+header.getValue());
				}
				System.out.println("################### Response Header end ###################");
			}
			//debug code end
		} finally {
			if(response != null) response.close();
		}
		return message;
	}
	
	/**
	 * 通过POST方法请求服务器
	 * @param url
	 * @param httpParamBuilder
	 * @param httpHeaderBuilder
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public String reqServerByPost(String url, HttpParamBuilder httpParamBuilder, HttpHeaderBuilder httpHeaderBuilder) throws ClientProtocolException, IOException {
		String message = null;
		CloseableHttpResponse response = null;
		try {
			HttpPost httpPost = new HttpPost(url);
			httpPost.setConfig(requestConfig);
			httpPost.setEntity(new UrlEncodedFormEntity(httpParamBuilder.getParams(), "UTF-8"));
			httpPost.setHeaders(httpHeaderBuilder.getHeaders());
			response = (CloseableHttpResponse) this.getHttpClient().execute(httpPost);
			message = processHttpResponse(response);
			//debug code start
			if(this.debug){
				System.out.println("################### Request Header start ###################");
				Header[] headers = httpPost.getAllHeaders();
				for(Header header: headers){
					System.out.println(header.getName()+": "+header.getValue());
				}
				System.out.println("################### Request Header end ###################");
				System.out.println("################### Response Header start ###################");
				headers = response.getAllHeaders();
				for(Header header: headers){
					System.out.println(header.getName()+": "+header.getValue());
				}
				System.out.println("################### Response Header end ###################");
			}
			//debug code end
		} finally {
			if(response != null) response.close();
		}
		return message;
	}
	
	/**
	 * 通过POST方法请求服务器
	 * @param url 请求地址
	 * 					Restful示例：http://120.25.63.58:1816/api/v1/userAuth
	 * 					Soap示例：http://125.88.60.241:8003/AccessPortal/wifiaccess?WSDL
	 * @param paramType 参数类型：JSON、XML
	 * @param paramValue 参数值（即JSON或XML字符串）
	 * @notice 此方法用于WebService中的Soap请求和Restful请求，且参数必须以JSON或XML加入请求体传递的情况
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public String reqServerByPost(String url, String paramType, String paramValue) throws ClientProtocolException, IOException {
		
		String message = null;
		CloseableHttpResponse response = null;
		
		try {
			
			StringEntity stringEntity = new StringEntity(paramValue);
			if(paramType.toUpperCase().equals("JSON")){
				stringEntity.setContentType("text/json;charset=utf-8");
			}else if(paramType.toUpperCase().equals("XML")){
				stringEntity.setContentType("text/xml;charset=utf-8");
			}
			
			HttpPost httpPost = new HttpPost(url);
			httpPost.setConfig(requestConfig);
			httpPost.setEntity(stringEntity);
			if(paramType.toUpperCase().equals("JSON")){
				httpPost.addHeader(HTTP.CONTENT_TYPE, "text/json;charset=utf-8");
			}else if(paramType.toUpperCase().equals("XML")){
				httpPost.addHeader(HTTP.CONTENT_TYPE, "text/xml;charset=utf-8");
			}
			httpPost.addHeader("Accept-Charset", "UTF-8");
			response = (CloseableHttpResponse) this.getHttpClient().execute(httpPost);
			message = processHttpResponse(response);
			
			//debug code start
			if(this.debug){
				System.out.println("################### Request Header start ###################");
				Header[] headers = httpPost.getAllHeaders();
				for(Header header: headers){
					System.out.println(header.getName()+": "+header.getValue());
				}
				System.out.println("################### Request Header end ###################");
				System.out.println("################### Response Header start ###################");
				headers = response.getAllHeaders();
				for(Header header: headers){
					System.out.println(header.getName()+": "+header.getValue());
				}
				System.out.println("################### Response Header end ###################");
			}
			//debug code end
		} finally {
			if(response != null) response.close();
		}
		return message;
	}
	
	/**
	 * 通过SOAP协议请求服务器
	 * @param wsdlLocation wsdl文件地址
	 * @param targetNamespace 命名空间
	 * @param reqMsgName 请求消息名称（即方法名）
	 * @param params 请求参数，示例：params.put("paramName", "paramValue");
	 * @param results 响应结果，即要获取的结果字段名称，结果字段的值先填写成null，此函数会自动填充值的，并且把填充好的map作为结果返回，此函数会默认放入faultcode和faultstring字段的值，可以根据此字段的值来判断发生的其它类型的服务器错误，比如：faultcode=soap:Client;faultstring=Unmarshalling Error: Not a number: ?
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public Map<String, Object> reqServerBySoap(String wsdlLocation, String targetNamespace, String reqMsgName, Map<String, Object> params, Map<String, Object> results) throws ClientProtocolException, IOException {
		
		CloseableHttpResponse response = null;
		
		try {
			
			String reqMsgXml = HttpSoapUtils.createReqMsg(targetNamespace, reqMsgName, params);
			StringEntity stringEntity = new StringEntity(reqMsgXml);
			stringEntity.setContentType("text/xml;charset=utf-8");
			
			HttpPost httpPost = new HttpPost(wsdlLocation);
			httpPost.setConfig(requestConfig);
			httpPost.setEntity(stringEntity);
			httpPost.addHeader(HTTP.CONTENT_TYPE, "text/xml;charset=utf-8");
			httpPost.addHeader("Accept-Charset", "UTF-8");
			response = (CloseableHttpResponse) this.getHttpClient().execute(httpPost);
			String resMsgXml = processHttpResponse(response);
			results = HttpSoapUtils.parseResMsg(resMsgXml, results);
			
			//debug code start
			if(this.debug){
				System.out.println("################### Request message content start ###################");
				System.out.println(reqMsgXml);
				System.out.println("################### Request message content end ###################");
				System.out.println("################### Response message content start ###################");
				System.out.println(resMsgXml);
				System.out.println("################### Response message content end ###################");
				System.out.println("################### Request Header start ###################");
				Header[] headers = httpPost.getAllHeaders();
				for(Header header: headers){
					System.out.println(header.getName()+": "+header.getValue());
				}
				System.out.println("################### Request Header end ###################");
				System.out.println("################### Response Header start ###################");
				headers = response.getAllHeaders();
				for(Header header: headers){
					System.out.println(header.getName()+": "+header.getValue());
				}
				System.out.println("################### Response Header end ###################");
			}
			//debug code end
		} finally {
			if(response != null) response.close();
		}
		return results;
	}
	
	/**
	 * 通过DELETE方法请求服务器
	 * @param url
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public String reqServerByDelete(String url) throws ClientProtocolException, IOException{
		String message = null;
		CloseableHttpResponse response = null;
		try {
			HttpDelete httpDelete = new HttpDelete(url);
			httpDelete.setConfig(requestConfig);
			response = (CloseableHttpResponse) this.getHttpClient().execute(httpDelete);
			message = processHttpResponse(response);
		} finally {
			if(response != null) response.close();
		}
		return message;
	}
	
	/**
	 * 通过DELETE方法请求服务器
	 * @param url
	 * @param httpParamBuilder
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public String reqServerByDelete(String url, HttpParamBuilder httpParamBuilder) throws ClientProtocolException, IOException{
		String message = null;
		CloseableHttpResponse response = null;
		try {
			HttpDelete httpDelete = new HttpDelete(url+"?"+httpParamBuilder.getQueryString());
			httpDelete.setConfig(requestConfig);
			response = (CloseableHttpResponse) this.getHttpClient().execute(httpDelete);
			message = processHttpResponse(response);
		} finally {
			if(response != null) response.close();
		}
		return message;
	}
	
	/**
	 * 通过DELETE方法请求服务器
	 * @param url
	 * @param httpHeaderBuilder
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public String reqServerByDelete(String url, HttpHeaderBuilder httpHeaderBuilder) throws ClientProtocolException, IOException{
		String message = null;
		CloseableHttpResponse response = null;
		try {
			HttpDelete httpDelete = new HttpDelete(url);
			httpDelete.setConfig(requestConfig);
			httpDelete.setHeaders(httpHeaderBuilder.getHeaders());
			response = (CloseableHttpResponse) this.getHttpClient().execute(httpDelete);
			message = processHttpResponse(response);
		} finally {
			if(response != null) response.close();
		}
		return message;
	}
	
	/**
	 * 通过DELETE方法请求服务器
	 * @param url
	 * @param httpParamBuilder
	 * @param httpHeaderBuilder
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public String reqServerByDelete(String url, HttpParamBuilder httpParamBuilder, HttpHeaderBuilder httpHeaderBuilder) throws ClientProtocolException, IOException{
		String message = null;
		CloseableHttpResponse response = null;
		try {
			HttpDelete httpDelete = new HttpDelete(url+"?"+httpParamBuilder.getQueryString());
			httpDelete.setConfig(requestConfig);
			httpDelete.setHeaders(httpHeaderBuilder.getHeaders());
			response = (CloseableHttpResponse) this.getHttpClient().execute(httpDelete);
			message = processHttpResponse(response);
		} finally {
			if(response != null) response.close();
		}
		return message;
	}
	
	/**
	 * 通过自定义方式请求服务器
	 * 通过回调传递进来一个CloseableHttpClient对象，让用户自定义请求方法体
	 * @param callback
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public String reqServerByCustom(CustomCodeCallback callback) throws ClientProtocolException, IOException {
		return callback.execute(this.getHttpClient());
	}
}
