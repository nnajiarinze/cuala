package com.tinkona.cuala.api.service;

import com.tinkona.cuala.api.dao.contract.HopeDao;
import com.tinkona.cuala.api.dao.contract.NewsDao;
import com.tinkona.cuala.api.model.HopeServiceModel;
import com.tinkona.cuala.api.model.News;
import com.tinkona.cuala.api.model.NewsComment;
import com.tinkona.cuala.api.model.Response;
import com.tinkona.cuala.api.service.contract.HopeService;
import com.tinkona.cuala.api.service.contract.NewsService;
import org.springframework.stereotype.Service;

/**
 * Created by Nnaji.Arinze on 10/26/2016.
 */
@Service
public class HopeServiceImplementation implements HopeService {
    private final HopeDao hopeDao;

    public HopeServiceImplementation(HopeDao hopeDao) {
        this.hopeDao = hopeDao;
    }

    @Override
    public Response createService(HopeServiceModel hopeServiceModel) {
        return hopeDao.createService(hopeServiceModel);
    }


    @Override
    public Response getAllServices() {
        return hopeDao.getAllServices();
    }
}
