package am.shopfx.order.service;

import am.shopfx.core.entity.LocalMessage;
import am.shopfx.core.pojo.OrderMsg;
import am.shopfx.order.dao.LocalMessageDao;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LocalMessageService {

    private final Gson gson;

    private final LocalMessageDao localMessageDao;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public List<LocalMessage> getRetryOrderMsgs() {
        return localMessageDao.getRetryOrderMsgs();
    }

    public void saveOrderMessage(OrderMsg message, int sendState) {
        LocalMessage localMessage = new LocalMessage();
        localMessage.setId(message.getId());
        localMessage.setMsgType("OrderMsg");
        localMessage.setMsgJson(gson.toJson(message));
        localMessage.setSendState(sendState);
        localMessageDao.save(localMessage);
    }

    public void saveOrderMessage(OrderMsg message, int sendState, int retry) {
        LocalMessage localMessage = new LocalMessage();
        localMessage.setId(message.getId());
        localMessage.setMsgType("OrderMsg");
        localMessage.setMsgJson(gson.toJson(message));
        localMessage.setSendState(sendState);
        localMessage.setRetry(retry);
        localMessageDao.save(localMessage);
    }

    public void setSendState(Integer sendState, String messageId) {
        localMessageDao.setSendState(sendState, messageId);
    }

    public void setRetry(String messageId, Integer retry) {
        localMessageDao.setRetry(retry, messageId);
    }
}
