package com.tinkona.cuala.api.controller;

import com.tinkona.cuala.api.model.Response;
import com.tinkona.cuala.api.model.User;
import com.tinkona.cuala.api.service.contract.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Nnaji.Arinze on 10/2/2016.
 */
@RestController
@RequestMapping(value = "api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value = "/",method = RequestMethod.POST)
    public @ResponseBody
    Response<User>   createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    Response<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public @ResponseBody
    Response<User> getUserById(@PathVariable int id){
        return userService.getUserById(id);
    }

    @RequestMapping(value = "/matric/{matric}",method = RequestMethod.GET)
    public @ResponseBody
    Response<User> getUserByMatricNo(@PathVariable String matric){
        return userService.getUserByMatricNo(matric);
    }

    @RequestMapping(value = "/facebookId/{facebookId}",method = RequestMethod.GET)
    public @ResponseBody
    Response<User> getUserByFacebookId(@PathVariable int facebookId){
        return userService.getUserByFacebookId(facebookId);
    }

    @RequestMapping(value = "/email",method = RequestMethod.GET)
    public @ResponseBody
    Response<User> getUserByEmail(@RequestParam String email){
        return userService.getUserByEmail(email);
    }

    @RequestMapping(value = "phone/{phone}",method = RequestMethod.GET)
    public @ResponseBody
    Response<User> getUserByPhone(@PathVariable String phone){
        return userService.getUserByPhone(phone);
    }


    @RequestMapping(value = "/{id}",method = RequestMethod.POST)
    public @ResponseBody
    Response<User> updateUser(@PathVariable Integer id,@RequestBody @Validated User user){
        user.setId(id);
        return userService.updateUser(user);
    }

}
