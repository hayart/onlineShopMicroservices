package am.shopfx.order.service;

import am.shopfx.core.entity.LocalMessage;
import am.shopfx.core.pojo.OrderMsg;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class SendMsgToMQ {
    private final Gson gson;
    private final LocalMessageService localMessageService;
    private final AsyncRabbitTemplate asyncRabbitTemplate;
    private final OrderService orderService;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public void resendOrderMsg() {
        List<LocalMessage> list = localMessageService.getRetryOrderMsgs();
        String msgJson = "";
        for (LocalMessage m : list) {
            try {
                msgJson = m.getMsgJson();
                OrderMsg msg = gson.fromJson(msgJson, OrderMsg.class);
                orderService.sendOrderMsg(msg, true);
            } catch (Exception e) {
                localMessageService.setRetry(m.getId(), 0);
                log.error("send message failed,OrderMsg id={}", m.getId());
                e.printStackTrace();
            }
        }
    }

}
