package cn.hubin.chatbpt.api.test;

import org.apache.http.HttpHost;
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
import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;

public class ApiTest {
    @Test
    public void query_unanswered_questions() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/88885412542282/topics?scope=unanswered_questions&count=20");

        get.addHeader("cookie","zsxq_access_token=3B021965-ED59-CFDF-C537-7D8453AC3C21_1A6AEF198A85DA71; zsxqsessionid=b5d087e97c38a1ca17523408f5494860; abtest_env=product; sajssdk_2015_cross_new_user=1; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22212544844582551%22%2C%22first_id%22%3A%2218a27ecf89b1de-0f00b3cc0705f88-7f5d547e-3686400-18a27ecf89c11f2%22%2C%22props%22%3A%7B%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMThhMjdlY2Y4OWIxZGUtMGYwMGIzY2MwNzA1Zjg4LTdmNWQ1NDdlLTM2ODY0MDAtMThhMjdlY2Y4OWMxMWYyIiwiJGlkZW50aXR5X2xvZ2luX2lkIjoiMjEyNTQ0ODQ0NTgyNTUxIn0%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22212544844582551%22%7D%2C%22%24device_id%22%3A%2218a27ecf89b1de-0f00b3cc0705f88-7f5d547e-3686400-18a27ecf89c11f2%22%7D");
        get.addHeader("Content-Type","application/json;charset=utf8");

        CloseableHttpResponse response = httpClient.execute(get);
        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        }else{
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }

    @Test
    public void answer() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/811848258554552/answer");

        post.addHeader("cookie","zsxq_access_token=3B021965-ED59-CFDF-C537-7D8453AC3C21_1A6AEF198A85DA71; zsxqsessionid=b5d087e97c38a1ca17523408f5494860; abtest_env=product; sajssdk_2015_cross_new_user=1; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22212544844582551%22%2C%22first_id%22%3A%2218a27ecf89b1de-0f00b3cc0705f88-7f5d547e-3686400-18a27ecf89c11f2%22%2C%22props%22%3A%7B%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMThhMjdlY2Y4OWIxZGUtMGYwMGIzY2MwNzA1Zjg4LTdmNWQ1NDdlLTM2ODY0MDAtMThhMjdlY2Y4OWMxMWYyIiwiJGlkZW50aXR5X2xvZ2luX2lkIjoiMjEyNTQ0ODQ0NTgyNTUxIn0%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22212544844582551%22%7D%2C%22%24device_id%22%3A%2218a27ecf89b1de-0f00b3cc0705f88-7f5d547e-3686400-18a27ecf89c11f2%22%7D");
        post.addHeader("Content-Type","application/json;charset=utf8");
        post.addHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36 Edg/116.0.1938.54");

        String paramJson = "{\n" +
                "  \"req_data\": {\n" +
                "    \"text\": \"自己去百度！\\n\",\n" +
                "    \"image_ids\": [],\n" +
                "    \"silenced\": false\n" +
                "  }\n" +
                "}";
        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json","UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);

        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        }else{
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }

    @Test
    public void test_chatGpt() throws IOException{
        // Define proxy settings
        HttpHost proxy = new HttpHost("127.0.0.1", 7890);

        // Create a custom request config with proxy
        RequestConfig requestConfig = RequestConfig.custom()
                .setProxy(proxy)
                .build();
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(requestConfig)
                .build();

        HttpPost post = new HttpPost("https://api.openai.com/v1/chat/completions");
        post.addHeader("Content-Type", "application/json");
        post.addHeader("Authorization", "Bearer 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");


        String paramJson = "{\n" +
                "     \"model\": \"gpt-3.5-turbo\",\n" +
                "     \"messages\": [{\"role\": \"user\", \"content\": \"请用Java帮我写一个冒泡排序!\"}],\n" +
                "     \"temperature\": 0.7\n" +
                "   }";
        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json","UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);

        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        }else{
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }
}
