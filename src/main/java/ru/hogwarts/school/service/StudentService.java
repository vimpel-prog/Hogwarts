package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.ObjectNotFoundException;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    private static final Logger logger= LoggerFactory.getLogger(StudentService.class);
    public Student createStudent(Student student) {
        logger.info("Was invoked method createStudent");
        return studentRepository.save(student);
    }

    public Student findStudent(long id) {
        logger.info("Was invoked method findStudent");
        return studentRepository.findById(id).orElseThrow();
    }

    public Collection<Student> getStudentsInAgeBetween(int min, int max) {
        logger.info("Was invoked method getStudentsInAgeBetween");
        return studentRepository.findByAgeBetween(min, max);
    }


    public Student editStudent(Long id, Student student) {
        logger.info("Was invoked method editStudent");
        Student dbStudent =
                this.studentRepository.findById(id)
                        .orElseThrow(ObjectNotFoundException::new);
        dbStudent.setName(student.getName());
        dbStudent.setAge(student.getAge());

        return studentRepository.save(dbStudent);
    }

    public Student deleteStudent(long id) {
        logger.info("Was invoked method deleteStudent");
        Student dbStudent = studentRepository.findById(id).orElseThrow(ObjectNotFoundException::new);
        studentRepository.delete(dbStudent);
        return dbStudent;
    }

    public Collection<Student> getAll() {
        logger.info("Was invoked method getAll");
        return studentRepository.findAll();
    }

    public Collection<Student> getStudentsInAge(int age) {
        logger.info("Was invoked method getStudentsInAge");
        return studentRepository.findAllByAge(age);
    }

    public Integer getAllCount(){
        logger.info("Was invoked method getAllCount");
        return studentRepository.getCount();
    }

    public Integer getAverageAge(){
        logger.info("Was invoked method getAverageAge");
        return studentRepository.getAverageAge();
    }

    public List<Student> getLastFive(){
        logger.info("Was invoked method getLastFive");
        return studentRepository.getLastFive();
    }
}
