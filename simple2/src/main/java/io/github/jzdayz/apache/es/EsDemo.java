package io.github.jzdayz.apache.es;

import io.undertow.Undertow;
import io.undertow.io.Sender;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

@Slf4j
public class EsDemo {
    public static void main(String[] args) throws Exception {
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(
                new HttpHost("localhost", 9400, "http")
        ));
        System.out.println(get(client));;
    }

    private static String get(RestHighLevelClient client) throws Exception {
        GetRequest request = new GetRequest(
                "posts", "doc", "3"
        );
        GetResponse response = client.get(request, RequestOptions.DEFAULT);
        return response.getSourceAsString();
    }

    private static void index(RestHighLevelClient client) throws IOException {
        IndexRequest request = new IndexRequest(
                "posts",
                "doc",
                "3");
        String jsonString = "{" +
                "\"user\":\"dd!!!!\"" +
                "}";
        request.source(jsonString, XContentType.JSON);
        IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);
        System.out.println(indexResponse.status());
    }
}
