package com.tinkona.cuala.api.dao;

import com.tinkona.cuala.api.dao.contract.EventDao;
import com.tinkona.cuala.api.model.Event;
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
    private SimpleJdbcCall create, update, getEventById, fetchPaginated,delete;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        fetchPaginated = new SimpleJdbcCall(jdbcTemplate).withProcedureName("psp_get_paginated_events").returningResultSet("LIST", BeanPropertyRowMapper.newInstance(Event.class));
        getEventById = new SimpleJdbcCall(jdbcTemplate).withProcedureName("psp_get_event_by_id").returningResultSet("LIST", BeanPropertyRowMapper.newInstance(Event.class));
        create = new SimpleJdbcCall(jdbcTemplate).withProcedureName("psp_create_event");
        update = new SimpleJdbcCall(jdbcTemplate).withProcedureName("psp_update_event");
        delete = new SimpleJdbcCall(jdbcTemplate).withProcedureName("psp_delete_event");
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

            response = new Response<Event>("500","Event Not Created "+ex.getCause().getMessage(),0);
        }



        return response;
    }

    @Override
    public Response fetchAllEvents() {
        return null;
    }

    @Override
    public Response fetchPaginated(int pageNum, int pageSize) {
        MapSqlParameterSource in = (new MapSqlParameterSource()).addValue("page_num", pageNum).addValue("page_size", pageSize);
        Map<String, Object> m = fetchPaginated.execute(in);
        Event event =null;
        List<Event> eventsList = new ArrayList<>();
        Response<Event> response =null;
        if (m.containsKey("list") && m.get("list") != null && ((List) m.get("list")).size() > 0) {
            eventsList = (List<Event>) ((List) m.get("list"));
            response = new Response<Event>("00","Successful",eventsList,event);
        }else{
            response = new Response<Event>("","End of List",eventsList,event);
        }

        return response;
    }

    @Override
    public Response getEventById(int id) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("idd",id);
        Map<String, Object> m = getEventById.execute(in);
        Event event = null;
        List<Event> eventsList = new ArrayList<>();
        Response<Event> response= null;
        if (m.containsKey("list") && m.get("list") != null && ((List) m.get("list")).size() > 0) {
            event = (Event)((List)m.get("list")).get(0);
            response = new Response<Event>("00","Successful",eventsList, event);
            response.setNoOfRecords(((List) m.get("list")).size());
        }else{
            response = new Response<Event>("00","No record",eventsList,event);
        }
        return response;
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
