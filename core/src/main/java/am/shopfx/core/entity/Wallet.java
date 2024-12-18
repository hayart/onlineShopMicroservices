package am.shopfx.core.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@Entity
public class Wallet implements Serializable {
    @Id
    private String id;

    @Column(unique = true)
    private String userId;

    @Column(precision = 18, scale = 2)
    private BigDecimal balance = new BigDecimal(0.00);

    @CreationTimestamp
    private Date createdTime;

    @UpdateTimestamp
    private Date updatedTime;

    public Wallet() {
        this.id = UUID.randomUUID().toString();
    }
}
