package com.atguigu.springcloud.elasticsearch;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

/**
 * @author jdx
 * @date 2021-07-18 17:40
 * @description: 创建文档
 */
public class ESTest_Doc_Update {


    public static void main(String[] args) throws Exception {
        //创建es客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("106.52.176.209", 9286, "http"))
        );
        UpdateRequest request = new UpdateRequest();
        request.index("user-jdx").id("1001");
        request.doc(XContentType.JSON, "age", "88");

        UpdateResponse response = esClient.update(request, RequestOptions.DEFAULT);

        System.out.println(response.getResult());
        //关闭es客户端
        esClient.close();
    }
}
