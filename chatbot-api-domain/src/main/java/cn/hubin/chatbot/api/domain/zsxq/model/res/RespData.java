package cn.hubin.chatbot.api.domain.zsxq.model.res;

import java.util.List;
import cn.hubin.chatbot.api.domain.zsxq.model.vo.Topics;

public class RespData
{
    private List<Topics> topics;

    public void setTopics(List<Topics> topics){
        this.topics = topics;
    }
    public List<Topics> getTopics(){
        return this.topics;
    }
}
