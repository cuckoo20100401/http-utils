package org.cuckoo.http;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 * SOAP协议请求工具
 * @author cuckoo20100401
 */
public class HttpSoapUtils {

	/**
	 * 生成SOAP请求消息
	 * @param targetNamespace 命名空间
	 * @param reqMsgName 请求消息名称（即方法名）
	 * @param params 请求参数，示例：params.put("paramName", "paramValue");，无参数时可设为null
	 * @return
	 */
	public static String createReqMsg(String targetNamespace, String reqMsgName, Map<String, Object> params){
		
		// 创建文档框架
		Document doc = new Document();
		
		// 创建文档元素
		//创建文档元素：soap信封
		Element soapEnvelope = new Element("Envelope");
		soapEnvelope.setNamespace(Namespace.getNamespace("soapenv", "http://schemas.xmlsoap.org/soap/envelope/"));
		soapEnvelope.addNamespaceDeclaration(Namespace.getNamespace("xyz", targetNamespace));
		//创建文档元素：soap头部
		Element soapHeader = new Element("Header");
		soapHeader.setNamespace(Namespace.getNamespace("soapenv", "http://schemas.xmlsoap.org/soap/envelope/"));
		//创建文档元素：soap体
		Element soapBody = new Element("Body");
		soapBody.setNamespace(Namespace.getNamespace("soapenv", "http://schemas.xmlsoap.org/soap/envelope/"));
		//创建文档元素：soap请求消息名
		Element soapReqMsg = new Element(reqMsgName);
		soapReqMsg.setNamespace(Namespace.getNamespace("xyz", targetNamespace));
		//创建文档元素：soap请求消息参数
		if(params != null){
			for(String paramName: params.keySet()){
				Object paramValue = params.get(paramName);
				if(paramValue != null){
					Element soapReqParam = new Element(paramName).setText(paramValue+"");
					soapReqMsg.addContent(soapReqParam);
				}
			}
		}
		
		// 添加所有元素到文档框架
		soapEnvelope.addContent(soapHeader);
		soapEnvelope.addContent(soapBody);
		soapBody.addContent(soapReqMsg);
		doc.setRootElement(soapEnvelope);
		
		// 输出结果
		Format format = Format.getPrettyFormat();
        XMLOutputter xmlOutputter = new XMLOutputter(format);
        //xmlOutputter.output(doc, new FileOutputStream("/home/cuckoo/Documents/sss.xml")); //输出文件
        String xmlContent = xmlOutputter.outputString(doc); //输出字符串
        return xmlContent;
	}
	
	/**
	 * 解析SOAP响应消息
	 * @param resMsgStr SOAP响应消息字符串
	 * @param results 响应结果，即要获取的结果字段名称，结果字段的值先填写成null，此函数会自动填充值的，并且把填充好的map作为结果返回，此函数会默认放入faultcode和faultstring字段的值，可以根据此字段的值来判断发生的其它类型的服务器错误，比如：faultcode=soap:Client;faultstring=Unmarshalling Error: Not a number: ?
	 * @return
	 */
	public static Map<String, Object> parseResMsg(String resMsgStr, Map<String, Object> results){
		if(results != null){
			results.put("faultcode", null);
			results.put("faultstring", null);
			for(String resultName: results.keySet()){
				StringBuilder regex = new StringBuilder();
				regex.append("<").append(resultName).append(">").append("(.*)").append("</").append(resultName).append(">");
				Pattern pattern = Pattern.compile(regex.toString());
				//Pattern pattern = Pattern.compile(regex.toString(), Pattern.CASE_INSENSITIVE); //忽略大小写
				Matcher matcher = pattern.matcher(resMsgStr);
				if(matcher.find()){
					String resultValue = matcher.group(1);
					results.put(resultName, resultValue);
				}
			}
		}
		return results;
	}
}
