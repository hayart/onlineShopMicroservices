package am.shopfx.web.api;

import am.shopfx.core.entity.PaymentLog;
import am.shopfx.core.pojo.ResponseData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "paymentServiceClient", url = "${gateway.url}/paymentService/")
public interface PaymentServiceFeignClient {
    @PostMapping("pay")
    ResponseData<PaymentLog> pay(@RequestParam String orderId);
}
