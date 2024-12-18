package am.shopfx.payment.service;

import am.shopfx.core.entity.SpOrder;
import am.shopfx.core.exception.ExceptionUtil;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@AllArgsConstructor
@Service
public class OrderService {

    private final OrderFeignClient orderFeignClient;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public SpOrder getOrder(String id) {
        return orderFeignClient.getOrder(id).getData();
    }

    public CompletableFuture<SpOrder> getToPayOrder(String orderId) {
        SpOrder order = getAndCheckOrder(orderId);
        int n = 0;
        while (order == null && n < 10) {
            try {
                Thread.sleep(20);
                log.info("getToPayOrder .... n={}", n);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            order = getAndCheckOrder(orderId);;
            n++;
        }
        return CompletableFuture.completedFuture(order);
    }

    private SpOrder getAndCheckOrder(String orderId) {
        SpOrder order = orderFeignClient.getOrder(orderId).getData();
        if (order == null) {
            ExceptionUtil.warn("The order does not exists,order id = " + orderId);
        } else {
            if (order.isValid() == true && order.getPaymentState() == 1) {
                return order;
            } else if (null != order.getInvalidReason() && !"".equals(order.getInvalidReason())) {
                ExceptionUtil.warn("the order is not able to pay,id="
                        + orderId + "," + order.getInvalidReason());
            }
        }
        return null;
    }
}
