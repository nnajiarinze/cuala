package com.tinkona.cuala.api.service.contract;

import com.tinkona.cuala.api.model.Event;
import com.tinkona.cuala.api.model.Response;

/**
 * Created by Nnaji.Arinze on 10/2/2016.
 */
public interface EventService {
    public Response create(Event event);
    public Response fetchPaginated(int pageNum, int pageSize);
    public Response getEventById(int id);
    public Response update(Event event);
    public Response delete(int id);
}
