package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudent(@PathVariable long id) {
        Student student = studentService.findStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @GetMapping
    public ResponseEntity<Collection<Student>> findStudents(@RequestParam(required = false) Integer minAge,
                                                            @RequestParam(required = false) Integer maxAge) {
        if (minAge != null && maxAge != null) {
            return ResponseEntity.ok(studentService.getStudentsInAgeBetween(minAge, maxAge));
        }
        return ResponseEntity.ok(studentService.getAll());
    }

    @GetMapping("age/{age}")
    public Collection<Student> getAllStudentsInAge(@PathVariable int age) {
        return studentService.getStudentsInAge(age);
    }

    @GetMapping("/faculty/{id}")
    public Faculty getStudentFaculty(@PathVariable Long id){
        return studentService.findStudent(id).getFaculty();
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping
    public Student editStudent(@RequestParam Long id,@RequestBody Student student) {
        return studentService.editStudent(id, student);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteStudent(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.deleteStudent(id));
    }

}
