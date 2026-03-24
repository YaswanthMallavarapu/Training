package org.hibernate.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="passenger_name")
    private String passengerName;
    @Column(name="seat_number")
    private String seatNumber;
    @Column(name="date_of_booking")
    @CreationTimestamp
    private Instant dateOfBooking;
    private BigDecimal price;

    public Booking(int age, Instant dateOfBooking, Flight flight, int id, String passengerName, BigDecimal price, String seatNumber) {
        this.age = age;
        this.dateOfBooking = dateOfBooking;
        this.flight = flight;
        this.id = id;
        this.passengerName = passengerName;
        this.price = price;
        this.seatNumber = seatNumber;
    }

    public Booking() {
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    private int age;
    @ManyToOne
    @JoinColumn(name="flight_id")
    private Flight flight;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Instant getDateOfBooking() {
        return dateOfBooking;
    }

    public void setDateOfBooking(Instant dateOfBooking) {
        this.dateOfBooking = dateOfBooking;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "age=" + age +
                ", id=" + id +
                ", passengerName='" + passengerName + '\'' +
                ", seatNumber='" + seatNumber + '\'' +
                ", dateOfBooking=" + dateOfBooking +
                ", price=" + price +
                ", flight=" + flight +
                '}';
    }
}
