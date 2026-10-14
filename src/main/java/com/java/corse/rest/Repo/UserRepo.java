package com.java.corse.rest.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.corse.rest.Models.User;

public interface UserRepo extends JpaRepository<User, Long> {

}
