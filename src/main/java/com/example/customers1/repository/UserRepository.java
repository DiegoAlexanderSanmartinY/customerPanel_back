package com.example.customers1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.customers1.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
    @Query("SELECT u FROM User u WHERE email LIKE %:email% or address LIKE %:address%")
    List<User> findByEmailOrAddress(@Param("email") String email, @Param("address") String address);

    @Query("SELECT u FROM User u WHERE email = :email AND password = :password")
    // List<User> findByEmailAndPassword(@Param("email") String email,
    //                                  @Param("password") String password);
    User findByEmailAndPassword(@Param("email") String email, 
                                @Param("password") String password);

}
