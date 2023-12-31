//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.driver.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(
        name = "reservation"
)
public class Reservation {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int id;
    private int numberOfHours;
    @ManyToOne
    @JoinColumn
    private User user;
    @ManyToOne
    @JoinColumn
    private Spot spot;
    @OneToOne(
            mappedBy = "reservation",
            cascade = {CascadeType.ALL}
    )
    private Payment payment;

    public Reservation() {
    }

    public Reservation(int id, int numberOfHour, User user, Spot spot, Payment payment) {
        this.id = id;
        this.numberOfHours = numberOfHour;
        this.user = user;
        this.spot = spot;
        this.payment = payment;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberOfHours() {
        return this.numberOfHours;
    }

    public void setNumberOfHours(int numberOfHour) {
        this.numberOfHours = numberOfHour;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Spot getSpot() {
        return this.spot;
    }

    public void setSpot(Spot spot) {
        this.spot = spot;
    }

    public Payment getPayment() {
        return this.payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
