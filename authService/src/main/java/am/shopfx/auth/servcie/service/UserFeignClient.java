package am.shopfx.auth.servcie.service;

import am.shopfx.core.entity.Account;
import am.shopfx.core.pojo.ResponseData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "${user-service.url}")
public interface UserFeignClient {
    @PostMapping("account/verify")
    ResponseData<Account> verify(@RequestParam String uname, @RequestParam String pwd);
}
