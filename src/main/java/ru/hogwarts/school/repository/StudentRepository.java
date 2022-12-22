package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Collection<Student> findAllByAge(int age);

    Collection<Student> findByAgeBetween(int min, int max);

    @Query(value = "SELECT COUNT(*) FROM Student ",nativeQuery = true)
    Integer getCount();

    @Query(value = "SELECT AVG(age) FROM Student ",nativeQuery = true)
    Integer getAverageAge();

    @Query(value = "SELECT * FROM Student ORDER BY id DESC LIMIT 5",nativeQuery = true)
    List<Student> getLastFive();
}
