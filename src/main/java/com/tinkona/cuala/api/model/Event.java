package com.tinkona.cuala.api.model;

/**
 * Created by Nnaji.Arinze on 10/2/2016.
 */
public class Event {
    private int id;
    private String title;
    private String location;
    private String date;
    private String image;
    private String description;
    private String createdDate;
    private Boolean deleted;
    private String lastModifiedDate;

    public Event() {
    }

    public Event(int id, String title, String location, String date,String image, String description, String createDate
    , Boolean deleted, String lastModifiedDate) {
        this.id = id;
        this.title = title;
        this.location =location;
        this.date = date;
        this.image = image;
        this.description = description;
        this.createdDate = createDate;
        this.deleted = deleted;
        this.lastModifiedDate = lastModifiedDate;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
