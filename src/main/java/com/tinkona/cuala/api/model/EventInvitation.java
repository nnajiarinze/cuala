package com.tinkona.cuala.api.model;

/**
 * Created by Nnaji.Arinze on 10/2/2016.
 */
public class EventInvitation {
    private int id;
    private int eventId;
    private int userId;
    private Boolean attending;

    public EventInvitation() {
    }

    public EventInvitation(int id, int eventId, int userId, Boolean attending) {
        this.id = id;
        this.eventId = eventId;
        this.userId = userId;
        this.attending = attending;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Boolean getAttending() {
        return attending;
    }

    public void setAttending(Boolean attending) {
        this.attending = attending;
    }
}
