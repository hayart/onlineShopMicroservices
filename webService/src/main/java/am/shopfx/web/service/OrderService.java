package am.shopfx.web.service;

import am.shopfx.core.entity.SpOrder;
import am.shopfx.core.pojo.Cart;
import am.shopfx.core.pojo.ResponseData;
import am.shopfx.web.api.OrderFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class OrderService {
    @Autowired
    private OrderFeignClient orderFeignClient;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public ResponseData<String> getOrderToken() {
        return orderFeignClient.getOrderToken();
    }

    public ResponseData<SpOrder> newOrder(Cart cart) {
        return orderFeignClient.newOrder(cart);
    }

    public ResponseData<SpOrder> getOrder(String orderId) {
        return orderFeignClient.getOrder(orderId);
    }

    public CompletableFuture<ResponseData<SpOrder>> getPaidOrder(String orderId) {
        ResponseData<SpOrder> responseData = orderFeignClient.getPaidOrder(orderId);
        SpOrder order = responseData.getData();
        if (order == null) {
            int n = 0;
            while (order == null && n < 10) {
                try {
                    Thread.sleep(1000);
                    log.debug("getToPaidOrder .... n={}", n);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                responseData = orderFeignClient.getPaidOrder(orderId);
                order = responseData.getData();
                n++;
            }
        }
        return CompletableFuture.completedFuture(responseData);
    }

}
