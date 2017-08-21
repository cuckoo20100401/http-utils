# http-utils

Sample http request utils.

## Supports

- http
- soap

## Usage

#### Example 1:
```java
HttpUtils httpUtils = new HttpUtils();
String message = httpUtils.reqServerByGet("http://www......com");
//String message = httpUtils.reqServerByPost("http://www......com");
//String message = httpUtils.reqServerByDelete("http://www......com");
System.out.println(message);
```

#### Example 2:
```java
HttpParamBuilder paramBuilder = new HttpParamBuilder();
paramBuilder.addParam("keyword", "自己");
paramBuilder.addParam("pageNo", "1");
paramBuilder.addParam("pageSize", "5");
String message = new HttpUtils().reqServerByGet("http://www......com", paramBuilder);
//String message = new HttpUtils().reqServerByPost("http://www......com", paramBuilder);
//String message = new HttpUtils().reqServerByDelete("http://www......com", paramBuilder);
System.out.println(message);
```

#### Example 3:
```java
HttpParamBuilder paramBuilder = new HttpParamBuilder();
paramBuilder.addParam("city", "shenzhen");
HttpHeaderBuilder headerBuilder = new HttpHeaderBuilder();
headerBuilder.addHeader("accept", "application/json");
headerBuilder.addHeader("api-key", "21a42808ac9c4d5276116a2ef3485625");
String message = new HttpUtils().reqServerByGet("http://www......com", paramBuilder, headerBuilder);
//String message = new HttpUtils().reqServerByPost("http://www......com", paramBuilder, headerBuilder);
//String message = new HttpUtils().reqServerByDelete("http://www......com", paramBuilder, headerBuilder);
System.out.println(message);
```
注：使用之前要导入libs目录中的jar文件，有关更多、更丰富的用法请参考测试类。