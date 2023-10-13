package app.raiko.university.student.mapper;

import app.raiko.university.student.dto.StudentCreationsRequest;
import app.raiko.university.student.dto.StudentInformationResponse;
import app.raiko.university.student.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentMapper {

  @Mapping(target = "id", ignore = true)
  Student mapToStudent(StudentCreationsRequest studentCreationsRequest);

  StudentInformationResponse mapToStudentInformationResponse(Student student);
}
