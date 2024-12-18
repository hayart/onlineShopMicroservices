package am.shopfx.payment.service;

import am.shopfx.core.pojo.OrderMsg;
import am.shopfx.payment.config.AmqpConfig;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@AllArgsConstructor
@Service
public class MqMessageService {

    private final RabbitTemplate rabbitTemplate;
    final Logger log = LoggerFactory.getLogger(this.getClass());

    public void send(String localMessageId, OrderMsg message) {
        CompletableFuture.runAsync(() -> {
            rabbitTemplate.convertAndSend(AmqpConfig.EXCHANGE_NAME,
                    AmqpConfig.ROUTING_KEY_SET_ORDER_PAID, message,
                    new CorrelationData(localMessageId));
            log.info("send message to ORDER SERVICE - to set order paid");
        });
    }

}
