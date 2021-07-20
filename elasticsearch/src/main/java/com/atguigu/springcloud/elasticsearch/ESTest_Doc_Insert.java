package com.atguigu.springcloud.elasticsearch;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

/**
 * @author jdx
 * @date 2021-07-18 17:40
 * @description: 创建文档
 */
public class ESTest_Doc_Insert {


    public static void main(String[] args) throws Exception {
        //创建es客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("106.52.176.209", 9286, "http"))
        );
        //新增操作request
        IndexRequest request = new IndexRequest();
        request.index("user-jdx").id("1001");//将文档

        UserJdx userJdx = new UserJdx("姜定兴", 99, 1);
        String jsonString = JSONObject.toJSONString(userJdx);
        request.source(jsonString, XContentType.JSON);//标识数据源为json格式
        IndexResponse response = esClient.index(request, RequestOptions.DEFAULT);

        System.out.println(response.getResult());
        //关闭es客户端
        esClient.close();
    }
}
