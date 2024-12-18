package am.shopfx.payment.service;


import am.shopfx.core.entity.PaymentLog;
import am.shopfx.core.entity.Wallet;

public interface PaymentService {
    Wallet createUserWallet(Wallet wallet);

    PaymentLog checkAndPay(String uid, String orderId);

    PaymentLog pay(String orderId);

    Wallet getWallet(String uid);
}
