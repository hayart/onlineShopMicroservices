package am.shopfx.web.controller;

import am.shopfx.core.Cache;
import am.shopfx.core.entity.ApiToken;
import am.shopfx.core.pojo.ResponseData;
import am.shopfx.core.response.ResponseDataResult;
import am.shopfx.web.Constant;
import am.shopfx.web.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@ResponseDataResult
public class LoginController {
    @Autowired
    private Cache cache;
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public Object login(String uname, String pwd) {
        ResponseData<ApiToken> responseData = loginService.login(uname, pwd);
        ApiToken apiToken = responseData.getData();
        if (apiToken == null) {
            return responseData;
        }
        Map result = new HashMap();
        cache.put(Constant.TOKEN_PREFIX + apiToken.getToken(), apiToken.getUid());
        cache.put(Constant.UID_PREFIX + apiToken.getUid(), apiToken.getToken());
        result.put("uid", apiToken.getUid());
        result.put("token", apiToken.getToken());
        return result;
    }
}
