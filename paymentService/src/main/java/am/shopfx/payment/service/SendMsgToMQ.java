package am.shopfx.payment.service;

import am.shopfx.core.entity.LocalMessage;
import am.shopfx.core.pojo.OrderMsg;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class SendMsgToMQ {
    private final Gson gson;
    private final LocalMessageService localMessageService;
    private final MqMessageService mqMessageService;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public void resendOrderMsg() {
        List<LocalMessage> list = localMessageService.getRetryOrderMsgs();
        String msgJson = "";
        for (LocalMessage m : list) {
            try {
                msgJson = m.getMsgJson();
                OrderMsg msg = gson.fromJson(msgJson, OrderMsg.class);
                localMessageService.setRetry(m.getId(), 0); //before send()
                mqMessageService.send(m.getId(), msg);
            } catch (Exception e) {
                log.error("send message failed,OrderMsg id={}", m.getId());
                e.printStackTrace();
            }
        }
    }
}
