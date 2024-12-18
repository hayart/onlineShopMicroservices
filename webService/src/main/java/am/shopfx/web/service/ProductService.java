package am.shopfx.web.service;

import am.shopfx.core.entity.Product;
import am.shopfx.core.pojo.PageQuery;
import am.shopfx.core.pojo.ResponseData;
import am.shopfx.web.api.ProductFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductFeignClient productFeignClient;

    public ResponseData<List<Product>> getProducts(PageQuery pageQuery) {
        return productFeignClient
                .getProducts(pageQuery.getPageNum(), pageQuery.getPageSize());
    }

}
