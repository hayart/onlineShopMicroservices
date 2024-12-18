package am.shopfx.core.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeductionResult implements Serializable {
    private String orderId;
    private boolean successful = false;
    private String message;


}
