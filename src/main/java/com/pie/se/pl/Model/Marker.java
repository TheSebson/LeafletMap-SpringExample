package com.pie.se.pl.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="Marker")
public class Marker {

    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(nullable = false, length = 100)
    private Double latitude;
    @Column( nullable = false, length = 100)
    private Double longtitude;



    public Marker(Double latitude, Double longtitude, String name) {
        this.name = name;
        this.latitude = latitude;
        this.longtitude = longtitude;
    }

    public Double getLatitude() {

        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(Double longtitude) {
        this.longtitude = longtitude;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String number) {
        this.name = number;
    }
}
