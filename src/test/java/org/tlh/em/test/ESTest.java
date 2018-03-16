package org.tlh.em.test;

import java.util.Collections;
import java.util.Map;

import org.apache.http.HttpHost;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.ResponseListener;
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

	// @see http://blog.csdn.net/u012371450/article/details/51776505
	// @see https://stackoverflow.com/questions/31677563/connection-refused-error-on-elastic-search
	// @see https://www.cnblogs.com/yidiandhappy/p/7714481.html
	// @see http://blog.csdn.net/winsonyuan/article/details/8625877
	// @see https://www.cnblogs.com/jager/p/5776655.html
	@Test
	public void query() {
		try {
			//同步调用
			//Response response = restClient.performRequest("GET", "/");
			//System.out.println(response.getEntity().toString());
			//异步调用
			restClient.performRequestAsync("GET", "/", new ResponseListener() {
				
				@Override
				public void onSuccess(Response response) {
					System.err.println(response);
				}
				
				@Override
				public void onFailure(Exception exception) {
					exception.printStackTrace();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void query1() throws Exception{
		Map<String, String> params = Collections.emptyMap();
		String jsonString = "{\"query\":{\"match\":{\"id\":\"1\"}}}";
		NStringEntity entity = new NStringEntity(jsonString, ContentType.APPLICATION_JSON);
		Response response = restClient.performRequest("GET", "/woniuxy/_search",params,entity);
		
		String string = EntityUtils.toString(response.getEntity());
		System.out.println(string);
	}

}
