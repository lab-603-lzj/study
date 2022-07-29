package other;


import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;

import java.util.Map;

/**
 * @Description:
 * @author: lzj
 * @date: 2022年07月08日 15:38
 */
public class httpClient {

	private HttpResponse sendHttp(String url, Map<String, String> headers, String jsonStr, String errorInfo, Method method) {
		HttpResponse response = null;
		try {
			//发送请求
			response = new HttpRequest(url)
					// 头信息
					.headerMap(headers, true)
					// json格式请求
					.body(jsonStr)
					// 超时时间（连接超时和读取超时是一样的）
					.timeout(DEFAULT_TIMEOUT)
					.method(method)
					.execute();
		} catch (Exception e) {
			logErrorInfo(url, headers, jsonStr, response, errorInfo, true, e);
			throw e;
		}
		return response;
	}
}

