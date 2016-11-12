package com.tinkona.cuala.api.service.contract;

import com.tinkona.cuala.api.model.Response;
import com.tinkona.cuala.api.model.User;

import java.util.List;

/**
 * Created by Nnaji.Arinze on 10/2/2016.
 */
public interface UserService {
    public Response createUser(User user);
    public Response getAllUsers(int pageNum, int pageSize);
    public Response getUserById(int id);
    public Response getUserByFacebookId(int facebookId);
    public Response getUserByPhone(String mobileNumber);
    public Response getUserByEmail(String email);
    public Response getUserByMatricNo(String matricNumber);
    public Response updateUser(User user);
    public Response deleteUserById(int contactId);
    public Response updateUserStatus(int userId,String status);
    public Response fetchAllUsersByCourse(String course,int pageNum, int pageSize);
    public Response fetchAllUsersByGradYear(int gradYear,int pageNum, int pageSize);
    public Response search(String name, int pageNum, int pageSize);
}
