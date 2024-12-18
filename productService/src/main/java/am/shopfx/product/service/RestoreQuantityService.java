package am.shopfx.product.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import am.shopfx.core.entity.Product;
import am.shopfx.product.dao.DeductQuantityLogDao;
import am.shopfx.product.dao.ProductDao;
import am.shopfx.product.entity.DeductQuantityLog;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
public class RestoreQuantityService {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private DeductQuantityLogDao deductQuantityLogDao;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * @param orderId
     * @param counterMap {productId : counter}
     * @return
     */
    @Transactional
    public void restoreQuantity(String orderId, Map<String, Integer> counterMap, DeductQuantityLog record) {
        List<Product> products = productDao.findAllById(counterMap.keySet());
        products.forEach(product -> {
            product.setQuantity(product.getQuantity() + counterMap.get(product.getId()));
        });
        productDao.saveAll(products);
        record.setState(0);
        deductQuantityLogDao.saveAndFlush(record);

        log.info("restore product quantity successful, order id={}", orderId);
    }
}
