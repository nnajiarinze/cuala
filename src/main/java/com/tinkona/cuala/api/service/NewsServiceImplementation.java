package com.tinkona.cuala.api.service;

import com.tinkona.cuala.api.dao.contract.NewsDao;
import com.tinkona.cuala.api.dao.contract.UserDao;
import com.tinkona.cuala.api.model.News;
import com.tinkona.cuala.api.model.Response;
import com.tinkona.cuala.api.model.User;
import com.tinkona.cuala.api.service.contract.NewsService;
import com.tinkona.cuala.api.service.contract.UserService;
import org.springframework.stereotype.Service;

/**
 * Created by Nnaji.Arinze on 10/26/2016.
 */
@Service
public class NewsServiceImplementation implements NewsService {
    private final NewsDao newsDao;

    public NewsServiceImplementation(NewsDao newsDao) {
        this.newsDao = newsDao;
    }

    @Override
    public Response create(News news) {
        return newsDao.create(news);
    }

    @Override
    public Response fetchAllNews() {
        return null;
    }

    @Override
    public Response fetchPaginatedNews(int pageNum, int pageSize) {
        return null;
    }

    @Override
    public Response getNewsById(int id) {
        return null;
    }

    @Override
    public Response update(int id) {
        return null;
    }


}
