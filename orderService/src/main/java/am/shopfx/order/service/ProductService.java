package am.shopfx.order.service;

import am.shopfx.core.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductFeignClient productFeignClient;

    public List<Product> getProducts(Set<String> idSet) {
        if (idSet.isEmpty()) {return new ArrayList();}
        String ids = String.join(",", idSet);
        List<Product> list = productFeignClient.getByIds(ids).getData();
        return list;
    }

    Set<String> reStoreQuantity(Map<String, Map<String, Integer>> restoreMap) {
        return productFeignClient.restoreProductQuantity(restoreMap).getData();
    }
}
