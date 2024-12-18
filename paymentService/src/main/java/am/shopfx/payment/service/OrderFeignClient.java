package am.shopfx.payment.service;

import am.shopfx.core.entity.SpOrder;
import am.shopfx.core.pojo.ResponseData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "${sp-order-service.url}")
public interface OrderFeignClient {
    @GetMapping("order/{id}")
    ResponseData<SpOrder> getOrder(@PathVariable String id);

    @GetMapping("order/toPay/{id}")
    ResponseData<SpOrder> getToPayOrder(@PathVariable String id);
}
