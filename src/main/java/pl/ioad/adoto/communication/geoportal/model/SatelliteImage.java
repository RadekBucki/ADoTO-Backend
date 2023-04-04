package pl.ioad.adoto.communication.geoportal.model;

import lombok.Builder;

@Builder
public record SatelliteImage(
        String base64
) {
}
