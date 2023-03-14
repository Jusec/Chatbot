package cn.eureka.api.domain.ai.service.model.aggreagates;

import cn.eureka.api.domain.ai.service.model.vo.Choices;

import java.util.List;

/**
 * @ClassName AIAnswe
 * @description:
 * @Author: Mo
 * @Date: 2023/03/14 14:25
 * @Version 17.0.1
 **/

public class AIAnswer {

    private String code;

    private Long timestamp;

    private String message;

    private String  data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}

