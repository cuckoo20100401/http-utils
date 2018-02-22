package org.cuckoo.http;

import org.apache.http.impl.client.CloseableHttpClient;

/**
 * 自定义代码回调接口
 * @author cuckoo20100401
 */
public interface CustomCodeCallback {

	public String execute(CloseableHttpClient httpClient);
}
