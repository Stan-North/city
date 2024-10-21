package employee;

public class EmployeeInvalidRateException extends RuntimeException {
    public EmployeeInvalidRateException(String message) {
        super(message);
    }
}
