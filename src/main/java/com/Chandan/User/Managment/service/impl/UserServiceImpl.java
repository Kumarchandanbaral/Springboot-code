package com.Chandan.User.Managment.service.impl;

import com.Chandan.User.Managment.model.User;
import com.Chandan.User.Managment.repository.UserRepository;
import com.Chandan.User.Managment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    @Override
    public List<User> getAllUser() {
        List<User> findAll = userRepository.findAll();
        return findAll;
    }

//    @Override
//    public User updateUser(User user) {
//        User updateUser = userRepository.save(user);
//        return updateUser;
//    }

    @Override
    public User updateUser(Integer id, User userDetails) {
        Optional<User> existingUser = getUserById(id);

        if (existingUser.isPresent()) {
            User user = existingUser.get();

            // Set fields to update here
            user.setName(userDetails.getName());
            user.setEmail(userDetails.getEmail());
            user.setAddress(userDetails.getAddress());
            user.setPassword(userDetails.getPassword());
            // Add more fields as needed

            // Save and return updated user
            return userRepository.save(user);
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }

//        @Override
//        public void deleteUser (Integer userId){
//            Optional<User> findByIdUser = userRepository.findById(userId);
//            if (findByIdUser.isPresent()) {
//                User user = findByIdUser.get();
//                userRepository.delete(user);
//            }
    }

    @Override
    public void deleteUser(Integer userId) {
        Optional<User> findByIdUser = userRepository.findById(userId);
        if (findByIdUser.isPresent()) {
            User user = findByIdUser.get();
            userRepository.delete(user);
        }
    }

    @Override
    public Optional<User> getUserById(Integer userId) {
//            Optional<User> findByIdUser = userRepository.findById(userId);
//            if (findByIdUser.isPresent()) {
//                return Optional.of(findByIdUser.get());
//            }
//            return null;
//        }
        return userRepository.findById(userId);
    }

}