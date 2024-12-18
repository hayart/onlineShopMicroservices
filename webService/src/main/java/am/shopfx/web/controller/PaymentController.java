package am.shopfx.web.controller;

import am.shopfx.core.entity.PaymentLog;
import am.shopfx.core.pojo.ResponseData;
import am.shopfx.core.response.ResponseDataResult;
import am.shopfx.web.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseDataResult
@RequestMapping("payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("pay")
    public ResponseData<PaymentLog> pay(String orderId) {
        return paymentService.pay(orderId);
    }
}
