package cn.eureka.api.domain.ai.service.model.vo;

/**
 * @ClassName Choices
 * @description:
 * @Author: Mo
 * @Date: 2023/03/14 14:24
 * @Version 17.0.1
 **/

public class Choices {
    private String text;

    private int index;

    private String logprobs;

    private String finish_reason;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getLogprobs() {
        return logprobs;
    }

    public void setLogprobs(String logprobs) {
        this.logprobs = logprobs;
    }

    public String getFinish_reason() {
        return finish_reason;
    }

    public void setFinish_reason(String finish_reason) {
        this.finish_reason = finish_reason;
    }
}
