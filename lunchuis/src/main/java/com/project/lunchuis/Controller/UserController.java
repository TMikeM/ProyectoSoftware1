package com.project.lunchuis.Controller;

import com.project.lunchuis.Model.Buy;
import com.project.lunchuis.Model.User;
import com.project.lunchuis.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers(); // Llamamos al servicio para obtener todos los usuarios
        return ResponseEntity.ok(users); // Devolvemos los usuarios con un status 200 OK
    }
    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestParam String code, @RequestParam String password) {
        Optional<User> user = userService.authenticate(code, password);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(401).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/{id}/purchases")
    public ResponseEntity<List<Buy>> getUserPurchases(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(user -> ResponseEntity.ok(user.getPurchases()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

