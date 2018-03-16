package org.tlh.em.test;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpHost;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ESTest2 {

	private RestHighLevelClient client;

	@Before
	public void before() {
		client = new RestHighLevelClient(RestClient.builder(new HttpHost("192.168.64.145", 9200, "http")));
	}
	
	@After
	public void after() throws Exception{
		if(client!=null){
			client.close();
		}
	}
	
	@Test
	public void getRequest() throws Exception{
		GetRequest request=new GetRequest("woniuxy","product","1");
		
		GetResponse response = client.get(request);

		if(response.isExists()){
			System.out.println(response.getVersion());
			String source = response.getSourceAsString();
			System.out.println(source);
		}
	}
	
	@Test
	public void delRequest() throws Exception{
		DeleteRequest request=new DeleteRequest("woniuxy", "product", "1");
		
		DeleteResponse response = client.delete(request);
		System.out.println(response.status());
	}

	@Test
	public void update() throws Exception{
		UpdateRequest request=new UpdateRequest("woniuxy", "product", "2");
		//设置传递到执行脚本中的参数
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("count", 4);
		//设置执行脚本
		Script inline = new Script(ScriptType.INLINE, "painless", "ctx._source.price += params.count", parameters);  
		request.script(inline);

		client.update(request);
	}
	
}
