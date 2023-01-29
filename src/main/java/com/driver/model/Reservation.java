package com.driver.model;


import javax.persistence.*;
import java.util.List;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    private int numberOfHours;


    @ManyToOne
    @JoinColumn
    private User User;


    @ManyToOne
    @JoinColumn
    private Spot Spot;


    @OneToOne(mappedBy = "reservation",cascade = CascadeType.ALL)
    private Payment payment;

    public Reservation(int numberOfHours){

        this.numberOfHours = numberOfHours;
    }

    public Reservation(){
    }

    public int getId(){

        return Id;
    }

    public void setId(int id){

        this.Id = id;
    }

    public int getNumberOfHours(){

        return numberOfHours;
    }

    public void setNumberOfHours(int numberOfHours){

        this.numberOfHours = numberOfHours;
    }

    public User getUser(){

        return User;
    }

    public void setUser(User user){

        this.User = user;
    }

    public Spot getSpot(){
        return Spot;
    }

    public void setSpot(Spot spot){

        this.Spot = spot;
    }

    public Payment getPayment(){

        return payment;
    }

    public void setPayment(Payment payment){

        this.payment = payment;
    }
}