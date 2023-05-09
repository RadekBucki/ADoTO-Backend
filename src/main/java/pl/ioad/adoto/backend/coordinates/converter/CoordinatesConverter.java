package pl.ioad.adoto.backend.coordinates.converter;

import org.locationtech.proj4j.*;
import org.springframework.data.util.Pair;

public class CoordinatesConverter {
    private final CoordinateReferenceSystem crs84;
    private final CoordinateReferenceSystem epsg2180;
    private final CoordinateTransformFactory ctFactory = new CoordinateTransformFactory();
    public CoordinatesConverter() {
        CRSFactory factory = new CRSFactory();
        crs84 = factory.createFromParameters("crs84", "+proj=longlat +datum=WGS84 +no_defs");
        epsg2180 = factory.createFromParameters("epsg2180", "+proj=tmerc +lat_0=0 +lon_0=19 +k=0.9993 +x_0=500000 +y_0=-5300000 +ellps=GRS80 +units=m +no_defs");
    }

    public Pair<Double, Double> convertToCRS84(Pair<Double, Double> epsg2180coordinates) {
        return convert(ctFactory.createTransform(epsg2180, crs84), epsg2180coordinates.getSecond(), epsg2180coordinates.getFirst());
    }

    public Pair<Double, Double> convertToEPSG2180(Pair<Double, Double> crs84coordinates) {
        return convert(ctFactory.createTransform(crs84, epsg2180), crs84coordinates.getSecond(), crs84coordinates.getFirst());
    }

    public Pair<Double, Double> convert(CoordinateTransform transform, double x, double y) {
        ProjCoordinate srcCoord = new ProjCoordinate(x, y);
        ProjCoordinate destCoord = new ProjCoordinate();
        transform.transform(srcCoord, destCoord);
        return Pair.of(destCoord.y, destCoord.x);
    }
}
