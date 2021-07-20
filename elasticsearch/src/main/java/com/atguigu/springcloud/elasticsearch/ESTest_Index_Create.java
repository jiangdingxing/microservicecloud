package com.atguigu.springcloud.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;

/**
 * @author jdx
 * @date 2021-07-18 17:40
 * @description: 创建es、索引
 */
public class ESTest_Index_Create {


    public static void main(String[] args) throws Exception {
        //创建es客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("106.52.176.209", 9286, "http"))
        );

        //创建索引
        CreateIndexRequest request = new CreateIndexRequest("user-jdx"); //创建索引名称
        CreateIndexResponse createIndexResponse =
                //TODO indices方法用于操作索引
                esClient.indices().create(request, RequestOptions.DEFAULT);
        //响应状态
        boolean acknowledged = createIndexResponse.isAcknowledged();
        System.out.println("索引操作： " + acknowledged);
        //关闭es客户端
        esClient.close();
    }
}
