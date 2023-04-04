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
    public SatelliteImage getSatelliteImage() {
        return geoportalService.getSatelliteImage();
    }
}
