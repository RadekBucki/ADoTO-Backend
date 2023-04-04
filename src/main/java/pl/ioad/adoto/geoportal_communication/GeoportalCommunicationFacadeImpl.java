package pl.ioad.adoto.geoportal_communication;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.ioad.adoto.geoportal_communication.model.SatelliteImage;
import pl.ioad.adoto.geoportal_communication.service.GeoportalService;

@Component
@RequiredArgsConstructor
public class GeoportalCommunicationFacadeImpl implements GeoportalCommunicationFacade {

    private final GeoportalService geoportalService;
    @Override
    public SatelliteImage getSatelliteImage(double height, double width, double minx, double miny, double maxx, double maxy) {
        return geoportalService.getSatelliteImage(height, width, minx, miny, maxx, maxy);
    }
}
