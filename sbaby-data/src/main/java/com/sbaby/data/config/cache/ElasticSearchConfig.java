package com.sbaby.data.config.cache;

import java.net.InetAddress;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

/**
 * 该处使用了java config模式连接elasticsearch数据库，注入并托管了ElasticsearchTemplate操作类，
 * 在其他地方可通过@Autowired获取到该类，操作数据库
 * @author Administrator
 *
 */
//@Configuration
public class ElasticSearchConfig {
	
	@Value("${elasticsearch.cluster.name}")
	private String clusterName;
	
	@Value("${elasticsearch.cluster.port}")
	private Integer port;
	
	@Value("${elasticsearch.cluster.host}")
	private String host;
	
	@Bean
    public ElasticsearchOperations elasticsearchTemplate() {
		ElasticsearchTemplate template = null;
		try {
			Settings settings = Settings.settingsBuilder()
			        .put("cluster.name", clusterName).build();
			TransportClient client = TransportClient.builder().settings(settings).build()
					.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), port));
			template = new ElasticsearchTemplate(client);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return template;
    }

}
