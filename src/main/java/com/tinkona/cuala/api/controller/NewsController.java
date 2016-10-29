package com.tinkona.cuala.api.controller;

import com.tinkona.cuala.api.model.News;
import com.tinkona.cuala.api.model.Response;
import com.tinkona.cuala.api.model.User;
import com.tinkona.cuala.api.service.contract.NewsService;
import com.tinkona.cuala.api.service.contract.UserService;
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
    Response<User>   create(@RequestBody News news){
        return newsService.create(news);
    }



}
