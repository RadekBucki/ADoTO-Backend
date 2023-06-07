package pl.ioad.adoto.backend.geoportal.logic.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import pl.ioad.adoto.backend.coordinates.converter.CoordinatesConverter;

@Service
@RequiredArgsConstructor
public class CoordinatesConverterService {

    private final CoordinatesConverter coordinatesConverter;

    public Pair<Double, Double> convertToEPSG2180(double x, double y) {
        return coordinatesConverter.convertToEPSG2180(Pair.of(x, y));
    }

    public Pair<Double, Double> convertToCRS84(double x, double y) {
        return coordinatesConverter.convertToCRS84(Pair.of(x, y));
    }
}
