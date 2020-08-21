package com.myclass.demo.service;

import com.myclass.demo.entity.CustomUserDetails;
import com.myclass.demo.repository.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
    private final UserRepo userRepo;

    public UserDetailsServiceImp(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return new CustomUserDetails(userRepo.findByEmail(s)
                .orElseThrow(() -> new UsernameNotFoundException("Can't not find user by email " + s)));
    }
}
