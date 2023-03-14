package cn.eureka.api.domain.ai;

import java.io.IOException;

/**
 * @ClassName IOpenAIApi
 * @description:
 * @Author: Mo
 * @Date: 2023/03/14 14:21
 * @Version 17.0.1
 **/

public interface IOpenAIApi {
    String doChatGbt(String question) throws IOException;
}
