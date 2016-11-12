package com.tinkona.cuala.api.service;

import com.tinkona.cuala.api.dao.contract.UserDao;
import com.tinkona.cuala.api.model.Response;
import com.tinkona.cuala.api.model.User;
import com.tinkona.cuala.api.service.contract.UserService;
import org.springframework.stereotype.Service;

/**
 * Created by Nnaji.Arinze on 10/26/2016.
 */
@Service
public class UserServiceImplementation implements UserService {
    private final UserDao userDao;

    public UserServiceImplementation(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Response createUser(User user) {
        return userDao.create(user);
    }

    @Override
    public Response getAllUsers(int pageNum, int pageSize) {
        return userDao.fetchAllUsers(pageNum, pageSize);
    }

    @Override
    public Response getUserById(int id) {
       return userDao.getUserById(id);
    }

    @Override
    public Response getUserByFacebookId(int facebookId) {
        return userDao.getUserByFBId(facebookId);
    }

    @Override
    public Response getUserByPhone(String mobileNumber) {
        return userDao.getUserByPhone(mobileNumber);
    }

    @Override
    public Response getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    @Override
    public Response getUserByMatricNo(String matricNumber) {
        return userDao.getUserByMatricNo(matricNumber);
    }

    @Override
    public Response updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public Response deleteUserById(int userId) {
        return userDao.deleteUserById(userId);
    }

    @Override
    public Response updateUserStatus(int userId, String status) {
        return userDao.updateUserStatus(userId,status);
    }

    @Override
    public Response fetchAllUsersByCourse(String course, int pageNum, int pageSize) {
        return userDao.fetchAllUsersByCourse(course,pageNum,pageSize);
    }

    @Override
    public Response fetchAllUsersByGradYear(int gradYear, int pageNum, int pageSize) {
        return userDao.fetchAllUsersByGradYear(gradYear,pageNum,pageSize);
    }

    @Override
    public Response search(String name, int pageNum, int pageSize) {
        return userDao.search(name,pageNum,pageSize);
    }
}
