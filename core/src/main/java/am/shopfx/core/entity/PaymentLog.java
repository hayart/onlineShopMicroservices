package am.shopfx.core.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
@Entity
public class PaymentLog implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;

    @Column(unique = true)
    private String orderId;

    @Column(precision = 18, scale = 2)
    private BigDecimal payment = new BigDecimal(0.00);
    private String remark;

    @CreationTimestamp
    private Date createdTime;

}
