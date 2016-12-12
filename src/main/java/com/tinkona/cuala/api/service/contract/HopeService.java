package com.tinkona.cuala.api.service.contract;

import com.tinkona.cuala.api.model.HopeServiceModel;
import com.tinkona.cuala.api.model.News;
import com.tinkona.cuala.api.model.NewsComment;
import com.tinkona.cuala.api.model.Response;

/**
 * Created by Nnaji.Arinze on 10/2/2016.
 */
public interface HopeService {
    public Response createService(HopeServiceModel hopeServiceModel);
    public Response getAllServices();
}
