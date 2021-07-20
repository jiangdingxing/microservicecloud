package com.atguigu.springcloud.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;

/**
 * @author jdx
 * @date 2021-07-18 17:40
 * @description: 创建es、索引
 */
public class ESTest_Index_Delete {




    public static void main(String[] args) throws Exception {
        //创建es客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("106.52.176.209", 9286, "http"))
        );


        DeleteIndexRequest request = new DeleteIndexRequest("user-jdx");
        //TODO indices方法用于操作索引
        AcknowledgedResponse response = esClient.indices().delete(request, RequestOptions.DEFAULT);

        System.out.println(response.isAcknowledged());

        //关闭es客户端
        esClient.close();
    }
}
