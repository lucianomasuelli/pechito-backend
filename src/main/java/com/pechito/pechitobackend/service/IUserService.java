package com.pechito.pechitobackend.service;

import com.pechito.pechitobackend.model.Exercise;
import com.pechito.pechitobackend.model.Section;
import com.pechito.pechitobackend.model.User;
import com.pechito.pechitobackend.model.WorkoutSession;

public interface IUserService {
    public void saveUser(User user);
    public Iterable<User> getAllUsers();
    public User getUserById(Long id);
    public User getUserByEmail(String email);
    public void deleteUser(Long id);
    public void editUser(Long id, String username, String password, String email);
    public void editUser(User user);
    public void addWorkoutSessionToUser(Long userId, WorkoutSession workoutSession);
    public void addWorkoutSessionToUser(Long userId, Long workoutSessionId);
    public void addSectionToUser(Long userId, Section section);
    public void addSectionToUser(Long userId, Long sectionId);
    public void addExerciseToUser(Long userId, Exercise exercise);
    public void addExerciseToUser(Long userId, Long exerciseId);
}
