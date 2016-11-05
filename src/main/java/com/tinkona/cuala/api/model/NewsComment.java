package com.tinkona.cuala.api.model;

/**
 * Created by Nnaji.Arinze on 10/2/2016.
 */
public class NewsComment {
    private int id;
    private int newsId;
    private int userId;
    private String comment;
    private String lastModifiedDate;

    public NewsComment() {
    }

    public NewsComment(int id,int newsId, int userId, String comment, String lastModifiedDate) {
        this.id = id;
        this.newsId=newsId;
        this.userId = userId;
        this.comment = comment;
        this.lastModifiedDate = lastModifiedDate;

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(String lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
