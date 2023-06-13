package pl.ioad.adoto.backend.coordinates.converter;

import org.junit.jupiter.api.Test;
import org.springframework.data.util.Pair;
import pl.ioad.adoto.backend.coordinates.converter.service.CoordinatesConverter;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CoordinatesConverterTest {

    private final CoordinatesConverter coordinatesConverter = new CoordinatesConverter();

    @Test
    void convertToCRS84() {
        double x = 433277.03;
        double y = 530898.38;
        Pair<Double, Double> crs84Coords = coordinatesConverter.convertToCRS84(Pair.of(x, y));
        assertEquals(51.76, crs84Coords.getFirst(), 0.1);
        assertEquals(19.44, crs84Coords.getSecond(), 0.1);
    }

    @Test
    void convertToEPSG2180() {
        double x = 51.76501763818851;
        double y = 19.44787701965619;
        Pair<Double, Double> epsg2180Coords = coordinatesConverter.convertToEPSG2180(Pair.of(x, y));
        assertEquals(433277.03, epsg2180Coords.getFirst(), 0.1);
        assertEquals(530898.38, epsg2180Coords.getSecond(), 0.1);
    }
}