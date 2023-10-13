package app.raiko.university.student.exception;

public class StudentNationalAlreadyExistsException extends RuntimeException {

  public StudentNationalAlreadyExistsException() {
    super("دانشجویی با این شماره ملی قبلا ثبت شده است");
  }
}
