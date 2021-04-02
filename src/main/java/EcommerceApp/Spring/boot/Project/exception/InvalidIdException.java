package EcommerceApp.Spring.boot.Project.exception;

public class InvalidIdException extends ProductException{
    public InvalidIdException(String message) {
        super(message);
    }
}
