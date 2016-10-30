package com.tinkona.cuala.api.controller;

import com.tinkona.cuala.api.model.News;
import com.tinkona.cuala.api.model.Response;
import com.tinkona.cuala.api.service.contract.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Nnaji.Arinze on 10/2/2016.
 */
@RestController
@RequestMapping(value = "api/news")
public class NewsController {
    private final NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }


    @RequestMapping(value = "/",method = RequestMethod.POST)
    public @ResponseBody
    Response<News> create(@RequestBody News news){
        return newsService.create(news);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public @ResponseBody
    Response<News> getNewsById(@PathVariable Integer id){

        return newsService.getNewsById(id);
    }

    @RequestMapping(value = "/paginate",method = RequestMethod.GET)
    public @ResponseBody
    Response<News> fetchNews(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        return newsService.fetchPaginated(pageNum,pageSize);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.POST)
    public @ResponseBody
    Response<News> update(@RequestBody News news, @PathVariable Integer id){
        news.setId(id);
        return newsService.update(news);
    }


    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public @ResponseBody
    Response<News> delete(@PathVariable Integer id){
        return newsService.delete(id);
    }



}
