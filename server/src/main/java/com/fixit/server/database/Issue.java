package com.fixit.server.database;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.*;
import java.io.File;
import java.util.Date;

@Entity
public class Issue {

    @JsonFormat(shape=JsonFormat.Shape.OBJECT)
    public enum Status {
        NEW, READ, FORWARDED, CANCELED, DONE;

        static {
            NEW.polishName = "nowy";
        }

        private String polishName;

        @JsonProperty("polishName")
        public String getPolishName() {
            return polishName;
        }

        @JsonProperty("value")
        public String getValue() {
            return this.toString();
        }
    }

    public enum PublicService {
        ROAD_SERVICE, CLEANING_SERVICE, CITY_GUARDS
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String androidId;

    private String imageFileName;

    private String address;

    private double latiude;

    private double longitude;

    private Status status;

    @Column(nullable = true)
    private String description;

    @Column(nullable = true)
    private PublicService publicService;

    private Date created;

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

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
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

    public PublicService getPublicService() {
        return publicService;
    }

    public void setPublicService(PublicService publicService) {
        this.publicService = publicService;
    }

    public void setImage(File image) {
        this.imageFileName = image.getName();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
