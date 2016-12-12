package com.tinkona.cuala.api.controller;


import com.tinkona.cuala.api.model.HopeServiceModel;
import com.tinkona.cuala.api.model.Job;
import com.tinkona.cuala.api.model.JobCategory;
import com.tinkona.cuala.api.model.Response;
import com.tinkona.cuala.api.service.contract.HopeService;
import com.tinkona.cuala.api.service.contract.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Nnaji.Arinze on 10/2/2016.
 */
@RestController
@RequestMapping(value = "api/hope")
public class HopeController {
    private final HopeService hopeService;

    @Autowired
    public HopeController(HopeService hopeService) {
        this.hopeService = hopeService;
    }


    @RequestMapping(value = "/service",method = RequestMethod.POST)
    public @ResponseBody
    Response<Job> createService(@RequestBody HopeServiceModel hopeServiceModel){
        return hopeService.createService(hopeServiceModel);
    }

    @RequestMapping(value = "/services",method = RequestMethod.GET)
    public @ResponseBody
    Response<Job> getAllServices(){
        return hopeService.getAllServices();
    }



}
