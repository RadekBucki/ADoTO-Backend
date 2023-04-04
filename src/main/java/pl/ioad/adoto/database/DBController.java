package pl.ioad.adoto.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.ioad.adoto.database.dto.BuildingDTO;

import java.util.List;

@RestController
@RequestMapping("/db")
public class DBController {

    @Autowired
    private DBService dbService;

    @PostMapping("/buildings")
    public ResponseEntity<List<BuildingDTO>> getAllBuildingsInBoundingBox(@RequestParam Double minX,
                                                                          @RequestParam Double minY,
                                                                          @RequestParam Double maxX,
                                                                          @RequestParam Double maxY) {
        return new ResponseEntity<>(dbService.findAllInBoundingBox(minX, minY, maxX, maxY),
                HttpStatus.OK);
    }
}
