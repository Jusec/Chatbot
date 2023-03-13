package cn.eureka.api;

import org.apache.http.HttpStatus;
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

        httpGet.addHeader("cookie", "zsxq_access_token=4772E0ED-8E9B-4F76-A8C5-15978E30C0F2_8232BFF1EE19CD7F; sensorsdata2015jssdkcross={\"distinct_id\":\"28441188144151\",\"first_id\":\"186d4e4a72afc2-0c023f59b0131a8-26031851-2073600-186d4e4a72ba91\",\"props\":{\"$latest_traffic_source_type\":\"直接流量\",\"$latest_search_keyword\":\"未取到值_直接打开\",\"$latest_referrer\":\"\"},\"identities\":\"eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTg2ZDRlNGE3MmFmYzItMGMwMjNmNTliMDEzMWE4LTI2MDMxODUxLTIwNzM2MDAtMTg2ZDRlNGE3MmJhOTEiLCIkaWRlbnRpdHlfbG9naW5faWQiOiIyODQ0MTE4ODE0NDE1MSJ9\",\"history_login_id\":{\"name\":\"$identity_login_id\",\"value\":\"28441188144151\"},\"$device_id\":\"186d4e4a72afc2-0c023f59b0131a8-26031851-2073600-186d4e4a72ba91\"}; zsxqsessionid=9533aa3939028a0ab2ab392de0be5372; abtest_env=product; __cuid=c8ad0a2e6e0344609cb5b08a0c19d8c6; amp_fef1e8=a0600cbc-0622-42f4-82ef-89a9cce4ab37R...1grd2970p.1grd2970u.1.1.2");
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
        HttpPost httpPost = new HttpPost("https://api.zsxq.com/v2/topics/412812584121858/answer");


        httpPost.addHeader("cookie","sensorsdata2015jssdkcross={\"distinct_id\":\"28441188144151\",\"first_id\":\"186d4e4a72afc2-0c023f59b0131a8-26031851-2073600-186d4e4a72ba91\",\"props\":{\"$latest_traffic_source_type\":\"直接流量\",\"$latest_search_keyword\":\"未取到值_直接打开\",\"$latest_referrer\":\"\"},\"identities\":\"eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTg2ZDRlNGE3MmFmYzItMGMwMjNmNTliMDEzMWE4LTI2MDMxODUxLTIwNzM2MDAtMTg2ZDRlNGE3MmJhOTEiLCIkaWRlbnRpdHlfbG9naW5faWQiOiIyODQ0MTE4ODE0NDE1MSJ9\",\"history_login_id\":{\"name\":\"$identity_login_id\",\"value\":\"28441188144151\"},\"$device_id\":\"186d4e4a72afc2-0c023f59b0131a8-26031851-2073600-186d4e4a72ba91\"}; zsxqsessionid=9533aa3939028a0ab2ab392de0be5372; abtest_env=product; __cuid=c8ad0a2e6e0344609cb5b08a0c19d8c6; amp_fef1e8=a0600cbc-0622-42f4-82ef-89a9cce4ab37R...1grd2970p.1grd2970u.1.1.2; zsxq_access_token=FBE877D9-AD93-CF93-3F81-1D7212130B58_8232BFF1EE19CD7F");
        httpPost.addHeader("Content-Type", "application/json;charset=utf8");

        String parm = "{\"req_data\":{\"text\":\"我不会！!!\\n\",\"image_ids\":[],\"silenced\":true}}";
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

}
