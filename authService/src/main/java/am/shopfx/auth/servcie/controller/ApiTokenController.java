package am.shopfx.auth.servcie.controller;

import am.shopfx.auth.servcie.service.ApiTokenService;
import am.shopfx.core.entity.ApiToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import am.shopfx.core.response.ResponseDataResult;



@RestController
@ResponseDataResult
@RequestMapping("api/token")
public class ApiTokenController {
    @Autowired
    private ApiTokenService apiTokenService;

    @PostMapping
    public ApiToken getToken(String uname, String pwd) {
        ApiToken apiToken = apiTokenService.generateToken(uname, pwd);
        return apiToken;
    }

}
