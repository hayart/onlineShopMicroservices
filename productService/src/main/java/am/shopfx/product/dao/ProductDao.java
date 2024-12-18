package am.shopfx.product.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import am.shopfx.core.entity.Product;

@Repository
public interface ProductDao extends JpaRepository<Product, String> {
}
