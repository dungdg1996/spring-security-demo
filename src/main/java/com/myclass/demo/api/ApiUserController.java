package com.myclass.demo.api;


import com.myclass.demo.entity.User;
import com.myclass.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class ApiUserController {
    final private UserService userService;

    public ApiUserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("")
    public ResponseEntity<?> all(){
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> one(@PathVariable Integer id){
        try {
            User user = userService.findById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("Khong tim thay user", HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("")
    public ResponseEntity<?> add(@RequestBody User user){
        try {
            userService.save(user);
            return new ResponseEntity<>("Them moi user thanh cong", HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>("Them moi user that bai", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody User user){
        try {
            userService.save(user);
            return new ResponseEntity<>("Cap nhat thanh cong", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("Cap nhat that bai", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id){
        try {
            userService.delete(id);
            return new ResponseEntity<>("Xoa user thanh cong", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("Xoa user that bai", HttpStatus.BAD_REQUEST);
        }
    }
}
