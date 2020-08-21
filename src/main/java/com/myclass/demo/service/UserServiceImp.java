package com.myclass.demo.service;

import com.myclass.demo.entity.User;
import com.myclass.demo.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {
    private final UserRepo userRepo;


    public UserServiceImp(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public List<User> findAll(){
        return userRepo.findAll();
    }

    @Override
    public User findById(int id){
        return userRepo.findById(id).get();
    }

    @Override
    public void save(User user){
        userRepo.save(user);
    }

    @Override
    public void delete(int id){
        User user = userRepo.findById(id).get();
        userRepo.delete(user);
    }
}
