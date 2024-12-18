package am.shopfx.core.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
@Entity
public class ErrorLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String operation;

    @Column(columnDefinition = "TEXT")
    private String error;

    @Column(columnDefinition = "TEXT")
    private String dataJson;
    private Boolean fixed = false;

    @CreationTimestamp
    private Date createdTime;

    @UpdateTimestamp
    private Date updatedTime;



    public void setOperation(String operation) {
        this.operation = operation;
    }



    public void setError(String error) {
        this.error = error;
    }


    public void setDataJson(String dataJson) {
        this.dataJson = dataJson;
    }


}
