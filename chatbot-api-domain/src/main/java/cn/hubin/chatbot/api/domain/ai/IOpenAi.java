package cn.hubin.chatbot.api.domain.ai;

import java.io.IOException;

public interface IOpenAi {
    String doChatGpt(String questions) throws IOException;
}
