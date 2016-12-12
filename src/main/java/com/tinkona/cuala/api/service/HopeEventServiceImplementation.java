package com.tinkona.cuala.api.service;


import com.tinkona.cuala.api.dao.contract.HopeEventDao;
import com.tinkona.cuala.api.model.HopeEvent;
import com.tinkona.cuala.api.model.Response;
import com.tinkona.cuala.api.service.contract.EventService;
import com.tinkona.cuala.api.service.contract.HopeEventService;
import org.springframework.stereotype.Service;

/**
 * Created by Nnaji.Arinze on 10/26/2016.
 */
@Service
public class HopeEventServiceImplementation implements HopeEventService {
    private final HopeEventDao hopeEventDao;

    public HopeEventServiceImplementation(HopeEventDao hopeEventDao) {
        this.hopeEventDao = hopeEventDao;
    }

    @Override
    public Response create(HopeEvent hopeEvent) {
        return hopeEventDao.create(hopeEvent);
    }

    @Override
    public Response fetchPaginated(int pageNum, int pageSize) {
       return hopeEventDao.fetchPaginated(pageNum,pageSize);
    }

    @Override
    public Response getEventById(int id) {
        return hopeEventDao.getEventById(id);
    }

    @Override
    public Response update(HopeEvent event) {
        return hopeEventDao.update(event);
    }

    @Override
    public Response delete(int id) {
        return hopeEventDao.delete(id);
    }


    @Override
    public Response getEventsByDate(String date) {
        return hopeEventDao.getEventsByDate(date);

    }

    @Override
    public Response getEventsBetweenDates(String startDate, String endDate) {
        return hopeEventDao.getEventsBetweenDates(startDate,endDate);
    }


}
