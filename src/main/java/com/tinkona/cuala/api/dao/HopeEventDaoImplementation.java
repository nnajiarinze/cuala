package com.tinkona.cuala.api.dao;

import com.tinkona.cuala.api.dao.contract.HopeEventDao;
import com.tinkona.cuala.api.model.HopeEvent;
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
public class HopeEventDaoImplementation implements HopeEventDao {

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcCall create, update, getEventById,getEventByDate,fetchPaginated,delete,createInvitationResponse,getInvitationResponse,
            getEventsBetweenDates;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        fetchPaginated = new SimpleJdbcCall(jdbcTemplate).withProcedureName("psp_get_paginated_hope_events").returningResultSet("LIST", BeanPropertyRowMapper.newInstance(HopeEvent.class));
        getEventById = new SimpleJdbcCall(jdbcTemplate).withProcedureName("psp_get_hope_event_by_id").returningResultSet("LIST", BeanPropertyRowMapper.newInstance(HopeEvent.class));
        getEventByDate = new SimpleJdbcCall(jdbcTemplate).withProcedureName("psp_get_hope_event_by_date").returningResultSet("LIST", BeanPropertyRowMapper.newInstance(HopeEvent.class));
        getEventsBetweenDates = new SimpleJdbcCall(jdbcTemplate).withProcedureName("psp_get_hope_events_between_date").returningResultSet("LIST", BeanPropertyRowMapper.newInstance(HopeEvent.class));
        create = new SimpleJdbcCall(jdbcTemplate).withProcedureName("psp_create_hope_event");
    }

    @Override
    public Response<HopeEvent> create(HopeEvent hopeEvent) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("titlee", hopeEvent.getTitle())
                .addValue("service_idd", hopeEvent.getServiceId())
                .addValue("locationn", hopeEvent.getLocation())
                .addValue("datee", hopeEvent.getDate())
                .addValue("imagee", hopeEvent.getImage())
                .addValue("descriptionn", hopeEvent.getDescription())
                .addValue("created_datee", hopeEvent.getCreatedDate());
        Map<String, Object> m = null;
        Response<HopeEvent> response =null;

        try{
            m = create.execute(in);
            int eventId =0;
            if(m!= null) {
                eventId = Integer.parseInt(String.valueOf(m.get("id")));
            }
            response = new Response<HopeEvent>("00","Creation Successful",eventId);
        }catch(Exception ex){

            response = new Response<HopeEvent>("500","Event Not Created "+ex.getCause().getMessage(),0);
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
        HopeEvent hopeEvent =null;
        List<HopeEvent> hopeEventsList = new ArrayList<>();
        Response<HopeEvent> response =null;
        if (m.containsKey("list") && m.get("list") != null && ((List) m.get("list")).size() > 0) {
            hopeEventsList = (List<HopeEvent>) ((List) m.get("list"));
            response = new Response<HopeEvent>("00","Successful",hopeEventsList,hopeEvent);
        }else{
            response = new Response<HopeEvent>("","End of List",hopeEventsList,hopeEvent);
        }

        return response;
    }

    @Override
    public Response getEventById(int id) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("idd",id);
        Map<String, Object> m = getEventById.execute(in);
        HopeEvent hopeEvent = null;
        List<HopeEvent> eventsList = new ArrayList<>();
        Response<HopeEvent> response= null;
        if (m.containsKey("list") && m.get("list") != null && ((List) m.get("list")).size() > 0) {
            hopeEvent = (HopeEvent)((List)m.get("list")).get(0);
            response = new Response<HopeEvent>("00","Successful",eventsList, hopeEvent);
            response.setNoOfRecords(((List) m.get("list")).size());
        }else{
            response = new Response<HopeEvent>("00","No record",eventsList,hopeEvent);
        }
        return response;
    }

    @Override
    public Response update(HopeEvent hopeEvent) {
        return null;
    }

    @Override
    public Response delete(int id) {
        return null;
    }

    @Override
    public Response getEventsByDate(String date) {
        MapSqlParameterSource in = (new MapSqlParameterSource()).addValue("datee",date);
        Map<String, Object> m = getEventByDate.execute(in);
        HopeEvent event =null;
        List<HopeEvent> eventsList = new ArrayList<>();
        Response<HopeEvent> response =null;
        if (m.containsKey("list") && m.get("list") != null && ((List) m.get("list")).size() > 0) {
            eventsList = (List<HopeEvent>) ((List) m.get("list"));
            response = new Response<HopeEvent>("00","Successful",eventsList,event);
        }else{
            response = new Response<HopeEvent>("","End of List",eventsList,event);
        }

        return response;

    }

    @Override
    public Response getEventsBetweenDates(String startDate, String endDate) {
        MapSqlParameterSource in = (new MapSqlParameterSource()).addValue("start_datee",startDate)
                                    .addValue("end_datee",endDate);
        Map<String, Object> m = getEventsBetweenDates.execute(in);
        HopeEvent event =null;
        List<HopeEvent> eventsList = new ArrayList<>();
        Response<HopeEvent> response =null;
        if (m.containsKey("list") && m.get("list") != null && ((List) m.get("list")).size() > 0) {
            eventsList = (List<HopeEvent>) ((List) m.get("list"));
            response = new Response<HopeEvent>("00","Successful",eventsList,event);
        }else{
            response = new Response<HopeEvent>("","End of List",eventsList,event);
        }

        return response;

    }
}
