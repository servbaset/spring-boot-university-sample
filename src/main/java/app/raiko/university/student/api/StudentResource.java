package app.raiko.university.student.api;

import app.raiko.university.student.dto.StudentCreationsRequest;
import app.raiko.university.student.dto.StudentInformationResponse;
import app.raiko.university.student.service.StudentCreatorServiceContract;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Validated
public class StudentResource {
  private final StudentCreatorServiceContract studentCreatorService;

  @PostMapping("/students")
  public StudentInformationResponse create(
      @RequestBody @Valid StudentCreationsRequest studentCreationsRequest) {
    return studentCreatorService.create(studentCreationsRequest);
  }
}
