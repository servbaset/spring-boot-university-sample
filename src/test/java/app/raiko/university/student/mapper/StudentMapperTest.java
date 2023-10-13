package app.raiko.university.student.mapper;

import app.raiko.university.student.dto.StudentCreationsRequest;
import app.raiko.university.student.dto.StudentInformationResponse;
import app.raiko.university.student.entity.Student;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class StudentMapperTest {

  private StudentMapper underTest = Mappers.getMapper(StudentMapper.class);

  @Test
  void itShouldMapToStudent() {
    String firsName = "firsName";
    String lastName = "lastName";
    String nationalCode = "3040483781";
    String address = "address";
    String number = "94249801";

    var studentCreationsRequest =
        new StudentCreationsRequest(firsName, lastName, address, number, nationalCode);

    var actual = underTest.mapToStudent(studentCreationsRequest);

    Assertions.assertThat(actual)
        .usingRecursiveComparison()
        .isEqualTo(
            Student.builder()
                .firstName(firsName)
                .lastName(lastName)
                .nationalCode(nationalCode)
                .number(number)
                .address(address)
                .build());
  }

  @Test
  void itShouldMapToStudentInformationResponse() {
    String firsName = "firsName";
    String lastName = "lastName";
    String nationalCode = "3040483781";
    String address = "address";
    String number = "94249801";
    var student =
        Student.builder()
            .id(1L)
            .firstName(firsName)
            .lastName(lastName)
            .nationalCode(nationalCode)
            .number(number)
            .address(address)
            .build();
    var actual = underTest.mapToStudentInformationResponse(student);
    Assertions.assertThat(actual)
        .isEqualTo(
            new StudentInformationResponse(1L, firsName, lastName, nationalCode, number, address));
  }
}
