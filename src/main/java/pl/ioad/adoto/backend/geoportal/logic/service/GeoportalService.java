package pl.ioad.adoto.backend.geoportal.logic.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.ioad.adoto.backend.geoportal.logic.mapper.SatelliteImageDtoMapper;
import pl.ioad.adoto.backend.geoportal.logic.model.dto.SatelliteImageDto;
import pl.ioad.adoto.communication.geoportal.GeoportalCommunicationFacade;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GeoportalService {

    private final GeoportalCommunicationFacade gcf;
    private final SatelliteImageDtoMapper satelliteImageDtoMapper;

    public List<SatelliteImageDto> getSatelliteImage(double height, double width, double heightResult, double widthResult,
                                                     double minx, double miny, double maxx, double maxy) {
        return gcf.getSatelliteImages(height, width, heightResult, widthResult, minx, miny, maxx, maxy).stream()
                .map(satelliteImageDtoMapper)
                .toList();
    }
}
