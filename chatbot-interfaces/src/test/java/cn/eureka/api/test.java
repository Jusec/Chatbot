package cn.eureka.api;

import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ExecutorService;

/**
 * @ClassName test
 * @description:
 * @Author: Mo
 * @Date: 2023/03/13 11:14
 * @Version 17.0.1
 **/
@SpringBootTest
public class test {
    @Test
    public void query() throws IOException {
        CloseableHttpClient build = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet("https://api.zsxq.com/v2/groups/28885884548541/topics?scope=unanswered_questions&count=20");

        httpGet.addHeader("cookie", "zsxq_access_token=65FB0C03-C3F1-458D-50DB-6A22441EFEEE_B6C91C2CF2EB0EB8; abtest_env=product; sajssdk_2015_cross_new_user=1; sensorsdata2015jssdkcross={\"distinct_id\":\"585224188512584\",\"first_id\":\"186da3b7318e61-0e54c57ee9508e8-7452547c-1823076-186da3b7319118c\",\"props\":{},\"identities\":\"eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTg2ZGEzYjczMThlNjEtMGU1NGM1N2VlOTUwOGU4LTc0NTI1NDdjLTE4MjMwNzYtMTg2ZGEzYjczMTkxMThjIiwiJGlkZW50aXR5X2xvZ2luX2lkIjoiNTg1MjI0MTg4NTEyNTg0In0=\",\"history_login_id\":{\"name\":\"$identity_login_id\",\"value\":\"585224188512584\"},\"$device_id\":\"186da3b7318e61-0e54c57ee9508e8-7452547c-1823076-186da3b7319118c\"}; zsxqsessionid=8493bb56b92c3c7fbacbb87a3beda6bc");
        httpGet.addHeader("Content-Type", "application/json;charset=utf8");

        CloseableHttpResponse response = build.execute(httpGet);

        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }

    }

    @Test
    public void answer() throws IOException {
        CloseableHttpClient build = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("https://api.zsxq.com/v2/topics/181581452818282/answer");


        httpPost.addHeader("cookie","sensorsdata2015jssdkcross={\"distinct_id\":\"28441188144151\",\"first_id\":\"186d4e4a72afc2-0c023f59b0131a8-26031851-2073600-186d4e4a72ba91\",\"props\":{\"$latest_traffic_source_type\":\"直接流量\",\"$latest_search_keyword\":\"未取到值_直接打开\",\"$latest_referrer\":\"\"},\"identities\":\"eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTg2ZDRlNGE3MmFmYzItMGMwMjNmNTliMDEzMWE4LTI2MDMxODUxLTIwNzM2MDAtMTg2ZDRlNGE3MmJhOTEiLCIkaWRlbnRpdHlfbG9naW5faWQiOiIyODQ0MTE4ODE0NDE1MSJ9\",\"history_login_id\":{\"name\":\"$identity_login_id\",\"value\":\"28441188144151\"},\"$device_id\":\"186d4e4a72afc2-0c023f59b0131a8-26031851-2073600-186d4e4a72ba91\"}; zsxqsessionid=9533aa3939028a0ab2ab392de0be5372; abtest_env=product; __cuid=c8ad0a2e6e0344609cb5b08a0c19d8c6; amp_fef1e8=a0600cbc-0622-42f4-82ef-89a9cce4ab37R...1grd2970p.1grd2970u.1.1.2; zsxq_access_token=FBE877D9-AD93-CF93-3F81-1D7212130B58_8232BFF1EE19CD7F");
        httpPost.addHeader("Content-Type", "application/json;charset=utf8");

        String parm = "{\"req_data\":{\"text\":\"我不会aa！!!\\n\",\"image_ids\":[],\"silenced\":true}}";
        StringEntity stringEntity = new StringEntity(parm, ContentType.create("text/json", "UTF-8"));


        httpPost.setEntity(stringEntity);
        CloseableHttpResponse respone = build.execute(httpPost);
        if (respone.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(respone.getEntity());
            System.out.println(res);
        } else {
            System.out.println(respone.getStatusLine().getStatusCode());
        }

    }

    @Test
    public void testgbt() throws IOException {
        CloseableHttpClient build = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost("https://api.openai-proxy.com/v1/chat/completions");
        RequestConfig build1 = RequestConfig.custom().setConnectTimeout(99990000).setConnectionRequestTimeout(99990000)
                .setSocketTimeout(99990000).build();

        post.setConfig(build1);

        UUID uuid = UUID.randomUUID();
        post.addHeader("Content-Type", "application/json");
        post.addHeader("Authorization", "Bearer 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");
        String paramJson = "{\n" +
                "  \"model\": \"text-davinci-003\",\n" +
                "  \"apiKey\": \"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\", \n"+
                " \"sessionId\": \"8d1cb9b0-d535-43a8-b738-4f61b1608579\" , \n" +
                "  \"content\": \"+who are you?\",\n" +
                "  \"temperature\": 0.7\n" +
                "}";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));


        post.setEntity(stringEntity);
        System.out.println("----------222----------------");
        CloseableHttpResponse respone = build.execute(post);
        System.out.println("----------111----------------");
        if (respone.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(respone.getEntity());
            System.out.println(res);
        } else {
            System.out.println(respone.getStatusLine().getStatusCode());
        }
    }

}
