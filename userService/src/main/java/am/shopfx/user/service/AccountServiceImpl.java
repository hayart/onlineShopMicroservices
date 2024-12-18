package am.shopfx.user.service;

import am.shopfx.core.entity.Account;
import am.shopfx.core.exception.ExceptionUtil;
import am.shopfx.user.dao.AccountDao;
import am.shopfx.user.service.AccountService;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao accountDao;

    @Override
    public void createAccounts(List<Account> accounts) {
        accountDao.saveAll(accounts);
    }

    @Override
    public Account verify(String uname, String pwd) {
        if (Strings.isNullOrEmpty(uname) || Strings.isNullOrEmpty(pwd)) {
            ExceptionUtil.warn("uname or pwd is empty");
        }
        return accountDao.findByUnameAndPwd(uname, pwd);
    }

    @Override
    public List<Account> getAll() {
        return  accountDao.findAll();
    }
}
