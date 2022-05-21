package emsi.ssii.devoir.models;

import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import emsi.ssii.devoir.Constants;

@Entity
@AttributeOverride(name = "user_id", column = @Column(name = "dev_id"))
@PrimaryKeyJoinColumn(name = "dev_id", referencedColumnName = "user_id")
public class Dev extends User {

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "dev_id")
    private Set<Ticket> tickets;

    public Dev() {
        super();
    }

    public Dev(String email, String displayName, String password) {
        super(email, displayName, password);
        this.setRole(Constants.getRole("DEV"));
    }
}