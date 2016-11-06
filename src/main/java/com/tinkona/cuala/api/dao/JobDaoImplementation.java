package com.tinkona.cuala.api.dao;

import com.tinkona.cuala.api.dao.contract.JobDao;
import com.tinkona.cuala.api.model.EventInvitation;
import com.tinkona.cuala.api.model.Job;
import com.tinkona.cuala.api.model.JobCategory;
import com.tinkona.cuala.api.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Nnaji.Arinze on 10/26/2016.
 */
@Component
public class JobDaoImplementation implements JobDao {

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcCall create,createJobCategory, update, getJobById,getJobsByCategoryId, fetchPaginated,fetchAllCategories;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        fetchPaginated = new SimpleJdbcCall(jdbcTemplate).withProcedureName("psp_get_paginated_jobs").returningResultSet("LIST", BeanPropertyRowMapper.newInstance(Job.class));
        getJobById = new SimpleJdbcCall(jdbcTemplate).withProcedureName("psp_get_job_by_id").returningResultSet("LIST", BeanPropertyRowMapper.newInstance(Job.class));
         create = new SimpleJdbcCall(jdbcTemplate).withProcedureName("psp_create_job");
        createJobCategory = new SimpleJdbcCall(jdbcTemplate).withProcedureName("psp_create_job_category");
        update = new SimpleJdbcCall(jdbcTemplate).withProcedureName("psp_update_job");
        fetchAllCategories = new SimpleJdbcCall(jdbcTemplate).withProcedureName("psp_get_job_categories").returningResultSet("LIST", BeanPropertyRowMapper.newInstance(JobCategory.class));
        getJobsByCategoryId = new SimpleJdbcCall(jdbcTemplate).withProcedureName("psp_get_jobs_per_category").returningResultSet("LIST", BeanPropertyRowMapper.newInstance(Job.class));

    }

    @Override
    public Response<Job> create(Job job) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("category_idd", job.getCategoryId())
                .addValue("titlee", job.getTitle())
                .addValue("locationn", job.getLocation())
                .addValue("descriptionn", job.getDescription())
                .addValue("created_datee", job.getCreatedDate())
                .addValue("end_datee", job.getEndDate());

        Map<String, Object> m = null;
        Response<Job> response =null;

        try{
            m = create.execute(in);
            int jobId =0;
            if(m!= null) {
                jobId = Integer.parseInt(String.valueOf(m.get("id")));
            }
            response = new Response<Job>("00","Creation Successful",jobId);
        }catch(Exception ex){

            response = new Response<Job>("500","Job Not Created "+ex.getCause().getMessage(),0);
        }
        return response;
    }

    @Override
    public Response fetchPaginated(int pageNum, int pageSize) {
        MapSqlParameterSource in = (new MapSqlParameterSource()).addValue("page_num", pageNum).addValue("page_size", pageSize);
        Map<String, Object> m = fetchPaginated.execute(in);
        Job job =null;
        List<Job> jobsList = new ArrayList<>();
        Response<Job> response =null;
        if (m.containsKey("list") && m.get("list") != null && ((List) m.get("list")).size() > 0) {
            jobsList = (List<Job>) ((List) m.get("list"));
            response = new Response<Job>("00","Successful",jobsList,job);
        }else{
            response = new Response<Job>("","End of List",jobsList,job);
        }

        return response;
    }

    @Override
    public Response getJobById(int id) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("idd",id);
        Map<String, Object> m = getJobById.execute(in);
        Job job = null;
        List<Job> jobsList = new ArrayList<>();
        Response<Job> response= null;
        if (m.containsKey("list") && m.get("list") != null && ((List) m.get("list")).size() > 0) {
            job = (Job) ((List)m.get("list")).get(0);
            response = new Response<Job>("00","Successful",jobsList, job);
            response.setNoOfRecords(((List) m.get("list")).size());
        }else{
            response = new Response<Job>("00","No record",jobsList,job);
        }
        return response;
    }

    @Override
    public Response update(Job job) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("idd",job.getId())
                .addValue("category_idd", job.getCategoryId())
                .addValue("titlee", job.getTitle())
                .addValue("locationn",job.getLocation())
                .addValue("descriptionn", job.getDescription())
                .addValue("created_datee", job.getCreatedDate())
                .addValue("end_datee",job.getEndDate());

        Map<String, Object> m = null;
        Response<Job> response =null;

        try{
            m = update.execute(in);
            if(Integer.parseInt(m.get("#update-count-1").toString()) >0){
                response = new Response<Job>("200","Updated",0);
            }else{
                response = new Response<Job>("500","Item does not exist",0);
            }

        }catch(Exception ex){

            response = new Response<Job>("500","Failed to update",0);
        }

        return response;
    }

    @Override
    public Response delete(int id) {
        return null;
    }

    @Override
    public Response createJobCategory(JobCategory jobCategory) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("namee", jobCategory.getName());

        Map<String, Object> m = null;
        Response<JobCategory> response =null;
        try{
            m = createJobCategory.execute(in);
            int categoryId =0;
            if(m!= null) {
                categoryId = Integer.parseInt(String.valueOf(m.get("id")));
            }
            response = new Response<JobCategory>("00","Creation Successful",categoryId);
        }catch(Exception ex){

            response = new Response<JobCategory>("500","Category Not Created "+ex.getCause().getMessage(),0);
        }
        return response;
    }

    @Override
    public Response getAllCategories() {
        Map<String, Object> m = fetchAllCategories.execute();
        JobCategory user = null;
        List<JobCategory> list = new ArrayList<>();
        if (m.containsKey("list") && m.get("list") != null && ((List) m.get("list")).size() > 0) {
            list = (List<JobCategory>) ((List) m.get("list"));
        }
        Response<JobCategory> response = new Response<>("00","Operation Successful",list, user);
        return response;

    }


    @Override
    public Response getJobsPerCateogry(int categoryId,int pageNum, int pageSize) {
        MapSqlParameterSource in = (new MapSqlParameterSource()).addValue("page_num", pageNum).addValue("page_size", pageSize).addValue("category_idd",categoryId);
        Map<String, Object> m = getJobsByCategoryId.execute(in);
        Job job =null;
        List<Job> jobsList = new ArrayList<>();
        Response<Job> response =null;
        if (m.containsKey("list") && m.get("list") != null && ((List) m.get("list")).size() > 0) {
            jobsList = (List<Job>) ((List) m.get("list"));
            response = new Response<Job>("00","Successful",jobsList,job);
        }else{
            response = new Response<Job>("","End of List",jobsList,job);
        }

        return response;
    }

}
