package app.raiko.university.student.repository;

import static org.assertj.core.api.Assertions.assertThat;

import app.raiko.university.student.entity.Student;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class StudentRepositoryTest {
  @Autowired private StudentRepository studentRepository;

  @Test
  void itShouldSave() {
    String number = "94249801";
    String firstName = "jahangir";
    String lastName = "shokrollahi";
    String nationalCode = "3040483781";
    String address = "my address";

    var savableStudent =
        Student.builder()
            .number(number)
            .firstName(firstName)
            .lastName(lastName)
            .nationalCode(nationalCode)
            .address(address)
            .build();

    var savedStudent = studentRepository.save(savableStudent);

    var expect = studentRepository.findById(savedStudent.getId());

    assertThat(expect)
        .hasValueSatisfying(
            student -> {
              assertThat(student.getId()).isNotNull();
              assertThat(student.getFirstName()).isEqualTo(firstName);
              assertThat(student.getLastName()).isEqualTo(lastName);
              assertThat(student.getNationalCode()).isEqualTo(nationalCode);
              assertThat(student.getNumber()).isEqualTo(number);
              assertThat(student.getAddress()).isEqualTo(address);
            });
  }

  @Test
  void itShouldExistsByNationalCode() {
    String number = "94249801";
    String firstName = "jahangir";
    String lastName = "shokrollahi";
    String nationalCode = "3040483781";
    String address = "my address";

    var savableStudent =
        Student.builder()
            .number(number)
            .firstName(firstName)
            .lastName(lastName)
            .nationalCode(nationalCode)
            .address(address)
            .build();

    studentRepository.save(savableStudent);

    var actual = studentRepository.existsByNationalCode(nationalCode);
    Assertions.assertThat(actual).isTrue();
  }

  @Test
  void itShouldExistsByNumber() {
    String number = "94249801";
    String firstName = "jahangir";
    String lastName = "shokrollahi";
    String nationalCode = "3040483781";
    String address = "my address";

    var savableStudent =
        Student.builder()
            .number(number)
            .firstName(firstName)
            .lastName(lastName)
            .nationalCode(nationalCode)
            .address(address)
            .build();

    studentRepository.save(savableStudent);

    var actual = studentRepository.existsByNumber(number);
    Assertions.assertThat(actual).isTrue();
  }

  @AfterEach
  void tearDown() {
    studentRepository.deleteAll();
  }
}
