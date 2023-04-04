package pl.ioad.adoto.backend.geoportal.logic.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.ioad.adoto.backend.geoportal.logic.mapper.SatelliteImageDtoMapper;
import pl.ioad.adoto.backend.geoportal.logic.model.dto.SatelliteImageDto;
import pl.ioad.adoto.communication.geoportal.GeoportalCommunicationFacade;

@RequiredArgsConstructor
@Service
public class GeoportalService {

    private final GeoportalCommunicationFacade gcf;
    private final SatelliteImageDtoMapper satelliteImageDtoMapper;

    public SatelliteImageDto getSatelliteImage(double height, double width, double minx, double miny, double maxx, double maxy) {
        return satelliteImageDtoMapper.apply(
                gcf.getSatelliteImage(height, width, minx, miny, maxx, maxy)
        );
    }
}
