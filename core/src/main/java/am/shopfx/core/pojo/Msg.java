package am.shopfx.core.pojo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public class Msg implements Serializable {
    private String id = UUID.randomUUID().toString();
    private String from;
    private String to;
    private String remark;

}
