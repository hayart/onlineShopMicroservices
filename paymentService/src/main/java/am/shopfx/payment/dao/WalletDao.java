package am.shopfx.payment.dao;

import am.shopfx.core.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletDao extends JpaRepository<Wallet, String> {
    Wallet findByUserId(String uid);
}
