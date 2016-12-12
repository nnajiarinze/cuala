package com.tinkona.cuala.api.dao.contract;

import com.tinkona.cuala.api.model.Event;
import com.tinkona.cuala.api.model.HopeEvent;
import com.tinkona.cuala.api.model.Response;

/**
 * Created by Nnaji.Arinze on 10/26/2016.
 */
public interface HopeEventDao {
    public Response create(HopeEvent hopeEvent);
    public Response fetchAllEvents();
    public Response fetchPaginated(int pageNum, int pageSize);
    public Response getEventById(int id);
    public Response update(HopeEvent hopeEvent);
    public Response delete(int id);
    public Response getEventsByDate(String date);
    public Response<Event> getEventsBetweenDates(String startDate, String endDate);
}
