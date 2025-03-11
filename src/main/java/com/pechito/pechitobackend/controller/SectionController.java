package com.pechito.pechitobackend.controller;

import com.pechito.pechitobackend.model.Exercise;
import com.pechito.pechitobackend.model.Section;
import com.pechito.pechitobackend.service.ISectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SectionController {
    @Autowired
    private ISectionService sectionService;

    @GetMapping("/sections/all")
    public Iterable<Section> getAllSections() {
        return sectionService.getAllSections();
    }

    @GetMapping("/sections/get/{id}")
    public Section getSectionById(@PathVariable Long id) {
        return sectionService.getSectionById(id);
    }

    @PostMapping("/sections/add")
    public void addSection(@RequestBody Section section) {
        sectionService.saveSection(section);
    }

    @DeleteMapping("/sections/delete/{id}")
    public void deleteSection(@PathVariable Long id) {
        sectionService.deleteSection(id);
    }

    @PutMapping("/sections/edit/{id}")
    public void editSection(@PathVariable Long id,
                            @RequestParam(required = false) String name,
                            @RequestParam(required = false) String description,
                            @RequestParam(required = false) int sets) {
        sectionService.editSection(id, name, description, sets);
    }

    @PutMapping("/sections/edit")
    public void editSection(@RequestBody Section section) {
        sectionService.editSection(section);
    }

    @PutMapping("/sections/addExerciseId/{sectionId}")
    public void addExerciseToSection(@PathVariable Long sectionId, @RequestParam Long exerciseId) {
        sectionService.addExercise(sectionId, exerciseId);
    }

    @PutMapping("/sections/addExercise/{sectionId}")
    public void addExerciseToSection(@PathVariable Long sectionId, @RequestBody Exercise exercise) {
        sectionService.addExercise(sectionId, exercise);
    }

}
