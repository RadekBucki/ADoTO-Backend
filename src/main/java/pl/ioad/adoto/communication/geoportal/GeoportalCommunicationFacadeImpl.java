package pl.ioad.adoto.communication.geoportal;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.ioad.adoto.communication.geoportal.model.SatelliteImage;
import pl.ioad.adoto.communication.geoportal.service.GeoportalAPIService;

@Component
@RequiredArgsConstructor
public class GeoportalCommunicationFacadeImpl implements GeoportalCommunicationFacade {

    private final GeoportalAPIService geoportalAPIService;
    @Override
    public SatelliteImage getSatelliteImage(double height, double width, double minx, double miny, double maxx, double maxy) {
        return geoportalAPIService.getSatelliteImage(height, width, minx, miny, maxx, maxy);
    }
}
