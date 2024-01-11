package course_2.course_25;

import org.springframework.web.bind.annotation.ResponseStatus;

//выбрасывается, когда нового сотрудника пытаются добавить в коллекцию, а в коллекции уже есть такой сотрудник.
@ResponseStatus
public class EmployeeAlreadyAddedException extends RuntimeException {
    public EmployeeAlreadyAddedException() {
    }

    public EmployeeAlreadyAddedException(String message) {
        super(message);
    }

    public EmployeeAlreadyAddedException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmployeeAlreadyAddedException(Throwable cause) {
        super(cause);
    }

    public EmployeeAlreadyAddedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
