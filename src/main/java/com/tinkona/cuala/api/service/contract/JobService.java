package com.tinkona.cuala.api.service.contract;

import com.tinkona.cuala.api.model.Job;
import com.tinkona.cuala.api.model.JobCategory;
import com.tinkona.cuala.api.model.Response;

/**
 * Created by Nnaji.Arinze on 10/2/2016.
 */
public interface JobService {
    public Response create(Job job);
    public Response fetchPaginated(int pageNum, int pageSize);
    public Response getJobById(int id);
    public Response update(Job job);
    public Response delete(int id);
    public Response createJobCategory(JobCategory jobCategory);
    public Response getAllCategories();
    public Response getJobsPerCateogry(int categoryId, int pageNum, int pageSize);

}
