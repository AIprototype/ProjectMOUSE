package custom_exceptions;

public class PlatformDimensionException extends Exception{

    public PlatformDimensionException() {}

    public PlatformDimensionException(String message) {
        super(message);
    }
}
