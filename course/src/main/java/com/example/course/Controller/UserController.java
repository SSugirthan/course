package com.example.course.Controller;

import com.example.course.Exception.ResourceNotFoundExceptions;
import com.example.course.Model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    List<User> users = new ArrayList<>();

    // Constructor with static data
    public UserController() {

        users.add(new User("U101", "Rahul", "pass1"));
        users.add(new User("U102", "Kumar", "pass2"));
        users.add(new User("U103", "Arun", "pass3"));
        users.add(new User("U104", "Priya", "pass4"));
        users.add(new User("U105", "John", "pass5"));
    }

    // 1. Get all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // 2. Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {

        for (User user : users) {

            if (user.getUserId().equalsIgnoreCase(id)) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
        }

        throw new ResourceNotFoundExceptions("User not found");
    }

    // 3. Get user using RequestParam
    @GetMapping("/search")
    public ResponseEntity<User> searchUser(@RequestParam String userId) {

        // Special character check
        if (!userId.matches("[a-zA-Z0-9]+")) {

            throw new IllegalArgumentException("User ID contains special character");
        }

        for (User user : users) {

            if (user.getUserId().equalsIgnoreCase(userId)) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
        }

        throw new ResourceNotFoundExceptions("User not found");
    }

    // 4. Add new user
    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody User user) {

        users.add(user);

        return new ResponseEntity<>("User added successfully", HttpStatus.CREATED);
    }

    // 5. Update password
    @PutMapping("/{id}")
    public ResponseEntity<String> updatePassword(
            @PathVariable String id,
            @RequestBody User updatedUser) {

        for (User user : users) {

            if (user.getUserId().equalsIgnoreCase(id)) {

                user.setPassword(updatedUser.getPassword());

                return new ResponseEntity<>("Password updated", HttpStatus.OK);
            }
        }

        throw new ResourceNotFoundExceptions("User not found");
    }

    // 6. Delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {

        for (User user : users) {

            if (user.getUserId().equalsIgnoreCase(id)) {

                users.remove(user);

                return new ResponseEntity<>("User deleted", HttpStatus.OK);
            }
        }

        throw new ResourceNotFoundExceptions("User not found");
    }
}
