package course_2.course_25;

import org.springframework.web.bind.annotation.ResponseStatus;
//Написать собственное непроверяемое исключение EmployeeStorageIsFullException,
//        которое выбрасывается, если превышен лимит количества сотрудников в фирме.
@ResponseStatus
public class EmployeeStorageIsFullException extends RuntimeException{
    public EmployeeStorageIsFullException() {
    }

    public EmployeeStorageIsFullException(String message) {
        super(message);
    }

    public EmployeeStorageIsFullException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmployeeStorageIsFullException(Throwable cause) {
        super(cause);
    }

    public EmployeeStorageIsFullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
