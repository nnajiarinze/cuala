package com.tinkona.cuala.api.model;

/**
 * Created by Nnaji.Arinze on 10/2/2016.
 */
public class News {
    private int id;
    private String headline;
    private String brief;
    private String description;
    private String publishDate;
    private String author;
    private String tweetText;
    private String createdDate;
    private String tags;
    private String image;
    private Boolean deleted;
    private String lastModifiedDate;
    private int comments;

    public News() {
    }

    public News(int id, String headline, String brief, String description, String publishDate, String author, String tweetText, String createDate, String tags, String image
            , Boolean deleted, String lastModifiedDate, int comments) {
        this.id = id;
        this.headline = headline;
        this.brief =brief;
        this.description = description;
        this.publishDate = publishDate;
        this.author=  author;
        this.tweetText = tweetText;
        this.createdDate = createDate;
        this.tags=tags;
        this.image=image;
        this.deleted = deleted;
        this.lastModifiedDate = lastModifiedDate;
        this.comments = comments;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTweetText() {
        return tweetText;
    }

    public void setTweetText(String tweetText) {
        this.tweetText = tweetText;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }
}
