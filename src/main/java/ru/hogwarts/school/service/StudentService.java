package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.ObjectNotFoundException;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student findStudent(long id) {
        return studentRepository.findById(id).orElseThrow();
    }

    public Collection<Student> getStudentsInAgeBetween(int min, int max) {
        return studentRepository.findByAgeBetween(min, max);
    }


    public Student editStudent(Long id, Student student) {
        Student dbStudent =
                this.studentRepository.findById(id)
                        .orElseThrow(ObjectNotFoundException::new);
        dbStudent.setName(student.getName());
        dbStudent.setAge(student.getAge());

        return studentRepository.save(dbStudent);
    }

    public Student deleteStudent(long id) {
        Student dbStudent = studentRepository.findById(id).orElseThrow(ObjectNotFoundException::new);
        studentRepository.delete(dbStudent);
        return dbStudent;
    }

    public Collection<Student> getAll() {
        return studentRepository.findAll();
    }

    public Collection<Student> getStudentsInAge(int age) {
        return studentRepository.findAllByAge(age);
    }
}
