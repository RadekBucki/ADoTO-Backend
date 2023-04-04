package pl.ioad.adoto.geoportal_communication;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ioad.adoto.geoportal_communication.model.SatelliteImage;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class Controller {

    private final GeoportalCommunicationFacade gcf;

    @GetMapping
    public ResponseEntity<SatelliteImage> get() {
        return ResponseEntity.ok(gcf.getSatelliteImage(1000, 2000, 431970,538000,432030,538120));
    }

}
