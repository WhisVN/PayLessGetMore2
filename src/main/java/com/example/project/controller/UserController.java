package com.example.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.payload.UserIdentityAvailability;
import com.example.project.payload.UserSummary;
import com.example.project.repository.UserRepository;
import com.example.project.security.CurrentUser;
import com.example.project.security.UserPrincipal;
import com.example.project.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserService userService;

    @GetMapping("/user/me")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getName());
        return userSummary;
    }

    @GetMapping("/user/checkUsernameAvailability")
    public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
        Boolean isAvailable = !userRepository.existsByUsername(username);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/user/checkEmailAvailability")
    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
        Boolean isAvailable = !userRepository.existsByEmail(email);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<?> getUserProfileByUsername(@PathVariable(value = "username") String username) {
        return userService.getInfoByUsername(username);
    }
    
    @GetMapping("/user/id/{id}")
    public ResponseEntity<?> getUserProfileById(@PathVariable(value = "id") Long id) {
        return userService.getInfoById(id);
    }
    
    @GetMapping("/user/all")
    public ResponseEntity<?> getAll() {
        return userService.getAll();
    }
}
