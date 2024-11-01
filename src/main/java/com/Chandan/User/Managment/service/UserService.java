package com.Chandan.User.Managment.service;

import com.Chandan.User.Managment.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public User saveUser(User user);
    public List<User> getAllUser();
    public User updateUser(Integer id,User userDetails);
    public void deleteUser(Integer userId);
    Optional<User> getUserById(Integer userId);


}
