package pl.ioad.adoto.database.dto;

import org.locationtech.jts.geom.Geometry;
import org.springframework.data.util.Pair;

import java.util.Arrays;
import java.util.Objects;

public class GeometryMapper {

    public static GeometryDTO mapObjectToDto(Geometry geometry) {
        if (!validateGeometryType(geometry.getGeometryType()))
            throw new IllegalArgumentException("Unsupported geometry type: " + geometry.getGeometryType());

        var coordinates = Arrays.stream(geometry.getCoordinates())
                .map(coordinate -> Pair.of(coordinate.x, coordinate.y))
                .toList();

        return new GeometryDTO(coordinates, geometry.getGeometryType());
    }

    private static boolean validateGeometryType(String geometryType) {
        return Objects.equals(geometryType, Geometry.TYPENAME_MULTIPOLYGON)
                || Objects.equals(geometryType, Geometry.TYPENAME_MULTILINESTRING);
    }
}
