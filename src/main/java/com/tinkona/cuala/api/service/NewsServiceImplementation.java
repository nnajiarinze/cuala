package com.tinkona.cuala.api.service;

import com.tinkona.cuala.api.dao.contract.NewsDao;
import com.tinkona.cuala.api.model.News;
import com.tinkona.cuala.api.model.NewsComment;
import com.tinkona.cuala.api.model.Response;
import com.tinkona.cuala.api.service.contract.NewsService;
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
    public Response createComment(NewsComment newsComment) {
        return newsDao.createComment(newsComment);
    }

    @Override
    public Response fetchAllNews() {
        return null;
    }

    @Override
    public Response fetchPaginated(int pageNum, int pageSize) {
       return newsDao.fetchPaginated(pageNum,pageSize);
    }

    @Override
    public Response getNewsById(int id) {
        return newsDao.getNewsById(id);
    }

    @Override
    public Response update(News news) {
        return newsDao.update(news);
    }

    @Override
    public Response delete(int id) {
        return newsDao.delete(id);
    }

    @Override
    public Response fetchPaginatedComments(int pageNum, int pageSize, Integer id) {
        return newsDao.fetchPaginatedComments(pageNum,pageSize,id);
    }


}
