package com.tinkona.cuala.api.dao.contract;

import com.tinkona.cuala.api.model.HopeServiceModel;
import com.tinkona.cuala.api.model.Response;

/**
 * Created by Nnaji.Arinze on 10/26/2016.
 */
public interface HopeDao {
    public Response createService(HopeServiceModel hopeServiceModel);
    public Response getAllServices();
}
