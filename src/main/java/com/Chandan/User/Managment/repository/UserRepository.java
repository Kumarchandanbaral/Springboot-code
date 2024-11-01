package com.Chandan.User.Managment.repository;

import com.Chandan.User.Managment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {


}
