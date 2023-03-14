package cn.eureka.api.domain.zsxq;

import cn.eureka.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;


import java.io.IOException;

/**
 * @ClassName ZsxqApi
 * @description:
 * @Author: Mo
 * @Date: 2023/03/13 20:06
 * @Version 17.0.1
 **/

public interface IZsxqApi {
    UnAnsweredQuestionsAggregates queryunAnsweredQuestionsAggregates(String groupId, String cookie) throws IOException;

    boolean answer(String groupId, String cookie, String topicId, String text, boolean silenced) throws IOException;



}
