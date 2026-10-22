package exceptions;

public class InvalidIngredientException extends RuntimeException {

    public InvalidIngredientException(String msg) {
        super(msg);
    }
}
