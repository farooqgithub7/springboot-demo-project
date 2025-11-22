package org.example.springbootdemo.controller;

import org.example.springbootdemo.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        // Simple demo: in real app, validate against DB
        if ("admin".equals(username) && "admin123".equals(password)) {
            String token = jwtUtil.generateToken(username);
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(401).body("Invalid Credentials");
    }
}
