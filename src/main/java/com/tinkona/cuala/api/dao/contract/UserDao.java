package com.tinkona.cuala.api.dao.contract;

import com.tinkona.cuala.api.model.Response;
import com.tinkona.cuala.api.model.User;
/**
 * Created by Nnaji.Arinze on 10/26/2016.
 */
public interface UserDao {
    public Response create(User user);
    public Response fetchAllUsers(int pageNum, int pageSize);
    public Response getUserById(int id);
    public Response getUserByFBId(int fbId);
    public Response getUserByPhone(String phone);
    public Response getUserByEmail(String email);
    public Response getUserByMatricNo(String matricNo);
    public Response updateUser(User user);
    public Response deleteUserById(int contactId);
    public Response updateUserStatus(int userId,String status);
    public Response fetchAllUsersByCourse(String course,int pageNum, int pageSize);
    public Response fetchAllUsersByGradYear(int gradYear,int pageNum, int pageSize);
    public Response search(String name, int pageNum, int pageSize);
}
