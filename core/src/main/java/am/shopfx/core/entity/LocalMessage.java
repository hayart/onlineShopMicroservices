package am.shopfx.core.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
@Entity
public class LocalMessage {
    @Id
    private String id;

    @Column(columnDefinition="text")
    private String msgJson;
    private String msgType;

    //{0:unsent, 1:sent}
    private Integer sendState = 0;
    private Integer retry = 0;

    @CreationTimestamp
    @Column(nullable = false)
    private Date createdTime;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date updatedTime;

}
