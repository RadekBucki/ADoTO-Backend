package pl.ioad.adoto.backend.exception.handler.response;

import java.util.Map;

public record ErrorResponse(
        String message,
        int statusCode,
        Map<String, String> errors) {
}
