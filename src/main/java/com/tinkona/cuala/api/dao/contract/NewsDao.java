package com.tinkona.cuala.api.dao.contract;

import com.tinkona.cuala.api.model.News;
import com.tinkona.cuala.api.model.NewsComment;
import com.tinkona.cuala.api.model.Response;

/**
 * Created by Nnaji.Arinze on 10/26/2016.
 */
public interface NewsDao {
    public Response create(News news);
    public Response createComment(NewsComment newsComment);
    public Response fetchAllNews();
    public Response fetchPaginated(int pageNum, int pageSize);
    public Response getNewsById(int id);
    public Response update(News news);
    public Response delete(int id);

}
