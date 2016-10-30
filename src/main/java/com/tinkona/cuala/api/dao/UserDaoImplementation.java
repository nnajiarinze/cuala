package com.tinkona.cuala.api.dao;

import com.tinkona.cuala.api.dao.contract.UserDao;
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
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Nnaji.Arinze on 10/26/2016.
 */
@Component
public class UserDaoImplementation implements UserDao {

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcCall create, update,fetchAll, getUserByPhone,
            getUserByUsername, getUserById, getUserByFBId, getUserByMatricNo;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        fetchAll = new SimpleJdbcCall(jdbcTemplate).withProcedureName("psp_retrieve_users").returningResultSet("LIST", BeanPropertyRowMapper.newInstance(User.class));
        create = new SimpleJdbcCall(jdbcTemplate).withProcedureName("psp_create_user");
        update = new SimpleJdbcCall(jdbcTemplate).withProcedureName("psp_update_user");
        getUserByPhone = new SimpleJdbcCall(jdbcTemplate).withProcedureName("psp_retrieve_user_by_phone").returningResultSet("LIST", BeanPropertyRowMapper.newInstance(User.class));
        getUserByUsername = new SimpleJdbcCall(jdbcTemplate).withProcedureName("psp_retrieve_user_by_email").returningResultSet("LIST", BeanPropertyRowMapper.newInstance(User.class));
        getUserById = new SimpleJdbcCall(jdbcTemplate).withProcedureName("psp_retrieve_user_by_id").returningResultSet("LIST", BeanPropertyRowMapper.newInstance(User.class));
        getUserByFBId = new SimpleJdbcCall(jdbcTemplate).withProcedureName("psp_retrieve_user_by_facebook_id").returningResultSet("LIST", BeanPropertyRowMapper.newInstance(User.class));
        getUserByMatricNo = new SimpleJdbcCall(jdbcTemplate).withProcedureName("psp_retrieve_user_by_matric_no").returningResultSet("LIST", BeanPropertyRowMapper.newInstance(User.class));
    }

    @Override
    public Response<User> create(User user) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("fb_idd", user.getFbId())
                .addValue("namee", user.getName())
                .addValue("emaill", user.getEmail())
                .addValue("phonee", user.getPhone())
                .addValue("matric_noo", user.getMatricNo())
                .addValue("reg_noo",user.getRegNo())
                .addValue("grad_yearr", user.getGradYear())
                .addValue("coursee", user.getCourse())
                .addValue("occupationn", user.getOccupation());
        Map<String, Object> m = null;
        Response<User> response =null;

        try{
            m = create.execute(in);
            int userId =0;
            if(m!= null) {
                userId = Integer.parseInt(String.valueOf(m.get("id")));
            }
            response = new Response<User>("00","User Creation Successful",userId);
        }catch(Exception ex){

            response = new Response<User>("500","User Not Created "+ex.getCause().getMessage(),0);
        }



        return response;
    }

    @Override
    public Response fetchAllUsers() {
        Map<String, Object> m = fetchAll.execute();
        User user = null;
        List<User> list = new ArrayList<>();
        if (m.containsKey("list") && m.get("list") != null && ((List) m.get("list")).size() > 0) {
            list = (List<User>) ((List) m.get("list"));
        }
        Response<User> response = new Response<>("00","Operation Successful",list, user);
        return response;
    }

    @Override
    public Response getUserById(int id) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("userId",id);
        Map<String, Object> m = getUserById.execute(in);
        User user = null;
        List<User> list = new ArrayList<>();
        if (m.containsKey("list") && m.get("list") != null && ((List) m.get("list")).size() > 0) {
            user = (User)((List)m.get("list")).get(0);
        }
        Response<User> response = new Response<>("00","Operation Successful",list, user);
        response.setNoOfRecords(list.size());
        return response;
    }

    @Override
    public Response getUserByFBId(int fbId) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("facebook_id", fbId);
        Map<String, Object> m = getUserByFBId.execute(in);
        User userr = null;
        List<User> list = new ArrayList<>();
        if (m.containsKey("list") && m.get("list") != null && ((List) m.get("list")).size() > 0) {
            userr = (User)((List)m.get("list")).get(0);
        }
        Response<User> response = new Response<>("00","Operation Successful",list, userr);
        return response;
    }

    @Override
    public Response getUserByPhone(String phone) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("mobileNumber", phone);
        Map<String, Object> m = getUserByPhone.execute(in);
        User userr = null;
        List<User> list = new ArrayList<>();
        if (m.containsKey("list") && m.get("list") != null && ((List) m.get("list")).size() > 0) {
            userr = (User)((List)m.get("list")).get(0);
        }
        Response<User> response = new Response<>("00","Operation Successful",list, userr);
        return response;
    }

    @Override
    public Response getUserByEmail(String email) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("userEmail",email.trim(), Types.VARCHAR);
        Map<String, Object> m = getUserByUsername.execute(in);
        User user = null;
        List<User> list = new ArrayList<>();
        if (m.containsKey("list") && m.get("list") != null && ((List) m.get("list")).size() > 0) {
            user = (User)((List)m.get("list")).get(0);
        }
        Response<User> response = new Response<>("00","Operation Successful",list, user);
        response.setNoOfRecords(list.size());
        return response;
    }

    @Override
    public Response getUserByMatricNo(String matricNo) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("matricNumber", matricNo);
        Map<String, Object> m = getUserByMatricNo.execute(in);
        User user = null;
        List<User> list = new ArrayList<>();
        if (m.containsKey("list") && m.get("list") != null && ((List) m.get("list")).size() > 0) {
            user = (User)((List)m.get("list")).get(0);
        }
        Response<User> response = new Response<>("00","Operation Successful",list, user);
        response.setNoOfRecords(list.size());
        return response;
    }

    @Override
    public Response updateUser(User user) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("idd",user.getId())
                .addValue("fb_idd", user.getFbId())
                .addValue("namee", user.getName())
                .addValue("emaill", user.getEmail())
                .addValue("phonee", user.getPhone())
                .addValue("matric_noo", user.getMatricNo())
                .addValue("reg_noo",user.getRegNo())
                .addValue("grad_yearr", user.getGradYear())
                .addValue("coursee", user.getCourse())
                .addValue("occupationn", user.getOccupation());

        Map<String, Object> m = null;
        Response<User> response =null;

        try{
            m = update.execute(in);
            if(Integer.parseInt(m.get("#update-count-1").toString()) >0){
                response = new Response<User>("200","User Updated",0);
            }else{
                response = new Response<User>("500","User does not exist",0);
            }

        }catch(Exception ex){

            response = new Response<User>("500","User Not Updated",0);
        }

        return response;

    }

    @Override
    public Response deleteUserById(int contactId) {
        return null;
    }

    @Override
    public Response updateUserStatus(int userId, String status) {
        return null;
    }

}
