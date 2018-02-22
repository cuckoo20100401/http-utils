package org.cuckoo.http;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
/*import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;

import com.cuckoo.http.CustomCodeCallback;
import com.cuckoo.http.HttpSoapUtils;*/
import org.cuckoo.http.HttpUtils;

public class Main_HttpUtils {
	
	public static void main(String[] args) throws ClientProtocolException, IOException {
		
		/*Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", "Joy");
		params.put("age", 27);
		params.put("sex", null);
		//String xmlContent = HttpSoapUtils.createReqMsg("Http://www.namespace.com", "findArticle", null);
		String xmlContent = HttpSoapUtils.createReqMsg("Http://www.namespace.com", "findArticle", params);
		System.out.println(xmlContent);*/
		
		HttpUtils httpUtils = new HttpUtils(true);
		
		String message = httpUtils.reqServerByGet("http://120.24.79.127:8081/ws/app/public/getMaxType");
		System.out.println(message);
		
		/*String message = httpUtils.reqServerByGet("http://120.24.79.127:8081/ws/app/public/getCourses?keyword=简历&maxTypeId=4028801244ce068f0144ce0e9ae02000&pageNo=1&pageSize=5");
		System.out.println(message);*/
		
		/*HttpParamBuilder paramBuilder = new HttpParamBuilder();
		paramBuilder.addParam("keyword", "自己");
		paramBuilder.addParam("maxTypeId", "4028801244ce068f0144ce0e9ae02000");
		paramBuilder.addParam("pageNo", "1");
		paramBuilder.addParam("pageSize", "5");
		String message = httpUtils.reqServerByGet("http://120.24.79.127:8081/ws/app/public/getCourses", paramBuilder);
		System.out.println(message);*/
		
		/*HttpHeaderBuilder headerBuilder = new HttpHeaderBuilder();
		headerBuilder.addHeader("accept", "application/json");
		headerBuilder.addHeader("content-type", "application/json");
		headerBuilder.addHeader("apix-key", "21a42808ac9c4d5276116a2ef341d817");
		String message = httpUtils.reqServerByGet("http://a.apix.cn/heweather/x3/free/weather?city=shenzhen", headerBuilder);
		System.out.println(message);*/
		
		/*HttpParamBuilder paramBuilder = new HttpParamBuilder();
		paramBuilder.addParam("city", "shenzhen");
		HttpHeaderBuilder headerBuilder = new HttpHeaderBuilder();
		headerBuilder.addHeader("accept", "application/json");
		headerBuilder.addHeader("content-type", "application/json");
		headerBuilder.addHeader("apix-key", "21a42808ac9c4d5276116a2ef341d817");
		String message = httpUtils.reqServerByGet("http://a.apix.cn/heweather/x3/free/weather", paramBuilder, headerBuilder);
		System.out.println(message);*/
		
		/*String message = httpUtils.reqServerByCustom(new CustomCodeCallback() {
			public String execute(CloseableHttpClient httpClient) {
				String responseStatusLine = null;
				CloseableHttpResponse response = null;
				try {
					HttpGet httpGet = new HttpGet("http://www.163.com/");
					response = (CloseableHttpResponse) httpClient.execute(httpGet);
					responseStatusLine = response.getStatusLine().toString();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if(response != null)
						try {
							response.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
				}
				return responseStatusLine;
			}
		});
		System.out.println(message);*/
		
		/*StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:gew=\"http://gewifi.eshore.cn\">");
		sb.append("<soapenv:Header/>");
		sb.append("<soapenv:Body>");
		sb.append("<gew:LoginRequest>");
		sb.append("<workFlowNo>888</workFlowNo>");
		sb.append("<systemType>593</systemType>");
		sb.append("<userName>13113026652</userName>");
		sb.append("<password>123456</password>");
		sb.append("<userIp>113.118.239.137</userIp>");
		sb.append("</gew:LoginRequest>");
		sb.append("</soapenv:Body>");
		sb.append("</soapenv:Envelope>");
		String msg = httpUtils.reqServerByPost("http://125.88.60.241:8003/AccessPortal/wifiaccess?WSDL", "XML", sb.toString());
		System.out.println(msg);*/
		
		/*String paramJson = "{\"username\":\"13122222224\",\"sign\":\"3EDADE4C0B092EF09CD2AD8317AB4F2B\"}";
		String msg = httpUtils.reqServerByPost("http://120.25.63.58:1816/api/v1/userIsExist", "JSON", paramJson);
		System.out.println(msg);*/
		
		/*Map<String, Object> params = new HashMap<String, Object>();
		params.put("workFlowNo", "888");
		params.put("systemType", "593");
		params.put("userName", "18855551111");
		params.put("password", "1234");
		params.put("userIp", "192.168.1.2");
		params.put("nasIp", null);
		params.put("ssid", null);
		Map<String, Object> results = new HashMap<String, Object>();
		results.put("resultCode", null);
		results.put("description", null);
		results = httpUtils.reqServerBySoap("http://125.88.60.241:8003/AccessPortal/wifiaccess?WSDL", "http://gewifi.eshore.cn", "LoginRequest", params, results);
		System.out.println("resultCode ="+results.get("resultCode"));
		System.out.println("description ="+results.get("description"));
		System.out.println("faultcode ="+results.get("faultcode"));
		System.out.println("faultstring ="+results.get("faultstring"));*/
	}
}
