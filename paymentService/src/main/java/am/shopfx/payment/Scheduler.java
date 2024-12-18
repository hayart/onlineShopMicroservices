package am.shopfx.payment;

import am.shopfx.payment.service.SendMsgToMQ;
import lombok.AllArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class Scheduler {

    private final SendMsgToMQ sendMsgToMQ;
    public final RedissonClient redisson;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Scheduled(fixedDelay = 15000)
    public void checkAndSendMessage() {
        RLock lock = redisson.getLock("scheduledTask-spPayment-checkAndSendMessage");
        if (!lock.tryLock()) {return;}
        try {
            sendMsgToMQ.resendOrderMsg();
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
