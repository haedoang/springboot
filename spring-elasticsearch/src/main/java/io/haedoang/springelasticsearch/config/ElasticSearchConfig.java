package io.haedoang.springelasticsearch.config;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class ElasticSearchConfig {

    @Value("${elasticsearch.host}")
    private String host;

    @Value("${elasticsearch.port}")
    private int port;

//    @Value("${elasticsearch.username}")
//    private String username;
//
//    @Value("${elasticsearch.password}")
//    private String password;

    @Bean
    public RestHighLevelClient client() {
//        BasicCredentialsProvider provider = new BasicCredentialsProvider();
//        provider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));

        return new RestHighLevelClient(
                RestClient.builder(new HttpHost(host, port))
//                        .setHttpClientConfigCallback(httpClientBuilder ->
//                                httpClientBuilder.setDefaultCredentialsProvider(provider))
        );
    }
}