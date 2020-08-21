package com.myclass.demo.repository;

import com.myclass.demo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {
    @Query("select r from Role r where lower(r.name) like lower(?1) or lower(r.description) like lower(?1)")
    List<Role> findByNameLike(String name);
}