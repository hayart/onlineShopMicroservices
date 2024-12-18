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
public class Product implements Serializable {
    @Id
    private String id;
    private String name;

    @Column(precision = 18, scale = 2)
    private BigDecimal price;
    private Integer quantity;

    @CreationTimestamp
    private Date createdTime;

    @UpdateTimestamp
    private Date updatedTime;

    public Product() {
        this.id = UUID.randomUUID().toString();
    }

    public Product(String name, BigDecimal price, Integer quantity, Date createdTime) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.createdTime = createdTime;
    }

}
