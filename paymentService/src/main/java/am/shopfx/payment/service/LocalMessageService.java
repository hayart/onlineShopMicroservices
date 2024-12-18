package am.shopfx.payment.service;

import am.shopfx.core.entity.LocalMessage;
import am.shopfx.core.pojo.OrderMsg;
import am.shopfx.payment.dao.LocalMessageDao;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class LocalMessageService {
    private Gson gson;
    private LocalMessageDao localMessageDao;
    final Logger log = LoggerFactory.getLogger(this.getClass());

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
        log.debug("save OrderMsg,id={}", message.getId());
    }

    public void setSendState(Integer sendState, String messageId) {
        localMessageDao.setSendState(sendState, messageId);
    }

    public void setRetry(String messageId, Integer times) {
        localMessageDao.setRetry(times, messageId);
    }
}
