package pl.ioad.adoto.database;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ioad.adoto.database.dto.BuildingDTO;
import pl.ioad.adoto.database.dto.GeometryMapper;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DBService {

    private final TopObjectRepository repository;

    public List<BuildingDTO> findAllInBoundingBox(Double minX, Double minY, Double maxX, Double maxY) {
        List<BuildingDTO> buildingDTOS = new ArrayList<>();

        repository.findIntersectingEntities(minX, minY, maxX, maxY).forEach(e ->
                buildingDTOS.add(new BuildingDTO(e.getId(), GeometryMapper.mapMultiPolygonToDto(e.getGeometry()))));

        return buildingDTOS;
    }
}

