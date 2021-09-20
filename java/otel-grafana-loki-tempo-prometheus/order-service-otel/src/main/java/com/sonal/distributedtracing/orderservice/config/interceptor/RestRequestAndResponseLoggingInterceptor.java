package com.sonal.distributedtracing.orderservice.config.interceptor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class RestRequestAndResponseLoggingInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] requestBody, ClientHttpRequestExecution executionJointPoint) throws IOException {
        
    	logRequest(request, requestBody);
        
        ClientHttpResponse response = executionJointPoint.execute(request, requestBody);
        
        logResponse(response);
        
        return response;
    }
    
    private void logRequest(HttpRequest request, byte[] requestBody) {
		
		if (log.isDebugEnabled()) {
			
			StringBuilder requestDetails = new StringBuilder();
			
			requestDetails.append("URI         = " + request.getURI() + " \n");
			requestDetails.append("Method      = " + request.getMethod() + "\n");
			requestDetails.append("Headers     = " + request.getHeaders() + "\n");
			requestDetails.append("Request body = " + new String(requestBody, StandardCharsets.UTF_8));
			
			log.debug(requestDetails.toString());
		}
		
	}

	private void logResponse(ClientHttpResponse response) throws IOException {
		
		Integer httpStatusCode = response.getStatusCode().value();
		
		log.info("HttpStatusCode=" + httpStatusCode);
		
		if (log.isDebugEnabled()) {
        	
            InputStreamReader isr = new InputStreamReader(response.getBody(), StandardCharsets.UTF_8);
            
            String responseBody = new BufferedReader(isr).lines()
            									         .collect(Collectors.joining("\n"));
            StringBuilder responseDetails = new StringBuilder();
            
            responseDetails.append("Status code   = " + httpStatusCode + "\n");
            responseDetails.append("Headers       = " +  response.getHeaders() + "\n");
            responseDetails.append("Response body = " +  responseBody);
            
            log.debug(responseDetails.toString());
        }
	}

	
}