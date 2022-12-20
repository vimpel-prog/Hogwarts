package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.ObjectNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(long id) {
        return facultyRepository.findById(id).orElseThrow();
    }

    public Faculty editFaculty(Long id, Faculty faculty) {
        Faculty dbFaculty = facultyRepository.findById(id).orElseThrow(ObjectNotFoundException::new);
        dbFaculty.setColor(faculty.getColor());
        dbFaculty.setName(faculty.getName());
        return facultyRepository.save(dbFaculty);
    }

    public Faculty deleteFaculty(long id) {
        Faculty dbFaculty = facultyRepository.findById(id).orElseThrow(ObjectNotFoundException::new);
        facultyRepository.delete(dbFaculty);
        return dbFaculty;
    }

    public Collection<Faculty> getAll() {
        return facultyRepository.findAll();
    }

    public Collection<Faculty> getFacultiesInColor(String color) {
        return facultyRepository.findAllByColor(color);
    }

    public Faculty getFacultyByColor(String color) {
        return facultyRepository.findByColorIgnoreCase(color);
    }

    public Faculty getFacultyByName(String name) {
        return facultyRepository.findByNameIgnoreCase(name);
    }
}
