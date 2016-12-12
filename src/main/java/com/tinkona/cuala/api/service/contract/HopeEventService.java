package com.tinkona.cuala.api.service.contract;

import com.tinkona.cuala.api.model.HopeEvent;
import com.tinkona.cuala.api.model.Response;

/**
 * Created by Nnaji.Arinze on 10/2/2016.
 */
public interface HopeEventService {
    public Response create(HopeEvent hopeEvent);
    public Response fetchPaginated(int pageNum, int pageSize);
    public Response getEventById(int id);
    public Response update(HopeEvent hopeEvent);
    public Response delete(int id);
    public Response getEventsByDate(String date);
    public Response<HopeEvent> getEventsBetweenDates(String startDate, String endDate);
}
