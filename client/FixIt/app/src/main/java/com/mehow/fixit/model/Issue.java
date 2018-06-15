package com.mehow.fixit.model;

import java.io.Serializable;

public class Issue implements Serializable{

    @Override
    public String toString() {
        return "Issue{" +
                "id=" + id +
                ", androidId='" + androidId + '\'' +
                ", address='" + address + '\'' +
                ", latiude=" + latiude +
                ", longitude=" + longitude +
                ", status=" + status +
                ", description='" + description + '\'' +
                '}';
    }

    public enum Status {
        NEW, READ, FORWARDER, CANCELED, DONE
    }

    private Integer id;

    private String androidId;

    private String address;

    private double latiude;

    private double longitude;

    private Status status;

    private String description;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAndroidId() {
        return androidId;
    }

    public void setAndroidId(String androidId) {
        this.androidId = androidId;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatiude() {
        return latiude;
    }

    public void setLatiude(double latiude) {
        this.latiude = latiude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
