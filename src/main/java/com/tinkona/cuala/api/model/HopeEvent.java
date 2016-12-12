package com.tinkona.cuala.api.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by Nnaji.Arinze on 10/2/2016.
 */
public class HopeEvent {
    private int id;
    @NotNull(message = "required")
    @Min(value = 1, message = "Service Id cannot be less than 1")
    private int serviceId;
    @NotNull(message = "required")
    private String title;
    @NotNull(message = "required")
    private String location;
    @NotNull(message = "required")
    private String date;

    private String image;
    private String description;
    private String createdDate;
    private Boolean deleted;
    private String lastModifiedDate;
    private String serviceName;

    public HopeEvent() {
    }

    public HopeEvent(int id, int serviceId,String title, String location, String date, String image, String description, String createDate
    , Boolean deleted, String serviceName, String lastModifiedDate) {
        this.id = id;
        this.serviceId = serviceId;
        this.title = title;
        this.location =location;
        this.date = date;
        this.image = image;
        this.description = description;
        this.createdDate = createDate;
        this.deleted = deleted;
        this.serviceName = serviceName;
        this.lastModifiedDate = lastModifiedDate;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(String lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
