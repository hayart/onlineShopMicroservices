package am.shopfx.payment.Controller;

import am.shopfx.core.entity.PaymentLog;
import am.shopfx.core.entity.Wallet;
import am.shopfx.core.response.ResponseDataResult;
import am.shopfx.payment.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@ResponseDataResult
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("wallet")
    public Wallet createUserWallet(@RequestBody Wallet wallet) {
        return paymentService.createUserWallet(wallet);
    }

    @PostMapping("pay")
    public PaymentLog pay(@RequestHeader("uid") String uid, String orderId) {
        return paymentService.checkAndPay(uid, orderId);
    }

    @GetMapping("wallet")
    public Wallet getWallet(String uid) {
        return paymentService.getWallet(uid);
    }

}
