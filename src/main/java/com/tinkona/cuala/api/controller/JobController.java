package com.tinkona.cuala.api.controller;


import com.tinkona.cuala.api.model.Job;
import com.tinkona.cuala.api.model.JobCategory;
import com.tinkona.cuala.api.model.Response;
import com.tinkona.cuala.api.service.contract.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Nnaji.Arinze on 10/2/2016.
 */
@RestController
@RequestMapping(value = "api/jobs")
public class JobController {
    private final JobService jobService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }


    @RequestMapping(value = "/category",method = RequestMethod.POST)
    public @ResponseBody
    Response<Job> create_category(@RequestBody JobCategory jobCategory){
        return jobService.createJobCategory(jobCategory);
    }

    @RequestMapping(value = "/category",method = RequestMethod.GET)
    public @ResponseBody
    Response<Job> get_categories(){
        return jobService.getAllCategories();
    }


    @RequestMapping(value = "/",method = RequestMethod.POST)
    public @ResponseBody
    Response<Job> create(@RequestBody Job job){
        return jobService.create(job);
    }



    @RequestMapping(value = "/paginate",method = RequestMethod.GET)
    public @ResponseBody
    Response<Job> fetchPaginated(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        return jobService.fetchPaginated(pageNum,pageSize);
    }

    @RequestMapping(value = "",method = RequestMethod.GET)
    public @ResponseBody
    Response<Job> getJobsByCategory(@RequestParam(value = "categoryId") int categoryId,@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        return jobService.getJobsPerCateogry(categoryId,pageNum,pageSize);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public @ResponseBody
    Response<Job> getJobById(@PathVariable Integer id){

        return jobService.getJobById(id);
    }


    @RequestMapping(value = "/{id}",method = RequestMethod.POST)
    public @ResponseBody
    Response<Job> update(@RequestBody Job job, @PathVariable Integer id){
        job.setId(id);
        return jobService.update(job);
    }




}
