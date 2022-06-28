package com.krest.es.service.impl;

import com.alibaba.fastjson.JSON;
import com.krest.Service.Response.R;
import com.krest.es.client.MybatisPlusClient;
import com.krest.es.entity.User;
import com.krest.es.service.EsService;

import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * @author: krest
 * @date: 2021/5/23 17:54
 * @description:
 */
@Service
public class EsServiceImpl implements EsService {

    @Autowired
    @Qualifier("elasticsearchClient")
    private RestHighLevelClient restHighLevelClient;

    @Autowired
    private MybatisPlusClient mybatisPlusClient;

    @Override
    public R getUserById(String id) throws ExecutionException, InterruptedException {
        return  mybatisPlusClient.getUserById(id);
    }

    @Override
    public R savaToEs(User user) throws IOException {
        // BulkRequest 可以用来批量保存数据，并且向Es中发送需要保存的数据
        BulkRequest bulkRequest = new BulkRequest();
        //找到需要的索引库，在es中建立索引和映射关系，需要提前在 Kibana 中进行操作
        IndexRequest indexRequest = new IndexRequest("users");
        //将对象转化为JSON格式
        String jsonString = JSON.toJSONString(user);
        //设置 Id
        indexRequest.id(user.getId());
        //将 Json信息放入请求体重
        indexRequest.source(jsonString, XContentType.JSON);
        //发送请求
        bulkRequest.add(indexRequest);

        BulkResponse response = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);

        //返回信息
        System.out.println("bulkRequest："+bulkRequest.toString());
        System.out.println("上传数量"+response.getItems()+",是否失败:"+response.hasFailures());

        return R.ok();
    }

    @Override
    public R deleteEsUser(String id) throws IOException {

        DeleteRequest deleteRequest = new DeleteRequest("users",id);
        DeleteResponse delete = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
        RestStatus status = delete.status();
        System.out.println(status);
        return R.ok();
    }
}
