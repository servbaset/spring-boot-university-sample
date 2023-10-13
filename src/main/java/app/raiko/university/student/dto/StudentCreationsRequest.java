package app.raiko.university.student.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record StudentCreationsRequest(
    @NotBlank @Size(max = 82) String firstName,
    @NotBlank @Size(max = 82) String lastName,
    String address,
    @NotBlank @Size(max = 82) String number,
    @NotBlank @Size(max = 10, min = 8) String nationalCode) {}
