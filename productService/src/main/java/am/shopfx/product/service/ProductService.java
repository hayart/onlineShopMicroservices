package am.shopfx.product.service;

import am.shopfx.core.entity.Product;
import am.shopfx.core.pojo.PageQuery;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ProductService {
    void addProducts(List<Product> products);

    List<Product> getProducts(PageQuery pageQuery);

    Product getById(String id);

    List<Product> getByIds(String ids);

    /**
     *
     * @param ids
     * @return {productId : quantity}
     */
    Map<String, Integer> getQuantity(String ids);

    Set<String> restoreProductQuantity(Map<String, Map<String, Integer>> restoreMap);
}
