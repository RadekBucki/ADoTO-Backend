package pl.ioad.adoto.database;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.ioad.adoto.database.dto.TopObjectDTO;
import pl.ioad.adoto.database.entity.EntitiesType;

import java.util.List;

@RestController
@RequestMapping("/db")
@RequiredArgsConstructor
public class DBController {

    private final DBService dbService;

    @PostMapping("/{entitiesType}")
    public ResponseEntity<List<TopObjectDTO>> getAllEntitiesInBoundingBox(@PathVariable EntitiesType entitiesType,
                                                                          @RequestParam Double minX,
                                                                          @RequestParam Double minY,
                                                                          @RequestParam Double maxX,
                                                                          @RequestParam Double maxY) {
        return new ResponseEntity<>(
                dbService.findAllInBoundingBox(entitiesType, minX, minY, maxX, maxY),
                HttpStatus.OK
        );
    }
}
