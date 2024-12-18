package am.shopfx.user.dao;

import am.shopfx.core.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDao extends JpaRepository<Account, String> {
    Account findByUnameAndPwd(String uname, String pwd);
}
