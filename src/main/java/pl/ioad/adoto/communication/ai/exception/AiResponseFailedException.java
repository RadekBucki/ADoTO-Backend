package pl.ioad.adoto.communication.ai.exception;

public class AiResponseFailedException extends RuntimeException {
    public AiResponseFailedException(String message) {
        super(message);
    }
}
