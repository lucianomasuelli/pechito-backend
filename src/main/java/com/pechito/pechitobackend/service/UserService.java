package com.pechito.pechitobackend.service;

import com.pechito.pechitobackend.exceptions.UserAlreadyExistsException;
import com.pechito.pechitobackend.exceptions.UserNotFoundException;
import com.pechito.pechitobackend.model.Exercise;
import com.pechito.pechitobackend.model.Section;
import com.pechito.pechitobackend.model.User;
import com.pechito.pechitobackend.model.WorkoutSession;
import com.pechito.pechitobackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IWorkoutService workoutService;

    @Autowired
    private  ISectionService sectionService;

    @Autowired
    private IExerciseService exerciseService;


    @Override
    public void saveUser(User user) {
        if (userRepository.findUserByUsername(user.getUsername()).isPresent() ||
                userRepository.findUserByEmail(user.getEmail()) != null) {
            System.out.println("User already exists");
            throw new UserAlreadyExistsException();
        }
    }

    @Override
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username).orElseThrow(() -> new UserNotFoundException());
    }

    @Override
    public User getUserByEmail(String email) {
        User user = userRepository.findUserByEmail(email);
        if(user == null){
            throw new UserNotFoundException(email);
        } else {
            return user;
        }
    }

    @Override
    public void deleteUser(Long id) {
        this.getUserById(id);  //Checks if the user exists
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
        this.getUserByEmail(user.getEmail());
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
        User user = this.getUserById(userId);
        WorkoutSession workoutSession = workoutService.getWorkoutById(workoutSessionId);
        List<WorkoutSession> workouts = user.getWorkouts();
        workouts.add(workoutSession);
        user.setWorkouts(workouts);
        this.saveUser(user);
    }

    @Override
    public void addSectionToUser(Long userId, Section section) {
        User user = this.getUserById(userId);
        List<Section> sections = user.getSections();
        sections.add(sectionService.saveSection(section));
        user.setSections(sections);
        this.saveUser(user);
    }

    @Override
    public void addSectionToUser(Long userId, Long sectionId) {
        User user = this.getUserById(userId);
        Section section = sectionService.getSectionById(sectionId);
        List<Section> sections = user.getSections();
        sections.add(section);
        user.setSections(sections);
        this.saveUser(user);
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
