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
import java.util.Map;

/**
 * Created by Nnaji.Arinze on 10/26/2016.
 */
@Component
public class NewsDaoImplementation implements NewsDao {

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcCall create, update,fetchAll, getUserByPhone,
            getUserByUsername, getUserById, getUserByFBId, getUserByMatricNo;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        fetchAll = new SimpleJdbcCall(jdbcTemplate).withProcedureName("psp_retrieve_users").returningResultSet("LIST", BeanPropertyRowMapper.newInstance(User.class));
        create = new SimpleJdbcCall(jdbcTemplate).withProcedureName("psp_create_news");
        update = new SimpleJdbcCall(jdbcTemplate).withProcedureName("psp_update_user");
        getUserByPhone = new SimpleJdbcCall(jdbcTemplate).withProcedureName("psp_retrieve_user_by_phone").returningResultSet("LIST", BeanPropertyRowMapper.newInstance(User.class));
        getUserByUsername = new SimpleJdbcCall(jdbcTemplate).withProcedureName("psp_retrieve_user_by_email").returningResultSet("LIST", BeanPropertyRowMapper.newInstance(User.class));
        getUserById = new SimpleJdbcCall(jdbcTemplate).withProcedureName("psp_retrieve_user_by_id").returningResultSet("LIST", BeanPropertyRowMapper.newInstance(User.class));
        getUserByFBId = new SimpleJdbcCall(jdbcTemplate).withProcedureName("psp_retrieve_user_by_facebook_id").returningResultSet("LIST", BeanPropertyRowMapper.newInstance(User.class));
        getUserByMatricNo = new SimpleJdbcCall(jdbcTemplate).withProcedureName("psp_retrieve_user_by_matric_no").returningResultSet("LIST", BeanPropertyRowMapper.newInstance(User.class));
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
    public Response fetchPaginatedNews(int pageNum, int pageSize) {
        return null;
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
