package am.shopfx.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@Entity
public class Account implements Serializable {
    @Id
    private String id;
    private String name;
    private String sex;
    private Integer age;

    @Column(unique = true)
    private String uname;
    private String pwd;

    public Account() {
        id = UUID.randomUUID().toString();
    }

}
