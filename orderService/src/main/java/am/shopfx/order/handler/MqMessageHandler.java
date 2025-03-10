package am.shopfx.order.handler;

import am.shopfx.core.Const;
import am.shopfx.core.pojo.OrderMsg;
import am.shopfx.core.service.ErrorLogService;
import am.shopfx.order.service.OrderService;
import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class MqMessageHandler {

    private final OrderService orderService;

    private final ErrorLogService errorLogService;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final Map<String, Integer> counterMap = new HashMap<>();
    private final int retryTimes = 3;

    //listen messages from PAYMENT SERVICE
    @RabbitListener(queuesToDeclare = @Queue(Const.QUEUE_SET_ORDER_PAID), concurrency = "5-10")
    public void setOrderPaidHandle(OrderMsg message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        log.debug("got message(set order paid) from PAYMENT SERVICE,OrderMsg id={}", message.getId());
        try {
            //set order paid
            orderService.setOrderPaymentState(message.getOrderId(), 2);
            log.info("set order paid,order id={}", message.getOrderId());
            channel.basicAck(tag, false);
        } catch (Exception e) {
            String error = "";
            String key = message.getId();
            Integer count = counterMap.get(key);
            if (count == null || count < retryTimes) {
                channel.basicNack(tag, false, true);
                count = count == null ? 0 : ++count;
                counterMap.put(key, count);
                error = "consume message failed,requeue message,OrderMsg id={},{}";
            } else {
                channel.basicAck(tag, false);
                Map data = new HashMap();
                data.put("OrderMsg", message);
                errorLogService.log(Const.OPERATION_SET_ORDER_PAID, e.getMessage(), data);
                counterMap.remove(key);
                error = "consume message failed,stop retry,OrderMsg id={},{}";
            }
            log.error(error, key, e.getMessage());
            e.printStackTrace();
        }
    }

}
