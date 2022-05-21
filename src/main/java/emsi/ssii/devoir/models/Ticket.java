package emsi.ssii.devoir.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ticket_id")
    private int id;
    private String description;
    @Enumerated(EnumType.STRING)
    private Urgence ugence;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String os;
    @ManyToOne
    @JoinColumn(name = "software_id")
    private Software software;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Ticket() {
    }

    public Ticket(String description, Urgence ugence, Status status, String os, Software software, Client client) {
        this.description = description;
        this.ugence = ugence;
        this.status = status;
        this.os = os;
        this.software = software;
        this.client = client;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Urgence getUgence() {
        return ugence;
    }

    public void setUgence(Urgence ugence) {
        this.ugence = ugence;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public Software getSoftware() {
        return software;
    }

    public void setSoftware(Software software) {
        this.software = software;
    }

    @Override
    public String toString() {
        return this.description;
    }
}
