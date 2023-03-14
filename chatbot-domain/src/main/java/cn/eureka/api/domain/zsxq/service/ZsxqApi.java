package cn.eureka.api.domain.zsxq.service;

import cn.eureka.api.domain.zsxq.IZsxqApi;
import cn.eureka.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;

import cn.eureka.api.domain.zsxq.model.req.AnswerReq;
import cn.eureka.api.domain.zsxq.model.req.ReqData;
import cn.eureka.api.domain.zsxq.model.res.AnswerRes;
import com.alibaba.fastjson.JSON;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @ClassName ZsxqApi
 * @description:
 * @Author: Mo
 * @Date: 2023/03/13 20:30
 * @Version 17.0.1
 **/

@Service
public class ZsxqApi implements IZsxqApi {

    private Logger logger = LoggerFactory.getLogger(ZsxqApi.class);

//    @Override
//    public UnAnsweredQuestionsTopicID queryUnAnsweredQuestionsTopicID(String groupId, String cookie) throws IOException {
//        CloseableHttpClient build = HttpClientBuilder.create().build();
//
//        HttpGet httpGet = new HttpGet("https://api.zsxq.com/v2/groups/"+ groupId +"/topics?scope=unanswered_questions&count=20");
//
//        httpGet.addHeader("cookie", cookie);
//        httpGet.addHeader("Content-Type", "application/json;charset=utf8");
//
//        CloseableHttpResponse response = build.execute(httpGet);
//
//        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//            String jsonstr = EntityUtils.toString(response.getEntity());
//            logger.info("提问数据： groupId:{} jsonStr:{}", groupId, jsonstr);
//            return JSON.parseObject(jsonstr, UnAnsweredQuestionsTopicID.class);
//        }
//
//        throw new RuntimeException("queryUnAnsweredQuestionsTopicId err code is "+ response.getStatusLine().getStatusCode());
//    }

    @Override
    public UnAnsweredQuestionsAggregates queryunAnsweredQuestionsAggregates(String groupId, String cookie) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/" + groupId + "/topics?scope=unanswered_questions&count=20");

        get.addHeader("cookie", cookie);
        get.addHeader("Content-Type", "application/json;charset=utf8");

        CloseableHttpResponse response = httpClient.execute(get);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String jsonStr = EntityUtils.toString(response.getEntity());
            logger.info("拉取提问数据。groupId：{} jsonStr：{}", groupId, jsonStr);
            return JSON.parseObject(jsonStr, UnAnsweredQuestionsAggregates.class);
        } else {
            throw new RuntimeException("queryUnAnsweredQuestionsTopicId Err Code is " + response.getStatusLine().getStatusCode());
        }
    }


    @Override
    public boolean answer(String groupId, String cookie, String topicId, String text, boolean silenced) throws IOException {
        CloseableHttpClient build = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("https://api.zsxq.com/v2/topics/"+ topicId + "/answer");

        httpPost.addHeader("cookie", cookie);
        httpPost.addHeader("Content-Type", "application/json;charset=utf8");

        httpPost.addHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Safari/537.36");

        AnswerReq answerReq = new AnswerReq(new ReqData(text, silenced));
        String paramJson = JSON.toJSONString(answerReq);
        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));

        httpPost.setEntity(stringEntity);
        CloseableHttpResponse response = build.execute(httpPost);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String jsonstr = EntityUtils.toString(response.getEntity());
            logger.info("回答结果： groupId:{} topicId:{} jsonStr:{}", groupId, topicId, jsonstr);
            AnswerRes answerRes = JSON.parseObject(jsonstr, AnswerRes.class);
            return answerRes.isSucceeded();
        }
        throw new RuntimeException("answer err code is "+ response.getStatusLine().getStatusCode());
    }

}
