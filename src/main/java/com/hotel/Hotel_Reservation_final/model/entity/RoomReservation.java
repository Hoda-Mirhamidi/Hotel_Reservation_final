package com.hotel.Hotel_Reservation_final.model.entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
public class RoomReservation {
    private int customer_id;
    private String customer_fname;
    private String customer_lname;
    private String start_date;
    private String end_date;
    private int capacity;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(1)
    private int room ;

    @Column (unique = true)
    private String reservation_code;
    @ManyToOne
    @JoinColumn
    private User user;

    public RoomReservation(int customer_id, String customer_fname, String customer_lname, String start_date, String end_date, int capacity, String reservation_code) {
        this.customer_id = customer_id;
        this.customer_fname = customer_fname;
        this.customer_lname = customer_lname;
        this.start_date = start_date;
        this.end_date = end_date;
        this.capacity = capacity;
        this.reservation_code = reservation_code;
    }



    public RoomReservation() {

    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_fname() {
        return customer_fname;
    }

    public void setCustomer_fname(String customer_fname) {
        this.customer_fname = customer_fname;
    }

    public String getCustomer_lname() {
        return customer_lname;
    }

    public void setCustomer_lname(String customer_lname) {
        this.customer_lname = customer_lname;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public String getReservation_code() {
        return reservation_code;
    }

    public void setReservation_code(String reservation_code) {
        this.reservation_code = reservation_code;
    }

    public String toString() {
        return "RoomReservation Information : " +
                "Id = " + customer_id +
                " , First Name='" + customer_fname + '\'' +
                " , Last Name='" + customer_lname + '\'' +
                " , Start Date=" + start_date +
                " , End Date=" + end_date +
                " , Capacity=" + capacity +
                " , Room=" + room +
                " , Reservation Code= "+reservation_code;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
