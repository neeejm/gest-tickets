package emsi.ssii.devoir.models;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import emsi.ssii.devoir.Constants;

@Entity
@AttributeOverride(name = "user_id", column = @Column(name = "client_id"))
@PrimaryKeyJoinColumn(name = "client_id", referencedColumnName = "user_id")
public class Client extends User {

    public Client() {
        super();
    }

    public Client(String email, String displayName, String password) {
        super(email, displayName, password);
        this.setRole(Constants.getRole("CLIENT"));
    }
}
