package com.myclass.demo.api;

import com.myclass.demo.dto.LoginDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("api/auth")
public class ApiAuthController {



    final AuthenticationManager authenticationManager;

    public ApiAuthController(@Autowired AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @GetMapping
    public Object login() {
        return new ResponseEntity<>("token", HttpStatus.OK);
    }

    @PostMapping
    public Object login(@RequestBody LoginDto dto) {
        System.out.println(dto);
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());
            // login method
            Authentication authenticate = authenticationManager.authenticate(authenticationToken);
            // save authenticate to context
            SecurityContextHolder.getContext().setAuthentication(authenticate);

            String token = generateToken(authenticate);
            return new ResponseEntity<>(token, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Sai thong tin dang nhap", HttpStatus.BAD_REQUEST);
        }

    }

    private String generateToken(Authentication authenticate) {

        final long JWT_EXPIRATION = 24 * 60 * 60 * 1000L;
        final String JWT_SECRET = "cybersoft";

        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + JWT_EXPIRATION);

        UserDetails userDetails = (UserDetails) authenticate.getPrincipal();

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }
}
