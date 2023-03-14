package cn.eureka.api;

import cn.eureka.api.domain.ai.IOpenAIApi;
import cn.eureka.api.domain.zsxq.IZsxqApi;
import cn.eureka.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;
import cn.eureka.api.domain.zsxq.model.vo.Topics;
import cn.eureka.api.domain.zsxq.service.ZsxqApi;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @ClassName texxt2
 * @description:
 * @Author: Mo
 * @Date: 2023/03/13 20:56
 * @Version 17.0.1
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class texxt2 {
    @Value("${chatbot.groupId}")
    private String groupId;
    @Value("${chatbot.cookie}")
    private String cookie;

    @Resource
    private IZsxqApi zsxqApi;

    private Logger logger = LoggerFactory.getLogger(texxt2.class);

    @Test
    public void test() throws IOException {

        UnAnsweredQuestionsAggregates unAnsweredQuestionsAggregates = zsxqApi.queryunAnsweredQuestionsAggregates(groupId, cookie);
        List<Topics> topics = unAnsweredQuestionsAggregates.getResp_data().getTopics();
        for (Topics topic : topics) {
            String topicId = topic.getTopic_id();
            String text = topic.getQuestion().getText();
            logger.info("topicId: {}   text: {}", topicId, text);
        }
    }

    @Resource
    private IOpenAIApi OpenAI;

    @Test
    public void testgbt() throws IOException {
        String resp = OpenAI.doChatGbt("你是谁！");
        logger.info("test result:{}", resp);

    }


}
