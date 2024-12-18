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
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@Entity
public class SpOrder implements Serializable {
    @Id
    private String id;
    private String userId;

    @Column(columnDefinition = "TEXT")
    private String cartJson;
    private boolean valid = true;
    private String invalidReason;

    //{0:init state, 1:to pay, 2:paid}
    private Integer paymentState = 0;

    @CreationTimestamp
    private Date createdTime;

    @UpdateTimestamp
    private Date updatedTime;

    public SpOrder() {
    }

    public SpOrder(String uid, String cartJson) {
        this.id = UUID.randomUUID().toString();
        userId = uid;
        this.cartJson = cartJson;
    }

}
