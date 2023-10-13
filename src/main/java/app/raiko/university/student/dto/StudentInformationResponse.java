package app.raiko.university.student.dto;

public record StudentInformationResponse(
    long id,
    String firstName,
    String lastName,
    String nationalCode,
    String number,
    String address) {}
