package com.tinkona.cuala.api.controller;

import com.tinkona.cuala.api.model.Event;
import com.tinkona.cuala.api.model.Response;
import com.tinkona.cuala.api.service.contract.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Nnaji.Arinze on 10/2/2016.
 */
@RestController
@RequestMapping(value = "api/events")
public class EventController {
    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }


    @RequestMapping(value = "/",method = RequestMethod.POST)
    public @ResponseBody
    Response<Event> create(@RequestBody Event event){
        return eventService.create(event);
    }



}
