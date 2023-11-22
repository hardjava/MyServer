package airbnb.exception;

public class ExsistIdException extends RuntimeException {
    public ExsistIdException(String str) {
        super(str);
    }
}
