package pl.ioad.adoto.geoportal_communication;

import pl.ioad.adoto.geoportal_communication.model.SatelliteImage;

public interface GeoportalCommunicationFacade {
    SatelliteImage getSatelliteImage(double height, double width, double minx, double miny, double maxx, double maxy);
}
