package am.shopfx.order.service;

import am.shopfx.core.entity.Product;
import am.shopfx.core.pojo.ResponseData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

@FeignClient(value = "${sp-product-service.url}")
public interface ProductFeignClient {
    @GetMapping("products/{ids}")
    ResponseData<List<Product>> getByIds(@PathVariable("ids") String ids);

    @PostMapping("quantity/restore")
    ResponseData<Set<String>> restoreProductQuantity(@RequestBody Map<String, Map<String, Integer>> restoreMap);
}
