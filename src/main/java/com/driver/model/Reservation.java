package com.driver.model;

import javax.persistence.*;

@Entity
@Table(name ="reservation" )
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    private int numberOfHour;
    @ManyToOne
    @JoinColumn
    private User user;
    @ManyToOne
    @JoinColumn
    private Spot spot;
  @OneToOne(mappedBy = "reservation",cascade = CascadeType.ALL)
  private Payment payment;

    public Reservation() {
    }

    public Reservation(int id, int numberOfHour, User user, Spot spot, Payment payment) {
        this.id = id;
        this.numberOfHour = numberOfHour;
        this.user = user;
        this.spot = spot;
        this.payment = payment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberOfHour() {
        return numberOfHour;
    }

    public void setNumberOfHour(int numberOfHour) {
        this.numberOfHour = numberOfHour;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Spot getSpot() {
        return spot;
    }

    public void setSpot(Spot spot) {
        this.spot = spot;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}