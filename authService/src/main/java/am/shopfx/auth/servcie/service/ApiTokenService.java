package am.shopfx.auth.servcie.service;

import am.shopfx.core.entity.Account;
import am.shopfx.core.entity.ApiToken;
import am.shopfx.core.exception.ExceptionUtil;
import am.shopfx.core.pojo.ResponseData;
import am.shopfx.core.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ApiTokenService {
    @Value("${token.secretKey}")
    private String secretKey;
    @Value("${token.expiredMillis}")
    private long expiredMillis;

    @Autowired
    private UserFeignClient userFeignClient;

    public ApiToken generateToken(String uname, String pwd) {
        ResponseData<Account> responseData = userFeignClient.verify(uname, pwd);
        Account account = responseData.getData();
        ApiToken apiToken = new ApiToken();
        if (account != null) {
            String token = JwtUtil.generateToken(account.getId(), account.getUname(), secretKey, expiredMillis);
            apiToken.setUid(account.getId());
            apiToken.setToken(token);
        } else {
            ExceptionUtil.warn(responseData.info());
        }
        return apiToken;
    }


}
