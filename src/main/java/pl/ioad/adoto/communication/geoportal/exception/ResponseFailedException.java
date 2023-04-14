package pl.ioad.adoto.communication.geoportal.exception;

public class ResponseFailedException extends RuntimeException {
    public ResponseFailedException(String message) {
        super(message);
    }
}
