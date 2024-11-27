package utils;

public class FrameworkException extends RuntimeException{

    public FrameworkException() {
        super();
    }

    // Constructor that accepts a message
    public FrameworkException(String message) {
        super(message);
    }

    public FrameworkException(String message,int lineNumber) {
        super(message+" at line Number "+lineNumber);
    }
}
