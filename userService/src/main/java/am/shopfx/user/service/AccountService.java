package am.shopfx.user.service;

import am.shopfx.core.entity.Account;

import java.util.List;

public interface AccountService {
    void createAccounts(List<Account> accounts);

    Account verify(String uname, String pwd);

    List<Account> getAll();
}
