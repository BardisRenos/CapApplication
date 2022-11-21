package com.example.capgemini.Application.dao;

import com.example.capgemini.Application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select p from User p join fetch p.account c where p.surname = surname")
    User findUserBySurName(@Param("surname") String surname);
}
