package pl.ioad.adoto.database.dto;

import java.awt.geom.Point2D;
import java.util.List;

public record GeometryDTO(List<Point2D.Double> coordinates, String type) {
}
