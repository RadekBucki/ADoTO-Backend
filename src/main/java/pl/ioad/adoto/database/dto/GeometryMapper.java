package pl.ioad.adoto.database.dto;

import org.locationtech.jts.geom.Geometry;

import java.util.Objects;

public class GeometryMapper {

    public static GeometryDTO mapMultiPolygonToDto(Geometry geometry) {
        if (!Objects.equals(geometry.getGeometryType(), Geometry.TYPENAME_MULTIPOLYGON))
            throw new IllegalArgumentException("Unsupported geometry type: " + geometry.getGeometryType());

        Double[][] coordinates = new Double[geometry.getCoordinates().length][2];
        for (int i = 0; i < geometry.getCoordinates().length; i++) {
            coordinates[i][0] = geometry.getCoordinates()[i].getX();
            coordinates[i][1] = geometry.getCoordinates()[i].getY();
        }


        return new GeometryDTO(coordinates, geometry.getGeometryType());
    }
}
