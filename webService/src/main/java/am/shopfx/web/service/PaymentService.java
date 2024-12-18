package am.shopfx.web.service;

import am.shopfx.core.entity.PaymentLog;
import am.shopfx.core.pojo.ResponseData;
import am.shopfx.web.api.PaymentServiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Autowired
    private PaymentServiceFeignClient paymentServiceFeignClient;

    public ResponseData<PaymentLog> pay(String orderId) {
        return paymentServiceFeignClient.pay(orderId);
    }

}
