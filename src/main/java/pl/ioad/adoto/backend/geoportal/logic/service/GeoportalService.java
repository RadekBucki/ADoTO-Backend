package pl.ioad.adoto.backend.geoportal.logic.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import pl.ioad.adoto.backend.coordinates.converter.CoordinatesConverter;
import pl.ioad.adoto.backend.geoportal.logic.mapper.SatelliteImageDtoMapper;
import pl.ioad.adoto.backend.geoportal.logic.model.dto.SatelliteImageDto;
import pl.ioad.adoto.backend.geoportal.logic.service.layers.Layer;
import pl.ioad.adoto.backend.svg.converter.SvgConverter;
import pl.ioad.adoto.backend.svg.converter.model.SvgConvertResponse;
import pl.ioad.adoto.communication.geoportal.GeoportalCommunicationFacade;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class GeoportalService {

    private final GeoportalCommunicationFacade gcf;
    private final SatelliteImageDtoMapper satelliteImageDtoMapper;
    private final SvgConverter svgConverter;
    private final CoordinatesConverter coordinatesConverter;
    private final Map<String, Layer> layers;

    public List<SatelliteImageDto> getSatelliteImagesCropped(double width, double heightResult, double widthResult,
                                                             double minx, double miny, double maxx, double maxy) {
        return gcf.getSatelliteImages(width, heightResult, widthResult, minx, miny, maxx, maxy).stream()
                .map(satelliteImageDtoMapper)
                .toList();
    }

    public SatelliteImageDto getSatelliteImageEPSG2180(double width, double minx, double miny, double maxx, double maxy) {
        return getSatelliteImagesCropped(width, 0, width, minx, miny, maxx, maxy).get(0);
    }

    public SatelliteImageDto getSatelliteImageCRS84(double width, double minx, double miny, double maxx, double maxy) {
        Pair<Double, Double> minCoordinates = coordinatesConverter.convertToEPSG2180(Pair.of(minx, miny));
        Pair<Double, Double> maxCoordinates = coordinatesConverter.convertToEPSG2180(Pair.of(maxx, maxy));
        return getSatelliteImagesCropped(width, 0, width, minCoordinates.getFirst(),
                minCoordinates.getSecond(), maxCoordinates.getFirst(), maxCoordinates.getSecond()).get(0);
    }

    public List<List<SvgConvertResponse>> getSvgObjects(double height, double width, double minx, double miny, double maxx, double maxy, String layer) {
        return svgConverter.getCoordinates(gcf.getSvgObjects(height, width, minx, miny, maxx, maxy, layers.get(layer).getLayersSpell()));
    }
}
