package app.raiko.university.student.service;

import app.raiko.university.student.dto.StudentCreationsRequest;
import app.raiko.university.student.dto.StudentInformationResponse;

public interface StudentCreatorServiceContract {

  StudentInformationResponse create(StudentCreationsRequest studentCreationsRequest);
}
