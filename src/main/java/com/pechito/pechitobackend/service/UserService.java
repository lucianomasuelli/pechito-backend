package com.pechito.pechitobackend.service;

import com.pechito.pechitobackend.model.Exercise;
import com.pechito.pechitobackend.model.Section;
import com.pechito.pechitobackend.model.User;
import com.pechito.pechitobackend.model.WorkoutSession;
import com.pechito.pechitobackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IWorkoutService workoutService;

    @Autowired
    private IExerciseService exerciseService;


    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void editUser(Long id, String username, String password, String email) {
        User user = this.getUserById(id);
        if(username != null){
            user.setUsername(username);
        }
        if(password != null){
            user.setPassword(password);
        }
        if(email != null){
            user.setEmail(email);
        }
        this.saveUser(user);
    }

    @Override
    public void editUser(User user) {
        this.saveUser(user);
    }

    @Override
    public void addWorkoutSessionToUser(Long userId, WorkoutSession workoutSession) {
        User user = this.getUserById(userId);
        List<WorkoutSession> workouts = user.getWorkouts();
        workouts.add(workoutService.saveWorkout(workoutSession));
        user.setWorkouts(workouts);
        this.saveUser(user);
    }

    @Override
    public void addWorkoutSessionToUser(Long userId, Long workoutSessionId) {

    }

    @Override
    public void addSectionToUser(Long userId, Section section) {
        User user = this.getUserById(userId);
        List<Section> sections = user.getSections();
        sections.add(section);
        user.setSections(sections);
        this.saveUser(user);
    }

    @Override
    public void addSectionToUser(Long userId, Long sectionId) {

    }

    @Override
    public void addExerciseToUser(Long userId, Exercise exercise) {
        User user = this.getUserById(userId);
        //Creates de exercise
        Exercise createdExercise = exerciseService.saveExercise(exercise);
        List<Exercise> exercises = user.getExercises();
        exercises.add(createdExercise);
        user.setExercises(exercises);
        this.saveUser(user);
    }

    @Override
    public void addExerciseToUser(Long userId, Long exerciseId) {
        User user = this.getUserById(userId);
        Exercise exercise = exerciseService.getExerciseById(exerciseId);
        List<Exercise> exercises = user.getExercises();
        exercises.add(exercise);
        user.setExercises(exercises);
        this.saveUser(user);
    }


}
