package com.tinkona.cuala.api.service;

import com.tinkona.cuala.api.dao.contract.EventDao;
import com.tinkona.cuala.api.model.Event;
import com.tinkona.cuala.api.model.News;
import com.tinkona.cuala.api.model.Response;
import com.tinkona.cuala.api.service.contract.EventService;
import org.springframework.stereotype.Service;

/**
 * Created by Nnaji.Arinze on 10/26/2016.
 */
@Service
public class EventServiceImplementation implements EventService {
    private final EventDao eventDao;

    public EventServiceImplementation(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    @Override
    public Response create(Event event) {
        return eventDao.create(event);
    }

    @Override
    public Response fetchPaginated(int pageNum, int pageSize) {
       return eventDao.fetchPaginated(pageNum,pageSize);
    }

    @Override
    public Response getEventById(int id) {
        return eventDao.getEventById(id);
    }

    @Override
    public Response update(Event event) {
        return eventDao.update(event);
    }

    @Override
    public Response delete(int id) {
        return eventDao.delete(id);
    }

    @Override
    public Response createInvitationResponse(Integer eventId, Integer userId, Boolean response) {
        return eventDao.createInvitationResponse(eventId,userId,response);
    }

    @Override
    public Response getInvitationResponse(Integer eventId, Integer userId) {
        return eventDao.getInvitationResponse(eventId,userId);
    }

    @Override
    public Response getEventsByDate(String date) {
        return eventDao.getEventsByDate(date);

    }

    @Override
    public Response<Event> getEventsBetweenDates(String startDate, String endDate) {
        return eventDao.getEventsBetweenDates(startDate,endDate);
    }


}
