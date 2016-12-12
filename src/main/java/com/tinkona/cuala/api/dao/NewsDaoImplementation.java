package com.tinkona.cuala.api.dao;

import com.tinkona.cuala.api.dao.contract.NewsDao;
import com.tinkona.cuala.api.model.News;
import com.tinkona.cuala.api.model.NewsComment;
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
public class NewsDaoImplementation implements NewsDao {

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcCall create, update,getNewsById, fetchPaginated,delete,create_comment;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        fetchPaginated = new SimpleJdbcCall(jdbcTemplate).withProcedureName("psp_fetch_paginated_news").returningResultSet("LIST", BeanPropertyRowMapper.newInstance(News.class));
        getNewsById = new SimpleJdbcCall(jdbcTemplate).withProcedureName("psp_get_news_by_id").returningResultSet("LIST", BeanPropertyRowMapper.newInstance(News.class));
        create = new SimpleJdbcCall(jdbcTemplate).withProcedureName("psp_create_news");
        create_comment = new SimpleJdbcCall(jdbcTemplate).withProcedureName("psp_create_news_comment");
        update = new SimpleJdbcCall(jdbcTemplate).withProcedureName("psp_update_news");
        delete = new SimpleJdbcCall(jdbcTemplate).withProcedureName("psp_delete_news");
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
    public Response<NewsComment> createComment(NewsComment newsComment) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("news_idd", newsComment.getNewsId())
                .addValue("user_idd", newsComment.getUserId())
                .addValue("commentt", newsComment.getComment());
        Map<String, Object> m = null;
        Response<NewsComment> response =null;

        try{
            m = create_comment.execute(in);
            int newsId =0;
            if(m!= null) {
                newsId = Integer.parseInt(String.valueOf(m.get("id")));
            }
            response = new Response<NewsComment>("00","Creation Successful",newsId);
        }catch(Exception ex){

            response = new Response<NewsComment>("500","Comment Not Created "+ex.getCause().getMessage(),0);
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
        }else{
             response = new Response<News>("","End of List",newsList,news);
         }

        return response;
    }

    @Override
    public Response getNewsById(int id) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("idd",id);
        Map<String, Object> m = getNewsById.execute(in);
        News news = null;
        List<News> newsList = new ArrayList<>();
        Response<News> response= null;
        if (m.containsKey("list") && m.get("list") != null && ((List) m.get("list")).size() > 0) {
            news = (News)((List)m.get("list")).get(0);
            response = new Response<News>("00","Operation Successful",newsList, news);
            response.setNoOfRecords(((List) m.get("list")).size());

            if(m.containsKey("#result-set-2")){
                news.setComments(((News) ((List)m.get("#result-set-2")).get(0)).getComments());
            }
        }else{
            response = new Response<News>("00","No record",newsList,news);
        }
        return response;
    }

    @Override
    public Response update(News news) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("idd",news.getId())
                .addValue("headlinee", news.getHeadline())
                .addValue("brieff", news.getBrief())
                .addValue("descriptionn",news.getDescription())
                .addValue("authorr", news.getAuthor())
                .addValue("publish_datee", news.getPublishDate())
                .addValue("tweet_textt",news.getTweetText())
                .addValue("created_datee",news.getCreatedDate())
                .addValue("tagss", news.getTags())
                .addValue("imagee",news.getImage())
                .addValue("deletedd", news.getDeleted());

        Map<String, Object> m = null;
        Response<News> response =null;

        try{
            m = update.execute(in);
            if(Integer.parseInt(m.get("#update-count-1").toString()) >0){
                response = new Response<News>("200","Updated",0);
            }else{
                response = new Response<News>("500","Item does not exist",0);
            }

        }catch(Exception ex){

            response = new Response<News>("500","Failed to update",0);
        }

        return response;
    }

    @Override
    public Response delete(int id) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("idd",id);
        Map<String, Object> m = null;
        Response<News> response =null;

        try{
            m = delete.execute(in);
            if(Integer.parseInt(m.get("#update-count-1").toString()) >0){
                response = new Response<News>("200","Deleted",0);
            }else{
                response = new Response<News>("500","Item does not exist",0);
            }

        }catch(Exception ex){

            response = new Response<News>("500","Failed to delete",0);
        }

        return response;
    }


}
