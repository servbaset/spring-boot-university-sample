package app.raiko.university.student.exception;

public class StudentNumberAlreadyExistsException extends RuntimeException {

  public StudentNumberAlreadyExistsException() {
    super("دانشجویی با این شماره قبلا ثبت شده است");
  }
}
