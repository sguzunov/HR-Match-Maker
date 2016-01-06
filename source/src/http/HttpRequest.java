package http;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class HttpRequest {
	private HttpServletRequest servletRequest;
	private Map<String, String> headers;
	private String body;
	private RequestParser requestParser;

	public HttpRequest(HttpServletRequest servletRequest, RequestParser requestParser) {
		this.servletRequest = servletRequest;
		this.requestParser = requestParser;
		this.headers = this.requestParser.getHeaders(this.servletRequest);
		this.body = this.requestParser.getBody(this.servletRequest);
	}

	public String getHeaderValueByKey(String key) {
		String headerValue = this.headers.get(key);

		return headerValue;
	}

	public String getBody() {
		return this.body;
	}
}
