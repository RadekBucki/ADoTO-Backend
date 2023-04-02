package pl.ioad.adoto.geoportal_communication;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class Controller {

    private final GeoportalCommunicationFacade gcf;

    @GetMapping
    public ResponseEntity<String> get() {
        gcf.test();
        return ResponseEntity.ok("dupa");
    }

}
