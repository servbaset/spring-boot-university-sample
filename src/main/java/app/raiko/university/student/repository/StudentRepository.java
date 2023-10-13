package app.raiko.university.student.repository;

import app.raiko.university.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

  boolean existsByNationalCode(String nationalCode);

  boolean existsByNumber(String number);
}
