package cn.hubin.chatbpt.api.test;

import cn.hubin.chatbot.api.ApiApplication;
import cn.hubin.chatbot.api.domain.ai.IOpenAi;
import cn.hubin.chatbot.api.domain.zsxq.IZsxqApi;
import cn.hubin.chatbot.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;
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

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ApiApplication.class}) //<________改成启动类类名
public class SpringBootRunTest {

    private Logger logger = LoggerFactory.getLogger(SpringBootRunTest.class);
    @Value("${chatbot-api.groupId}")
    private String groupId;
    @Value("${chatbot-api.cookie}")
    private String cookie;

    @Resource
    private IZsxqApi zsxqApi;
    @Resource
    private IOpenAi openAi;

    @Test
    public void test_zsxqApi() throws IOException{
        UnAnsweredQuestionsAggregates unAnsweredQuestionsAggregates = zsxqApi.queryUnAnsweredQuestionsTopicId(groupId, cookie);
        logger.info("测试结果：{}", JSON.toJSONString(unAnsweredQuestionsAggregates));
    }

    @Test
    public void test_open_ai() throws IOException{
        String response = openAi.doChatGpt("请帮我写一个冒泡排序！");
        logger.info("测试结果：{}", response);
    }
}
