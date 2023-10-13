package app.raiko.university.student.service;

import static org.mockito.BDDMockito.given;

import app.raiko.university.student.dto.StudentCreationsRequest;
import app.raiko.university.student.dto.StudentInformationResponse;
import app.raiko.university.student.entity.Student;
import app.raiko.university.student.mapper.StudentMapper;
import app.raiko.university.student.repository.StudentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class StudentCreatorServiceTest {

  @Mock private StudentRepository studentRepository;
  @Mock private StudentMapper studentMapper;
  private StudentCreatorServiceContract studentCreatorService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    studentCreatorService = new StudentCreatorService(studentRepository, studentMapper);
  }

  @Test
  void itShouldCreate() {
    String firsName = "firsName";
    String lastName = "lastName";
    String nationalCode = "3040483781";
    String address = "address";
    String number = "94249801";

    var studentCreationsRequest =
        new StudentCreationsRequest(firsName, lastName, address, number, nationalCode);

    var studentMapperMapToStudentResult =
        Student.builder()
            .firstName(firsName)
            .lastName(lastName)
            .nationalCode(nationalCode)
            .number(number)
            .address(address)
            .build();
    given(studentMapper.mapToStudent(studentCreationsRequest))
        .willReturn(studentMapperMapToStudentResult);

    var studentRepositorySaveResult =
        Student.builder()
            .id(1L)
            .firstName(firsName)
            .lastName(lastName)
            .nationalCode(nationalCode)
            .number(number)
            .address(address)
            .build();
    given(studentRepository.save(studentMapperMapToStudentResult))
        .willReturn(studentRepositorySaveResult);

    var expect =
        new StudentInformationResponse(1L, firsName, lastName, nationalCode, number, address);
    given(studentMapper.mapToStudentInformationResponse(studentRepositorySaveResult))
        .willReturn(expect);

    var actual = studentCreatorService.create(studentCreationsRequest);

    Assertions.assertThat(actual).isEqualTo(expect);
  }
}
