package exceptions;

public class InvalidNumericValueException extends RuntimeException {

    public InvalidNumericValueException(String msg) {
        super(msg);
    }
}