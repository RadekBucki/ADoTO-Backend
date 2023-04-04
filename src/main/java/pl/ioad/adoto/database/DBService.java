package pl.ioad.adoto.database;

import org.springframework.beans.factory.annotation.Autowired;
import pl.ioad.adoto.database.dto.BuildingDTO;
import pl.ioad.adoto.database.dto.GeometryMapper;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class DBService {
    @Autowired
    private TopObjectRepository repository;

    public List<BuildingDTO> findAllInBoundingBox(Double minX, Double minY, Double maxX, Double maxY) {
        List<BuildingDTO> buildingDTOS = new ArrayList<>();

        repository.findIntersectingEntities(minX, minY, maxX, maxY).forEach(e ->
                buildingDTOS.add(new BuildingDTO(e.getId(), GeometryMapper.mapMultiPolygonToDto(e.getGeometry()))));

        return buildingDTOS;
    }
}

