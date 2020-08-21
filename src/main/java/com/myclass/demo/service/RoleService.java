package com.myclass.demo.service;

import com.myclass.demo.entity.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll();
    Role findById(int id);
    void save(Role role);
    void delete(int id);

    List<Role> searchByName(String name);
}
