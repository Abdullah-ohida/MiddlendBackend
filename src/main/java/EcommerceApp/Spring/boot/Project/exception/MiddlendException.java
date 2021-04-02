package EcommerceApp.Spring.boot.Project.exception;

public class MiddlendException extends RuntimeException{
    public MiddlendException() {
    }

    public MiddlendException(String message) {
        super(message);
    }

    public MiddlendException(String message, Throwable cause) {
        super(message, cause);
    }

    public MiddlendException(Throwable cause) {
        super(cause);
    }
}
