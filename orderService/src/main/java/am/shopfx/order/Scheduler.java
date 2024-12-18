package am.shopfx.order;

import am.shopfx.order.service.OrderService;
import am.shopfx.order.service.SendMsgToMQ;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class Scheduler {
    private final SendMsgToMQ sendMsgToMQ;
    private final OrderService orderService;
    @Autowired
    public RedissonClient redisson;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Scheduled(fixedDelay = 15000)
    public void checkAndSendMessage() {
        //make sure to run only once when running multiple service instances
        RLock lock = redisson.getLock("scheduledTask-spOrder-checkAndSendMessage");
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

    @Scheduled(fixedDelay = 1800000)
    public void checkExpiredOrders() {
        RLock lock = redisson.getLock("scheduledTask-spOrder-checkExpiredOrders");
        if (!lock.tryLock()) {return;}
        try {
            orderService.processExpiredOrders();
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
