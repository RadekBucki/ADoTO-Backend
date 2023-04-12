package pl.ioad.adoto.database;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.ioad.adoto.database.dto.TopObjectDTO;
import pl.ioad.adoto.database.entity.Building;
import pl.ioad.adoto.database.entity.River;
import pl.ioad.adoto.database.entity.Road;

import java.util.ArrayList;
import java.util.List;

import static pl.ioad.adoto.database.dto.GeometryMapper.mapObjectToDto;

@RequiredArgsConstructor
@Service
public class DBService {

    private final TopObjectRepository<Building> buildingsRepository;
    private final TopObjectRepository<River> riversRepository;
    private final TopObjectRepository<Road> roadsRepository;

    public List<TopObjectDTO> findAllInBoundingBox(EntitiesType entitiesType,
                                                   Double minX,
                                                   Double minY,
                                                   Double maxX,
                                                   Double maxY) {

        List<TopObjectDTO> topObjectDTOs = new ArrayList<>();
        var intersectingEntities = switch (entitiesType) {
            case BUILDINGS -> buildingsRepository.findIntersectingBuildings(minX, minY, maxX, maxY);
            case RIVERS -> riversRepository.findIntersectingRivers(minX, minY, maxX, maxY);
            case ROADS -> roadsRepository.findIntersectingRoads(minX, minY, maxX, maxY);
        };
        intersectingEntities.forEach(e ->
                topObjectDTOs.add(
                        new TopObjectDTO(e.getId(), mapObjectToDto(e.getGeometry()))));
        return topObjectDTOs;
    }

    public enum EntitiesType {
        BUILDINGS,
        RIVERS,
        ROADS
    }
}

