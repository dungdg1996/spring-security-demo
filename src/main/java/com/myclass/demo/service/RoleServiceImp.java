package com.myclass.demo.service;

import com.myclass.demo.entity.Role;
import com.myclass.demo.repository.RoleRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImp implements RoleService {
    private final RoleRepo roleRepo;

    public RoleServiceImp(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    public List<Role> findAll() {
        return roleRepo.findAll();
    }

    @Override
    public Role findById(int id) {
        return roleRepo.findById(id).get();
    }

    @Override
    public void save(Role role) {
        roleRepo.save(role);
    }

    @Override
    public void delete(int id) {
        Role role = roleRepo.findById(id).get();
        roleRepo.delete(role);
    }

    @Override
    public List<Role> searchByName(String name) {
        return roleRepo.findByNameLike(name);
    }

}
