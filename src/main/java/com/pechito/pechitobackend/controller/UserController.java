package com.pechito.pechitobackend.controller;

import com.pechito.pechitobackend.model.Exercise;
import com.pechito.pechitobackend.model.Section;
import com.pechito.pechitobackend.model.User;
import com.pechito.pechitobackend.model.WorkoutSession;
import com.pechito.pechitobackend.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/users/all")
    public Iterable<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/get/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/users/getByEmail/{email}")
    public User getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    @PostMapping("/users/add")
    public void addUser(@RequestBody User user) {
        userService.saveUser(user);
    }

    @DeleteMapping("/users/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @PutMapping("/users/edit/{id}")
    public void editUser(@PathVariable Long id,
                         @RequestParam(required = false) String username,
                         @RequestParam(required = false) String password,
                         @RequestParam(required = false) String email) {

        userService.editUser(id, username, password, email);
    }

    @PutMapping("/users/edit")
    public void editUser(@RequestBody User user) {
        userService.editUser(user);
    }

    @PutMapping("/users/addExerciseId/{userId}")
    public void addExerciseToUser(@PathVariable Long userId, @RequestParam Long exerciseId) {
        userService.addExerciseToUser(userId, exerciseId);
    }

    @PutMapping("/users/addExercise/{userId}")
    public void addExerciseToUser(@PathVariable Long userId, @RequestBody Exercise exercise) {
        userService.addExerciseToUser(userId, exercise);
    }

    @PutMapping("/users/addWorkoutSessionId/{userId}")
    public void addWorkoutSessionToUser(@PathVariable Long userId, @RequestParam Long workoutSessionId) {
        userService.addWorkoutSessionToUser(userId, workoutSessionId);
    }

    @PutMapping("/users/addWorkoutSession/{userId}")
    public void addWorkoutSessionToUser(@PathVariable Long userId, @RequestBody WorkoutSession workoutSession) {
        userService.addWorkoutSessionToUser(userId, workoutSession);
    }

}
