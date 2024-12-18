package am.shopfx.web.controller;

import am.shopfx.core.entity.Product;
import am.shopfx.core.pojo.PageQuery;
import am.shopfx.core.pojo.ResponseData;
import am.shopfx.core.response.ResponseDataResult;
import am.shopfx.web.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@ResponseDataResult
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("products")
    public ResponseData<List<Product>> getProducts(PageQuery pageQuery){
        return productService.getProducts(pageQuery);
    }
}
