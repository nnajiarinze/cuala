package com.tinkona.cuala.api.dao;

import com.tinkona.cuala.api.dao.contract.NewsDao;
import com.tinkona.cuala.api.model.News;
import com.tinkona.cuala.api.model.Response;
import com.tinkona.cuala.api.model.User;
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
public class NewsDaoImplementation implements NewsDao {

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcCall create, update,fetchAll, fetchPaginated;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        fetchAll = new SimpleJdbcCall(jdbcTemplate).withProcedureName("psp_retrieve_users").returningResultSet("LIST", BeanPropertyRowMapper.newInstance(News.class));
        fetchPaginated = new SimpleJdbcCall(jdbcTemplate).withProcedureName("psp_fetch_paginated").returningResultSet("LIST", BeanPropertyRowMapper.newInstance(News.class));
        create = new SimpleJdbcCall(jdbcTemplate).withProcedureName("psp_create_news");
        update = new SimpleJdbcCall(jdbcTemplate).withProcedureName("psp_update_user");
    }

    @Override
    public Response<News> create(News news) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("headlinee", news.getHeadline())
                .addValue("brieff", news.getBrief())
                .addValue("descriptionn", news.getDescription())
                .addValue("publish_datee", news.getPublishDate())
                .addValue("authorr",news.getAuthor())
                .addValue("tweet_textt", news.getTweetText())
                .addValue("created_datee", news.getCreatedDate())
                .addValue("tagss", news.getTags())
                .addValue("imagee", news.getImage())
                .addValue("deletedd",news.getDeleted());
        Map<String, Object> m = null;
        Response<News> response =null;

        try{
            m = create.execute(in);
            int newsId =0;
            if(m!= null) {
                newsId = Integer.parseInt(String.valueOf(m.get("id")));
            }
            response = new Response<News>("00","Creation Successful",newsId);
        }catch(Exception ex){

            response = new Response<News>("500","News Not Created "+ex.getCause().getMessage(),0);
        }



        return response;
    }

    @Override
    public Response fetchAllNews() {
        return null;
    }

    @Override
    public Response fetchPaginated(int pageNum, int pageSize) {
        MapSqlParameterSource in = (new MapSqlParameterSource()).addValue("page_num", pageNum).addValue("page_size", pageSize);
        Map<String, Object> m = fetchPaginated.execute(in);
        News news =null;
        List<News> newsList = new ArrayList<>();
        Response<News> response =null;
         if (m.containsKey("list") && m.get("list") != null && ((List) m.get("list")).size() > 0) {
            newsList = (List<News>) ((List) m.get("list"));
             response = new Response<News>("00","Successful",newsList,news);
        }

        return response;
    }

    @Override
    public Response getNewsById(int id) {
        return null;
    }

    @Override
    public Response update(int id) {
        return null;
    }


}
