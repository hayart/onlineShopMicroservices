package am.shopfx.core.pojo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Map;

@Getter
@Setter
public class OrderMsg extends Msg implements Serializable {
    private String orderId;
    private Map<String, Integer> counterMap;

}
