package pl.ioad.adoto.database.dto;

import org.locationtech.jts.geom.Geometry;

import java.util.Arrays;
import java.util.Objects;

public class GeometryMapper {

    public static GeometryDTO mapObjectToDto(Geometry geometry) {
        if (!validateGeometryType(geometry.getGeometryType()))
            throw new IllegalArgumentException("Unsupported geometry type: " + geometry.getGeometryType());

        double[][] coordinates = Arrays.stream(geometry.getCoordinates())
                .map(coordinate -> new double[]{coordinate.x, coordinate.y})
                .toArray(double[][]::new);

        return new GeometryDTO(coordinates, geometry.getGeometryType());
    }

    private static boolean validateGeometryType(String geometryType) {
        return Objects.equals(geometryType, Geometry.TYPENAME_MULTIPOLYGON)
                || Objects.equals(geometryType, Geometry.TYPENAME_MULTILINESTRING);
    }
}
