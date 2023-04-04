package pl.ioad.adoto.backend.geoportal.logic.mapper;

import org.springframework.stereotype.Component;
import pl.ioad.adoto.backend.geoportal.logic.model.dto.SatelliteImageDto;
import pl.ioad.adoto.communication.geoportal.model.SatelliteImage;

import java.util.function.Function;

@Component
public class SatelliteImageDtoMapper implements Function<SatelliteImage, SatelliteImageDto> {
    @Override
    public SatelliteImageDto apply(SatelliteImage satelliteImage) {
        return SatelliteImageDto.builder()
                .base64(satelliteImage.base64())
                .build();
    }
}
