package pl.ioad.adoto.database;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.ioad.adoto.database.dto.TopObjectDTO;
import pl.ioad.adoto.database.entity.Building;
import pl.ioad.adoto.database.entity.EntitiesType;
import pl.ioad.adoto.database.entity.Field;
import pl.ioad.adoto.database.entity.Forest;
import pl.ioad.adoto.database.entity.River;
import pl.ioad.adoto.database.entity.Road;

import java.util.List;

import static pl.ioad.adoto.database.dto.GeometryMapper.mapObjectToDto;

@RequiredArgsConstructor
@Service
public class DBService {

    private final TopObjectRepository<Building> buildingsRepository;
    private final TopObjectRepository<River> riversRepository;
    private final TopObjectRepository<Road> roadsRepository;
    private final TopObjectRepository<Forest> forestsRepository;
    private final TopObjectRepository<Field> fieldsRepository;

    public List<TopObjectDTO> findAllInBoundingBox(EntitiesType entitiesType,
                                                   Double minX,
                                                   Double minY,
                                                   Double maxX,
                                                   Double maxY) {

        var intersectingEntities = switch (entitiesType) {
            case BUILDINGS -> buildingsRepository.findIntersectingBuildings(minX, minY, maxX, maxY);
            case RIVERS -> riversRepository.findIntersectingRivers(minX, minY, maxX, maxY);
            case ROADS -> roadsRepository.findIntersectingRoads(minX, minY, maxX, maxY);
            case FORESTS -> forestsRepository.findIntersectingForests(minX, minY, maxX, maxY);
            case FIELDS -> fieldsRepository.findIntersectingFields(minX, minY, maxX, maxY);
        };

        return intersectingEntities.stream()
                .map(entity -> new TopObjectDTO(entity.getId(), mapObjectToDto(entity.getGeometry())))
                .toList();
    }
}

