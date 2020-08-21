package com.myclass.demo.api;


import com.myclass.demo.entity.Role;
import com.myclass.demo.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class ApiRoleController {
    final private RoleService roleService;

    public ApiRoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("")
    public ResponseEntity<?> all(){
        List<Role> roles = roleService.findAll();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> one(@PathVariable Integer id){
        try {
            Role role = roleService.findById(id);
            return new ResponseEntity<>(role, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("Khong tim thay user", HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("")
    public ResponseEntity<?> add(@RequestBody Role role){
        try {
            roleService.save(role);
            return new ResponseEntity<>("Them moi quyen thanh cong", HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>("Them moi quyen that bai", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody Role role){
        try {
            roleService.save(role);
            return new ResponseEntity<>("Cap nhat thanh cong", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("Cap nhat that bai", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id){
        try {
            roleService.delete(id);
            return new ResponseEntity<>("Xoa quyen thanh cong", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("Xoa quyen that bai", HttpStatus.BAD_REQUEST);
        }
    }
}
