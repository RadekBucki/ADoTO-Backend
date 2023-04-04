package pl.ioad.adoto.communication.geoportal;

import pl.ioad.adoto.communication.geoportal.model.SatelliteImage;

public interface GeoportalCommunicationFacade {
    SatelliteImage getSatelliteImage(double height, double width, double minx, double miny, double maxx, double maxy);
}
