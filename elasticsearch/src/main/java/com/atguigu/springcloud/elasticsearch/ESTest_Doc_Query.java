package com.atguigu.springcloud.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.MaxAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.MinAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.hibernate.validator.resourceloading.AggregateResourceBundleLocator;

import javax.naming.directory.SearchResult;

/**
 * @author jdx
 * @date 2021-07-18 17:40
 * @description: 高级查询
 */
public class ESTest_Doc_Query {


    public static void main(String[] args) throws Exception {
        //创建es客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("106.52.176.209", 9286, "http"))
        );
        //TODO 1. 查询索引中全部数据
        SearchRequest request = new SearchRequest();
        request.indices("user_jdx");
        request.source(new SearchSourceBuilder().query(QueryBuilders.matchAllQuery()));
        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();
        System.out.println(hits.getTotalHits());
        System.out.println(response.getTook());
        for (SearchHit hit : hits) {
            System.out.println("全部查询--->" + hit.getSourceAsString());
        }

        //TODO 2.条件查询
        request.source(new SearchSourceBuilder().query(QueryBuilders.termQuery("name", "zhangsan")));
        SearchResponse response2 = esClient.search(request, RequestOptions.DEFAULT);
        SearchHits hits2 = response2.getHits();
        System.out.println(hits2.getTotalHits());
        System.out.println(response2.getTook());
        for (SearchHit hit : hits2) {
            System.out.println("条件查询--->" + hit.getSourceAsString());
        }

        //TODO 3分页查询&排序查询&过滤字段
        SearchSourceBuilder builder = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
        //(当前页码-1)*页大小
        builder.from(0);//第几页
        builder.size(4);//页大小
        builder.sort("age", SortOrder.DESC); //排序查询
        String[] excludes = {"age"};//排除字段
        String[] includes = {};//包含字段
        builder.fetchSource(includes, excludes);//过滤字段查询
        request.source(builder);//可链式调用
        SearchResponse response3 = esClient.search(request, RequestOptions.DEFAULT);
        SearchHits hits3 = response3.getHits();
        System.out.println(hits3.getTotalHits());
        System.out.println(response3.getTook());
        for (SearchHit hit : hits3) {
            System.out.println("分页查询--->" + hit.getSourceAsString());
        }

        //TODO 4。组合查询
        SearchSourceBuilder builder2 = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.matchQuery("age", "50"));//必须年龄为50（must 必须）
        boolQueryBuilder.must(QueryBuilders.matchQuery("sex", "1"));
        boolQueryBuilder.mustNot(QueryBuilders.matchQuery("sex", "0"));//(mustNot 必须不是什么东西)
        boolQueryBuilder.should(QueryBuilders.matchQuery("name", "lisi"));//或者名称=李四(should 或者的意思，不能和must同用)
        builder2.query(boolQueryBuilder);
        request.source(builder2);//可链式调用
        SearchResponse response4 = esClient.search(request, RequestOptions.DEFAULT);
        SearchHits hits4 = response4.getHits();
        System.out.println(hits4.getTotalHits());
        System.out.println(response4.getTook());
        for (SearchHit hit : hits4) {
            System.out.println("组合查询--->" + hit.getSourceAsString());
        }


        //TODO 5。范围查询
        SearchSourceBuilder builder3 = new SearchSourceBuilder();
        RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("age");
       // rangeQuery.gt(20);//大于
        rangeQuery.gte(30);//大于等于30岁（gt 大于 gte 大于等于 lt小于 lte小于等于）
        rangeQuery.lte(50);//小于等于50岁

        builder3.query(rangeQuery);
        request.source(builder3);//可链式调用
        SearchResponse response5 = esClient.search(request, RequestOptions.DEFAULT);
        SearchHits hits5 = response5.getHits();
        System.out.println(hits5.getTotalHits());
        System.out.println(response5.getTook());
        for (SearchHit hit : hits5) {
            System.out.println("范围查询--->" + hit.getSourceAsString());
        }

        //TODO 6。模糊查询
        SearchSourceBuilder builder4 = new SearchSourceBuilder();
       // FuzzyQueryBuilder fuzzyQuery = QueryBuilders.fuzzyQuery("name", "liuqi").fuzziness(Fuzziness.ONE);//查询刘琦， 允许有差别一个字符
        FuzzyQueryBuilder fuzzyQuery2 = QueryBuilders.fuzzyQuery("name", "liuqi").fuzziness(Fuzziness.TWO);//查询刘琦， 允许有差两个字符
        builder4.query(fuzzyQuery2);
        request.source(builder4);//可链式调用
        SearchResponse response6 = esClient.search(request, RequestOptions.DEFAULT);
        SearchHits hits6 = response6.getHits();
        System.out.println(hits6.getTotalHits());
        System.out.println(response6.getTook());
        for (SearchHit hit : hits6) {
            System.out.println("模糊查询--->" + hit.getSourceAsString());
        }

        //TODO 7。高亮查询
        SearchSourceBuilder builder5 = new SearchSourceBuilder();
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", "liuqi");
        builder5.highlighter(new HighlightBuilder().preTags("<font color='red'>").postTags("</font>").field("name"));//查询出来的name属性用font标签框住形成高亮
        builder5.query(termQueryBuilder);
        request.source(builder5);//可链式调用
        SearchResponse response7 = esClient.search(request, RequestOptions.DEFAULT);
        SearchHits hits7= response7.getHits();
        System.out.println(hits7.getTotalHits());
        System.out.println(response7.getTook());
        for (SearchHit hit : hits7) {
            System.out.println("高亮查询--->" + hit.getSourceAsString());
        }

        //TODO 8。聚合查询 min -  max - group
        SearchSourceBuilder builder6 = new SearchSourceBuilder();
        //MaxAggregationBuilder maxAggregationBuilder = AggregationBuilders.max("maxAge").field("age");//获取到最大的年龄
       // MinAggregationBuilder maxAggregationBuilder = AggregationBuilders.min("minAge").field("age");//获取到最小的年龄
        TermsAggregationBuilder termsAggregationBuilder = AggregationBuilders.terms("ageGroup").field("age");//根据age分组
        builder6.aggregation(termsAggregationBuilder);

        request.source(builder6);//可链式调用

        SearchResponse response8 = esClient.search(request, RequestOptions.DEFAULT);
        SearchHits hits8= response8.getHits();
        System.out.println(hits8.getTotalHits());
        System.out.println(response8.getTook());
        for (SearchHit hit : hits8) {
            System.out.println("聚合查询--->" + hit.getSourceAsString());
        }

        //关闭es客户端
        esClient.close();
    }
}
