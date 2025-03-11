package com.pechito.pechitobackend.controller;

import com.pechito.pechitobackend.model.Section;
import com.pechito.pechitobackend.model.WorkoutSession;
import com.pechito.pechitobackend.model.WorkoutType;
import com.pechito.pechitobackend.service.IWorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class WorkoutController {
    @Autowired
    private IWorkoutService workoutService;

    @GetMapping("/workouts/all")
    public Iterable<WorkoutSession> getAllWorkouts() {
        return workoutService.getAllWorkouts();
    }

    @GetMapping("/workouts/get/{id}")
    public WorkoutSession getWorkoutById(@PathVariable Long id) {
        return workoutService.getWorkoutById(id);
    }

    @PostMapping("/workouts/add")
    public void addWorkout(@RequestBody WorkoutSession workout) {
        workoutService.saveWorkout(workout);
    }

    @DeleteMapping("/workouts/delete/{id}")
    public void deleteWorkout(@PathVariable Long id) {
        workoutService.deleteWorkout(id);
    }

    @PutMapping("/workouts/edit/{id}")
    public void editWorkout(@PathVariable Long id,
                            @RequestParam(required = false) String name,
                            @RequestParam(required = false) String description,
                            @RequestParam(required = false) WorkoutType type) {
        workoutService.editWorkout(id, name, description, type);
    }

    @PutMapping("/workouts/edit")
    public void editWorkout(@RequestBody WorkoutSession workout) {
        workoutService.editWorkout(workout);
    }

    @PutMapping("/workouts/addSectionId/{workoutId}")
    public void addSectionToWorkout(@PathVariable Long workoutId, @RequestParam Long sectionId) {
        workoutService.addSection(workoutId, sectionId);
    }

    @PutMapping("/workouts/addSection/{workoutId}")
    public void addSectionToWorkout(@PathVariable Long workoutId, @RequestBody Section section) {
        workoutService.addSection(workoutId, section);
    }
}
