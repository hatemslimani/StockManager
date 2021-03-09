package com.example.geststock.Controller;

import com.example.geststock.Model.UserModel;
import com.example.geststock.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<List<UserModel>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> getById(@PathVariable int id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<UserModel> add(@RequestBody UserModel user) {
        return ResponseEntity.ok(userService.add(user));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserModel> update(@PathVariable int id, @RequestBody UserModel user) {
        return ResponseEntity.ok(userService.update(id, user));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserModel> findByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.findByUsername(username));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserModel> findByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.findByEmail(email));
    }
} 