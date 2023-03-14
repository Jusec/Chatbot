package cn.eureka.api.domain.zsxq.model.req;

/**
 * @ClassName AnswerReq
 * @description:
 * @Author: Mo
 * @Date: 2023/03/13 20:16
 * @Version 17.0.1
 **/

public class AnswerReq {

    private ReqData req_data;

    public AnswerReq(ReqData req_data) {
        this.req_data = req_data;
    }

    public ReqData getReq_data() {
        return req_data;
    }

    public void setReq_data(ReqData req_data) {
        this.req_data = req_data;
    }

}

