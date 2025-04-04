package com.pechito.pechitobackend.service;

import com.pechito.pechitobackend.exceptions.SectionAlreadyExistsException;
import com.pechito.pechitobackend.exceptions.SectionNotFoundException;
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
        try {
            this.getSectionByName(section.getName());
            throw new SectionAlreadyExistsException(section.getName());
        } catch (SectionNotFoundException e){
            //If the section does not exist, it is created
            return sectionRepository.save(section);
        }
    }

    @Override
    public Iterable<Section> getAllSections() {
        return sectionRepository.findAll();
    }

    @Override
    public Section getSectionById(Long id) {
        return sectionRepository.findById(id).orElseThrow(() -> new SectionNotFoundException(id));
    }

    @Override
    public Section getSectionByName(String name) {
        Section section = sectionRepository.findSectionByName(name);
        if(section == null){
            throw new SectionNotFoundException(name);
        } else {
            return section;
        }
    }

    @Override
    public void deleteSection(Long id) {
        this.getSectionById(id); //Checks if the section exists
        sectionRepository.deleteById(id);
    }

    @Override
    public void editSection(Long id, String name, String description, int sets) {
        this.getSectionById(id); //Checks if the section exists

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
        this.getSectionById(section.getId()); //Checks if the section exists
        this.saveSection(section);
    }

    @Override
    public void addExercise(Long sectionId, Long exerciseId) {
        Section section = this.getSectionById(sectionId);
        Exercise exercise = exerciseService.getExerciseById(exerciseId);
        List<Exercise> exercises = section.getExercises();
        exercises.add(exercise);
        section.setExercises(exercises);
        this.saveSection(section);
    }

    @Override
    public void addExercise(Long sectionId, Exercise exercise) {
        Section section = this.getSectionById(sectionId);
        //Creates the exercise
        Exercise createdExercise = exerciseService.saveExercise(exercise);
        List<Exercise> exercises = section.getExercises();
        exercises.add(createdExercise);
        section.setExercises(exercises);
        this.saveSection(section);
    }
}
