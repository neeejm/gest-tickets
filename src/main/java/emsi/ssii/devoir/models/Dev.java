package emsi.ssii.devoir.models;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import emsi.ssii.devoir.utils.Constants;

@Entity
@AttributeOverride(name = "user_id", column = @Column(name = "dev_id"))
@PrimaryKeyJoinColumn(name = "dev_id", referencedColumnName = "user_id")
public class Dev extends User {

    public Dev() {
        super();
        this.setRole(Constants.getRole("dev"));
    }

    public Dev(String email, String displayName, String password) {
        super(email, displayName, password);
        this.setRole(Constants.getRole("dev"));
    }
}