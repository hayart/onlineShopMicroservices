package am.shopfx.payment.dao;

import am.shopfx.core.entity.PaymentLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentLogDao extends JpaRepository<PaymentLog, String> {
    PaymentLog findByOrderId(String orderId);
}
