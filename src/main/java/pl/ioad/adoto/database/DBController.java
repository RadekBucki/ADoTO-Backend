package pl.ioad.adoto.database;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.ioad.adoto.database.dto.TopObjectDTO;

import java.util.List;

import static pl.ioad.adoto.database.DBService.EntitiesType.BUILDINGS;
import static pl.ioad.adoto.database.DBService.EntitiesType.RIVERS;

@RestController
@RequestMapping("/db")
@RequiredArgsConstructor
public class DBController {

    private final DBService dbService;

    @PostMapping("/buildings")
    public ResponseEntity<List<TopObjectDTO>> getAllBuildingsInBoundingBox(@RequestParam Double minX,
                                                                           @RequestParam Double minY,
                                                                           @RequestParam Double maxX,
                                                                           @RequestParam Double maxY) {
        return getAllObjectsInBoundingBox(BUILDINGS, minX, minY, maxX, maxY);
    }

    @PostMapping("/rivers")
    public ResponseEntity<List<TopObjectDTO>> getAllRiversInBoundingBox(@RequestParam Double minX,
                                                                        @RequestParam Double minY,
                                                                        @RequestParam Double maxX,
                                                                        @RequestParam Double maxY) {
        return getAllObjectsInBoundingBox(RIVERS, minX, minY, maxX, maxY);
    }

    private ResponseEntity<List<TopObjectDTO>> getAllObjectsInBoundingBox(DBService.EntitiesType entitiesType,
                                                                          Double minX,
                                                                          Double minY,
                                                                          Double maxX,
                                                                          Double maxY) {
        return new ResponseEntity<>(
                dbService.findAllInBoundingBox(entitiesType, minX, minY, maxX, maxY),
                HttpStatus.OK);
    }
}
