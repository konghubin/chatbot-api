package cn.hubin.chatbot.api.domain.zsxq;

import cn.hubin.chatbot.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;
import org.springframework.stereotype.Component;

import java.io.IOException;

public interface IZsxqApi {
    UnAnsweredQuestionsAggregates queryUnAnsweredQuestionsTopicId(String groupId, String cookie) throws IOException;

    boolean answer(String groupId, String cookie, String topicId, String text, boolean silenced) throws IOException;


}
