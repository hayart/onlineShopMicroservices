package am.shopfx.user.controller;

import am.shopfx.core.entity.Account;
import am.shopfx.core.pojo.ResponseData;
import am.shopfx.core.response.ResponseDataResult;
import am.shopfx.user.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@ResponseDataResult
public class AccountController {

    private final AccountService accountService;

    @PostMapping("accounts")
    public ResponseData createAccounts(@RequestBody List<Account> accounts) {
        accountService.createAccounts(accounts);
        return new ResponseData().setMessage("created accounts successful");
    }

    @PostMapping("account/verify")
    public Account verify(String uname, String pwd){
        return accountService.verify(uname, pwd);
    }

    @GetMapping("accounts")
    public List<Account> getAll() {return accountService.getAll();}
}
