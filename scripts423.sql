SELECT student.name, student.age, f.name
FROM student
         INNER JOIN faculty f on student.faculty_id = f.id;

SELECT student.name, student.age
FROM student
         INNER JOIN avatar a on student.id = a.student_id;