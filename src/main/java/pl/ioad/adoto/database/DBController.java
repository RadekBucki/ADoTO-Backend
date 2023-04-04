package pl.ioad.adoto.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
                                                                          @RequestParam Double maxY,
                                                                          @RequestParam Integer srId) {
        return new ResponseEntity<>(dbService.findAllInBoundingBox(minX, minY, maxX, maxY, srId),
                HttpStatusCode.valueOf(200));
    }
}
