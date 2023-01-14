package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;
import java.util.Comparator;

@RestController
@RequestMapping("faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("{id}")
    public Faculty getFaculty(@PathVariable Long id) {
        return facultyService.findFaculty(id);
    }

    @GetMapping("/students/{id}")
    public Collection<Student> getAllStudentsByFaculty(@PathVariable Long id) {
        return facultyService.findFaculty(id).getStudents();
    }

    @GetMapping()
    public ResponseEntity<?> getFacultyByColor(@RequestParam(required = false) String color,
                                                     @RequestParam(required = false) String name) {
        if(color!=null&&!color.isBlank()) {
            return ResponseEntity.ok(facultyService.getFacultyByColor(color));
        }
        if (name != null && !name.isBlank()) {
            return ResponseEntity.ok(facultyService.getFacultyByName(name));
        }
        return ResponseEntity.ok(facultyService.getAll());
    }

    @PostMapping
    public Faculty addFaculty(@RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }

    @PutMapping
    public Faculty editFaculty(@RequestParam Long id, @RequestBody Faculty faculty) {
        return facultyService.editFaculty(id, faculty);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Faculty> deleteFaculty(@PathVariable Long id) {
        return ResponseEntity.ok(facultyService.deleteFaculty(id));
    }

    @GetMapping("longestFacultyName")
    public String getLongestFacultyName() {
        return facultyService.getAll().stream()
                .map(Faculty::getName)
                .max(Comparator.comparing(String::length))
                .orElse("default");
    }
}
