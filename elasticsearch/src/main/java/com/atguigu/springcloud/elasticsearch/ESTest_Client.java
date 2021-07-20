package com.atguigu.springcloud.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

/**
 * @author jdx
 * @date 2021-07-18 17:32
 * @description:
 */
public class ESTest_Client {

    public static void main(String[] args) throws Exception{
        //创建es客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("113.108.157.29",9286,"http"))
        );
        //关闭es客户端
        esClient.close();
    }
}
