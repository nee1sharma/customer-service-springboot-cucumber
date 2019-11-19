package com.sharma.nks.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class FTRequestInterceptor implements ClientHttpRequestInterceptor{

	private Map<String, String> calls = new HashMap<>();

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		calls.put(request.getMethod().toString(), request.getURI().toString());
		return execution.execute(request, body);
	}

	public Map<String, String> getCalls() {
		return calls;
	}
}
