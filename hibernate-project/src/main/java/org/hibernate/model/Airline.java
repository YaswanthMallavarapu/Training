package org.hibernate.model;

import jakarta.persistence.*;

@Entity
public class Airline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;
    String country;

    public Airline() {
    }

    public Airline(String country, int id, String name) {
        this.country = country;
        this.id = id;
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Airline{" +
                "country='" + country + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
