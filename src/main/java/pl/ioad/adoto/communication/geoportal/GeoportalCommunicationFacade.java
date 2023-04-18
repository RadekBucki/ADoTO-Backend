package pl.ioad.adoto.communication.geoportal;

import pl.ioad.adoto.communication.geoportal.model.SvgObject;
import pl.ioad.adoto.communication.geoportal.model.SatelliteImage;

import java.util.List;

public interface GeoportalCommunicationFacade {
    List<SatelliteImage> getSatelliteImages(double height, double width, double heightResult, double widthResult, double minx, double miny, double maxx, double maxy);
    List<SvgObject> getSvgObjects(double height, double width, double minx, double miny, double maxx, double maxy, String layer);
}
