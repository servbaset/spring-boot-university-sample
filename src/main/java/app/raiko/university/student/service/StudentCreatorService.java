package app.raiko.university.student.service;

import app.raiko.university.student.dto.StudentCreationsRequest;
import app.raiko.university.student.dto.StudentInformationResponse;
import app.raiko.university.student.exception.StudentNationalAlreadyExistsException;
import app.raiko.university.student.exception.StudentNumberAlreadyExistsException;
import app.raiko.university.student.mapper.StudentMapper;
import app.raiko.university.student.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class StudentCreatorService implements StudentCreatorServiceContract {
  private final StudentRepository studentRepository;
  private final StudentMapper studentMapper;

  @Transactional
  @Override
  public StudentInformationResponse create(StudentCreationsRequest studentCreationsRequest) {
    doDuplicateNationalCode(studentCreationsRequest.nationalCode());
    doDuplicateNumber(studentCreationsRequest.number());
    var savableStudent = studentMapper.mapToStudent(studentCreationsRequest);
    var savedStudent = studentRepository.save(savableStudent);
    return studentMapper.mapToStudentInformationResponse(savedStudent);
  }

  private void doDuplicateNumber(String number) {
    if (studentRepository.existsByNumber(number))
      throw new StudentNumberAlreadyExistsException();
  }

  private void doDuplicateNationalCode(String nationalCode) {
    if (studentRepository.existsByNationalCode(nationalCode))
      throw new StudentNationalAlreadyExistsException();
  }
}
