package com.pechito.pechitobackend.service;

import com.pechito.pechitobackend.model.Exercise;
import com.pechito.pechitobackend.model.Section;

public interface ISectionService {
    public Section saveSection(Section section);
    public Iterable<Section> getAllSections();
    public Section getSectionById(Long id);
    public Section getSectionByName(String name);
    public void deleteSection(Long id);
    public void editSection(Long id, String name, String description, int sets);
    public void editSection(Section section);
    public void addExercise(Long sectionId, Long exerciseId);
    public void addExercise(Long sectionId, Exercise exercise);
}
