package com.tinkona.cuala.api.controller;

import com.tinkona.cuala.api.model.Event;
import com.tinkona.cuala.api.model.EventInvitation;
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


    @RequestMapping(value = {"/",""},method = RequestMethod.POST)
    public @ResponseBody
    Response<Event> create(@RequestBody Event event){
        return eventService.create(event);
    }


    @RequestMapping(value = "/paginate",method = RequestMethod.GET)
    public @ResponseBody
    Response<Event> fetchPaginated(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        return eventService.fetchPaginated(pageNum,pageSize);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public @ResponseBody
    Response<Event> getEventById(@PathVariable Integer id){

        return eventService.getEventById(id);
    }

    @RequestMapping(value = "",method = RequestMethod.GET)
    public @ResponseBody
    Response<Event> getEventByDate(@RequestParam(value = "date") String date){

        return eventService.getEventsByDate(date);
    }

    @RequestMapping(value = "/between",method = RequestMethod.GET)
    public @ResponseBody
    Response<Event> getEventBetweenDates(@RequestParam(value = "startDate") String startDate,@RequestParam(value = "endDate") String endDate){

        return eventService.getEventsBetweenDates(startDate,endDate);
    }

    @RequestMapping(value = "/{eventId}/{userId}/{response}",method = RequestMethod.POST)
    public @ResponseBody
    Response<Event> eventInvitation(@PathVariable Integer eventId, @PathVariable Integer userId,@PathVariable Boolean response){
        return eventService.createInvitationResponse(eventId,userId,response);
    }


    @RequestMapping(value = "/{eventId}/{userId}",method = RequestMethod.GET)
    public @ResponseBody
    Response<EventInvitation> getInvitationResponse(@PathVariable Integer eventId, @PathVariable Integer userId){
        return eventService.getInvitationResponse(eventId,userId);
    }




}
