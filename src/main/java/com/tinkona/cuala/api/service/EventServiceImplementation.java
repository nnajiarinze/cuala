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


}