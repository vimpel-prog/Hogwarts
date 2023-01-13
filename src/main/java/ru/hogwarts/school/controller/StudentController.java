package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.List;

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
    public Faculty getStudentFaculty(@PathVariable Long id) {
        return studentService.findStudent(id).getFaculty();
    }

    @GetMapping("/count")
    public Integer getAllCount() {
        return studentService.getAllCount();
    }

    @GetMapping("/age/average")
    public Integer getAverageAge() {
        return studentService.getAverageAge();
    }

    @GetMapping("/lastFive")
    public List<Student> getLastFive() {
        return studentService.getLastFive();
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping
    public Student editStudent(@RequestParam Long id, @RequestBody Student student) {
        return studentService.editStudent(id, student);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteStudent(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.deleteStudent(id));
    }

    @GetMapping("/namesStartWithA")
    public ResponseEntity<Collection<String>> findStudentsNameStartWithA() {
        return ResponseEntity.ok(studentService.getAll().stream()
                .map(i -> i.getName())
                .filter(i -> i.startsWith("A"))
                .sorted()
                .map(String::toUpperCase)
                .toList());
    }

    @GetMapping("/averageAge")
    public Double getAverageAgeByStream() {
        return studentService.getAll().stream()
                .map(i -> i.getAge())
                .mapToInt(Integer::intValue)
                .average()
                .getAsDouble();
    }

}
