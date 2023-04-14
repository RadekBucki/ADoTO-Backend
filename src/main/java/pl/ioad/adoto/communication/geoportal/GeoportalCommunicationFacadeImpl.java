package pl.ioad.adoto.communication.geoportal;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.ioad.adoto.communication.geoportal.model.SatelliteImage;
import pl.ioad.adoto.communication.geoportal.service.GeoportalAPIService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GeoportalCommunicationFacadeImpl implements GeoportalCommunicationFacade {

    private final GeoportalAPIService geoportalAPIService;
    @Override
    public List<SatelliteImage> getSatelliteImages(double height, double width, double heightResult, double widthResult, double minx, double miny, double maxx, double maxy) {
        return geoportalAPIService.getSatelliteImage(height, width, heightResult, widthResult, minx, miny, maxx, maxy);
    }
}
