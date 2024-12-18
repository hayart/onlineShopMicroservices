package am.shopfx.web.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import am.shopfx.core.entity.ApiToken;
import am.shopfx.core.pojo.ResponseData;

@FeignClient(name = "authServerClient", url = "${gateway.url}/authServer/")
public interface AuthServerFeignClient {
    @PostMapping("api/token")
    ResponseData<ApiToken> getToken(@RequestParam String uname, @RequestParam String pwd);
}
