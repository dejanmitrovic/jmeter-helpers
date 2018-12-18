package jmeter.helpers

import org.apache.http.HttpHeaders
import org.apache.http.client.config.RequestConfig
import org.apache.http.client.methods.HttpUriRequest
import org.apache.http.client.methods.RequestBuilder
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.commons.validator.routines.*
import org.apache.http.util.EntityUtils
import org.apache.http.entity.StringEntity
import com.google.gson.Gson

/**
 * Wrapper for HTTP request related actions
 */
class Request {
    
    /**
     * Fetch response body for URI that has HTTP basic auth using GET method
     *
     * @param url URI resource to GET
     * @param body Request body
     * @param authUser username for basic HTTP auth
     * @param authPassword password for basic HTTP auth
     * @return Response body
     */
	static def getBodyBySendGetWithAuthorization(url, body, authUser, authPassword) throws IllegalArgumentException {
        if(!(new UrlValidator().isValid(url))) {
            throw new IllegalArgumentException("url parameter must be valid URI")
        }
		def requestConfig = RequestConfig.custom()
			.setConnectTimeout(2000)
			.setSocketTimeout(3000)
			.build()

		def entity = new StringEntity(new Gson().toJson(body), "UTF-8")
		def authHeader = "Basic " + Base64.getEncoder().encodeToString("$authUser:$authPassword".getBytes())

		def requestBuilder = RequestBuilder.get()
     		.setConfig(requestConfig)
          	.setUri(url)
          	.setHeader(HttpHeaders.AUTHORIZATION, authHeader)
          	.setHeader(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8")

			body.forEach({key, value -> requestBuilder.addParameter(key, value)})
     		def request = requestBuilder.build()

		HttpClientBuilder.create().build().withCloseable {httpClient ->
    		httpClient.execute(request).withCloseable {response ->
        		return "${(response.getEntity() != null ? EntityUtils.toString(response.getEntity()) : "")}"
         	}
     	}
	}
}
