package com.tinkona.cuala.api.dao;

import com.tinkona.cuala.api.dao.contract.EventDao;
import com.tinkona.cuala.api.model.Event;
import com.tinkona.cuala.api.model.News;
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
public class EventDaoImplementation implements EventDao {

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcCall create, update,getNewsById, fetchPaginated,delete;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        fetchPaginated = new SimpleJdbcCall(jdbcTemplate).withProcedureName("psp_fetch_paginated_events").returningResultSet("LIST", BeanPropertyRowMapper.newInstance(News.class));
        getNewsById = new SimpleJdbcCall(jdbcTemplate).withProcedureName("psp_get_news_by_id").returningResultSet("LIST", BeanPropertyRowMapper.newInstance(News.class));
        create = new SimpleJdbcCall(jdbcTemplate).withProcedureName("psp_create_event");
        update = new SimpleJdbcCall(jdbcTemplate).withProcedureName("psp_update_news");
        delete = new SimpleJdbcCall(jdbcTemplate).withProcedureName("psp_delete_news");
    }

    @Override
    public Response<Event> create(Event event) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("titlee", event.getTitle())
                .addValue("locationn", event.getLocation())
                .addValue("datee", event.getDate())
                .addValue("imagee", event.getImage())
                .addValue("descriptionn", event.getDescription())
                .addValue("created_datee", event.getCreatedDate());
        Map<String, Object> m = null;
        Response<Event> response =null;

        try{
            m = create.execute(in);
            int eventId =0;
            if(m!= null) {
                eventId = Integer.parseInt(String.valueOf(m.get("id")));
            }
            response = new Response<Event>("00","Creation Successful",eventId);
        }catch(Exception ex){

            response = new Response<Event>("500","News Not Created "+ex.getCause().getMessage(),0);
        }



        return response;
    }

    @Override
    public Response fetchAllEvents() {
        return null;
    }

    @Override
    public Response fetchPaginated(int pageNum, int pageSize) {
        return null;
    }

    @Override
    public Response getEventById(int id) {
        return null;
    }

    @Override
    public Response update(Event event) {
        return null;
    }

    @Override
    public Response delete(int id) {
        return null;
    }


}
