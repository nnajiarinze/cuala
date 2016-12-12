package com.tinkona.cuala.api.controller;


import com.tinkona.cuala.api.model.HopeEvent;
import com.tinkona.cuala.api.model.Response;

import com.tinkona.cuala.api.service.contract.HopeEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Nnaji.Arinze on 10/2/2016.
 */
@RestController
@RequestMapping(value = "api/hope/events")
public class HopeEventController {
    private final HopeEventService hopeEventService;

    @Autowired
    public HopeEventController(HopeEventService hopeEventService) {
        this.hopeEventService = hopeEventService;
    }


    @RequestMapping(value = {"/",""},method = RequestMethod.POST)
    public @ResponseBody
    Response<HopeEvent> create(@Validated @RequestBody HopeEvent hopeEvent){
        return hopeEventService.create(hopeEvent);
    }


    @RequestMapping(value = "/paginate",method = RequestMethod.GET)
    public @ResponseBody
    Response<HopeEvent> fetchPaginated(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        return hopeEventService.fetchPaginated(pageNum,pageSize);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public @ResponseBody
    Response<HopeEvent> getEventById(@PathVariable Integer id){

        return hopeEventService.getEventById(id);
    }

    @RequestMapping(value = "",method = RequestMethod.GET)
    public @ResponseBody
    Response<HopeEvent> getEventByDate(@RequestParam(value = "date") String date){

        return hopeEventService.getEventsByDate(date);
    }

    @RequestMapping(value = "/between",method = RequestMethod.GET)
    public @ResponseBody
    Response<HopeEvent> getEventBetweenDates(@RequestParam(value = "startDate") String startDate,@RequestParam(value = "endDate") String endDate){

        return hopeEventService.getEventsBetweenDates(startDate,endDate);
    }



}
