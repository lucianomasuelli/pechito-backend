package com.pechito.pechitobackend.service;

import com.pechito.pechitobackend.model.Exercise;
import com.pechito.pechitobackend.model.Section;
import com.pechito.pechitobackend.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionService implements ISectionService{
    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private ExerciseService exerciseService;

    @Override
    public Section saveSection(Section section) {
        return sectionRepository.save(section);
    }

    @Override
    public Iterable<Section> getAllSections() {
        return sectionRepository.findAll();
    }

    @Override
    public Section getSectionById(Long id) {
        return sectionRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteSection(Long id) {
        sectionRepository.deleteById(id);
    }

    @Override
    public void editSection(Long id, String name, String description, int sets) {
        Section section = this.getSectionById(id);
        if(name != null){
            section.setName(name);
        }
        if(description != null){
            section.setDescription(description);
        }
        if(sets != 0){
            section.setSets(sets);
        }
        this.saveSection(section);
    }

    @Override
    public void editSection(Section section) {
        this.saveSection(section);
    }

    @Override
    public void addExercise(Long sectionId, Long exerciseId) {

    }

    @Override
    public void addExercise(Long sectionId, Exercise exercise) {
        Section section = this.getSectionById(sectionId);
        //Creates de exercise
        Exercise createdExercise = exerciseService.saveExercise(exercise);
        List<Exercise> exercises = section.getExercises();
        exercises.add(createdExercise);
        section.setExercises(exercises);
        this.saveSection(section);
    }
}
