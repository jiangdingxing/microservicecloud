package com.atguigu.springcloud.elasticsearch;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

/**
 * @author jdx
 * @date 2021-07-18 17:40
 * @description: 创建文档
 */
public class ESTest_Doc_BatchDelete {


    public static void main(String[] args) throws Exception {
        //创建es客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("106.52.176.209", 9286, "http"))
        );

        //批量删除doc
        BulkRequest request = new BulkRequest();
        request.add(new DeleteRequest().index("user-jdx").id("1001"));
        request.add(new DeleteRequest().index("user-jdx").id("1002"));
        request.add(new DeleteRequest().index("user-jdx").id("1003"));
        BulkResponse response = esClient.bulk(request, RequestOptions.DEFAULT);

        System.out.println(response.getTook());
        System.out.println(response.getItems());

        //关闭es客户端
        esClient.close();
    }
}
