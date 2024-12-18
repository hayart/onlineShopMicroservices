package am.shopfx.web.service;

import am.shopfx.core.entity.ApiToken;
import am.shopfx.core.pojo.ResponseData;
import am.shopfx.web.api.AuthServerFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private AuthServerFeignClient authServerFeignClient;

    public ResponseData<ApiToken> login(String uname, String pwd) {
        return authServerFeignClient.getToken(uname, pwd);
    }
}
