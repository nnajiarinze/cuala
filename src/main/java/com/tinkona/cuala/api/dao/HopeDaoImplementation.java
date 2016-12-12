package com.tinkona.cuala.api.dao;

import com.tinkona.cuala.api.dao.contract.HopeDao;
import com.tinkona.cuala.api.model.HopeServiceModel;
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
public class HopeDaoImplementation implements HopeDao {

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcCall createService, fetchAllServices;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        createService = new SimpleJdbcCall(jdbcTemplate).withProcedureName("psp_create_hope_service");
        fetchAllServices = new SimpleJdbcCall(jdbcTemplate).withProcedureName("psp_get_hope_services").returningResultSet("LIST", BeanPropertyRowMapper.newInstance(JobCategory.class));
    }


    public Response createService(HopeServiceModel hopeServiceModel) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("namee", hopeServiceModel.getName());

        Map<String, Object> m = null;
        Response<JobCategory> response =null;
        try{
            m = createService.execute(in);
            int serviceId =0;
            if(m!= null) {
                serviceId  = Integer.parseInt(String.valueOf(m.get("id")));
            }
            response = new Response<JobCategory>("00","Creation Successful",serviceId);
        }catch(Exception ex){

            response = new Response<JobCategory>("500","Service Not Created "+ex.getCause().getMessage(),0);
        }
        return response;
    }


    public Response getAllServices() {
        Map<String, Object> m = fetchAllServices.execute();
        HopeServiceModel hopeServiceModel = null;
        List<HopeServiceModel> list = new ArrayList<>();
        if (m.containsKey("list") && m.get("list") != null && ((List) m.get("list")).size() > 0) {
            list = (List<HopeServiceModel>) ((List) m.get("list"));
        }
        Response<HopeServiceModel> response = new Response<>("00","Operation Successful",list, hopeServiceModel);
        return response;

    }


}
