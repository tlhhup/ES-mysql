package org.tlh.em.test;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author ping
 * @see https://www.elastic.co/guide/en/elasticsearch/client/java-rest/current/java-rest-low-usage-requests.html
 *
 */
public class ESTest {

	private RestClient restClient;

	@Before
	public void before(){
		restClient = RestClient.builder(new HttpHost("localhost", 9200, "http")).build();
	}
	
	@After
	public void after() throws Exception{
		if(restClient!=null){
			restClient.close();
		}
	}

	// http://blog.csdn.net/u012371450/article/details/51776505
	// https://stackoverflow.com/questions/31677563/connection-refused-error-on-elastic-search
	@Test
	public void query() {
		try {
			Response response = restClient.performRequest("GET", "/");
			
			System.out.println(response.getEntity().toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
