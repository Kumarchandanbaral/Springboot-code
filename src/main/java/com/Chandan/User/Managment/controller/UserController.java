package com.Chandan.User.Managment.controller;

import com.Chandan.User.Managment.model.User;
import com.Chandan.User.Managment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    // http://localhost:9095/save

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveUser(@RequestBody User user){
        User savedUser = userService.saveUser(user);
        if (ObjectUtils.isEmpty(savedUser)){
            return new ResponseEntity<>("User not saved",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("User Saved Successfully", HttpStatus.CREATED);
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<?> getAllUser()
    {
        List<User> allUsers= userService.getAllUser();
        return new ResponseEntity<>(allUsers,HttpStatus.OK);
    }

//    @PutMapping("/updateUser/{id}")
//    public ResponseEntity<?> updateUser(@PathVariable Integer id,@RequestBody User user){
//        User savedUser = userService.updateUser(user);
//        if (ObjectUtils.isEmpty(savedUser)){
//            return new ResponseEntity<>("User not update",HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        return new ResponseEntity<>("User Update Successfully", HttpStatus.CREATED);
//    }

//    @PutMapping("/updateUser/{id}")
//    public ResponseEntity<?> updateUser(@PathVariable Integer id, @RequestBody User userDetails) {
//        Optional<User> existingUser = userService.getUserById(id);
//
//        if (existingUser.isEmpty()) {
//            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
//        }
//
//        return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
//    }
@PutMapping("/updateUser/{id}")
public ResponseEntity<?> updateUser(@PathVariable Integer id, @RequestBody User userDetails) {
    try {
        User updatedUser = userService.updateUser(id, userDetails);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    } catch (RuntimeException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id)
    {
        userService.deleteUser(id);
        return new ResponseEntity<>("User delete Successfully",HttpStatus.OK);
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Integer id){
        Optional<User> userById = userService.getUserById(id);
        if (ObjectUtils.isEmpty(userById)){

            return new ResponseEntity<>("User not found",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("User found with "+userById,HttpStatus.FOUND);
    }
}
