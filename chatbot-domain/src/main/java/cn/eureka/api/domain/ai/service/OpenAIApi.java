package cn.eureka.api.domain.ai.service;

import cn.eureka.api.domain.ai.IOpenAIApi;
import cn.eureka.api.domain.ai.service.model.aggreagates.AIAnswer;
import cn.eureka.api.domain.ai.service.model.vo.Choices;
import com.alibaba.fastjson.JSON;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName OpenAIApi
 * @description:
 * @Author: Mo
 * @Date: 2023/03/14 14:22
 * @Version 17.0.1
 **/
@Service
public class OpenAIApi implements IOpenAIApi {
    private Logger logger = LoggerFactory.getLogger(OpenAIApi.class);

    @Value("${openAIKey}")
    private String OpenAIKey;

    @Override
    public String doChatGbt(String question) throws IOException {
        CloseableHttpClient build = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost("https://api.openai-proxy.com/v1/chat/completions");

        post.addHeader("Content-Type", "application/json");
        post.addHeader("Authorization", "Bearer " + OpenAIKey);
        String paramJson = "{\n" +
                "  \"model\": \"gpt-3.5-turbo\",\n" +
                "  \"apiKey\": \"" + OpenAIKey + "\", \n" +
                "  \"sessionId\": \"8d1cb9b0-d535-43a8-b738-4f61b1608579\" , \n" +
                "  \"content\": \"" + question + "\",\n" +
                "  \"temperature\": 0.7\n" +
                "}";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));


//        post.setEntity(stringEntity);
//        CloseableHttpResponse respone = build.execute(post);
//        if (respone.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//            String jsonStr = EntityUtils.toString(respone.getEntity());
//            AIAnswer aiAnswer = JSON.parseObject(jsonStr, AIAnswer.class);
//            StringBuilder answer = new StringBuilder();
//            List<Choices> choices = aiAnswer.getChoices();
//            for (Choices choice : choices) {
//                answer.append(choice.getText());
//            }
//            return answer.toString();
//        }

        post.setEntity(stringEntity);
        CloseableHttpResponse respone = build.execute(post);
        if (respone.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String jsonStr = EntityUtils.toString(respone.getEntity());
            AIAnswer aiAnswer = JSON.parseObject(jsonStr, AIAnswer.class);
            StringBuilder answer = new StringBuilder(aiAnswer.getData());
            return answer.toString();
        }

        throw new RuntimeException("openai err code is " + respone.getStatusLine().getStatusCode());

    }


}
