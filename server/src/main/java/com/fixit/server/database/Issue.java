package com.fixit.server.database;

import javax.persistence.*;

@Entity
public class Issue {

    enum Status {
        NEW, READ, FORWARDER, CANCELED, DONE
    }

    enum PublicService {
        ROAD_SERVICE, CLEANING_SERVICE
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String androidId;

    private String imagePath;

    private String address;

    private long latiude;

    private long longitude;

    private Status status;

    @Column(nullable = true)
    private String description;

    @Column(nullable = true)
    private PublicService publicService;

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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getLatiude() {
        return latiude;
    }

    public void setLatiude(long latiude) {
        this.latiude = latiude;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
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

    public PublicService getPublicService() {
        return publicService;
    }

    public void setPublicService(PublicService publicService) {
        this.publicService = publicService;
    }
}
