package am.shopfx.core.pojo;

import am.shopfx.core.entity.Product;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
public class Cart implements Serializable {
    private String uid;
    private transient String orderToken;

    //{productId : count}
    private Map<String, Integer> counterMap = new HashMap<>();
    private Set<Product> productSet = new HashSet<>();

}
