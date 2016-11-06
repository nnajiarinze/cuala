package com.tinkona.cuala.api.service;


import com.tinkona.cuala.api.dao.contract.JobDao;
import com.tinkona.cuala.api.model.Job;
import com.tinkona.cuala.api.model.JobCategory;
import com.tinkona.cuala.api.model.Response;
import com.tinkona.cuala.api.service.contract.JobService;
import org.springframework.stereotype.Service;

/**
 * Created by Nnaji.Arinze on 10/26/2016.
 */
@Service
public class JobServiceImplementation implements JobService {
    private final JobDao jobDao;

    public JobServiceImplementation(JobDao jobDao) {
        this.jobDao = jobDao;
    }

    @Override
    public Response create(Job job) {
        return jobDao.create(job);
    }

    @Override
    public Response fetchPaginated(int pageNum, int pageSize) {
       return jobDao.fetchPaginated(pageNum,pageSize);
    }

    @Override
    public Response getJobById(int id) {
        return jobDao.getJobById(id);
    }

    @Override
    public Response update(Job job) {
        return jobDao.update(job);
    }

    @Override
    public Response delete(int id) {
        return null;
    }

    @Override
    public Response createJobCategory(JobCategory jobCategory) {
       return jobDao.createJobCategory(jobCategory);
    }

    @Override
    public Response getAllCategories() {
        return jobDao.getAllCategories();
    }

    @Override
    public Response getJobsPerCateogry(int categoryId, int pageNum, int pageSize) {
        return jobDao.getJobsPerCateogry(categoryId,pageNum,pageSize);
    }


}
