package EcommerceApp.Spring.boot.Project.exception;

public class ProductException extends MiddlendException{
    public ProductException() {
    }

    public ProductException(String message) {
        super(message);
    }

    public ProductException(String message, Throwable cause) {
        super(message, cause);
    }
}
