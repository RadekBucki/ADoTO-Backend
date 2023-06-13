package pl.ioad.adoto.database;

import com.google.common.collect.Streams;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Polygon;
import org.springframework.stereotype.Service;
import pl.ioad.adoto.database.dto.TopObjectDTO;
import pl.ioad.adoto.database.entity.*;
import pl.ioad.adoto.database.entity.predicted.PredictedBuilding;
import pl.ioad.adoto.database.entity.predicted.PredictedForest;
import pl.ioad.adoto.database.entity.predicted.PredictedRiver;
import pl.ioad.adoto.database.entity.predicted.PredictedRoad;
import pl.ioad.adoto.database.entity.sample.Building;
import pl.ioad.adoto.database.entity.sample.Forest;
import pl.ioad.adoto.database.entity.sample.River;
import pl.ioad.adoto.database.entity.sample.Road;

import java.util.List;

import static pl.ioad.adoto.database.dto.GeometryMapper.mapObjectToDto;

@RequiredArgsConstructor
@Service
public class DBService {

    private final TopObjectRepository<PredictedBuilding> buildingsRepository;
    private final TopObjectRepository<PredictedRiver> riversRepository;
    private final TopObjectRepository<PredictedRoad> roadsRepository;
    private final TopObjectRepository<PredictedForest> forestsRepository;


    private final int WINDOW_WIDTH = 1000;

    public List<TopObjectDTO> findAllInBoundingBoxWithCRS84(EntitiesType entitiesType,
                                                   Double minX,
                                                   Double minY,
                                                   Double maxX,
                                                   Double maxY) {

        var intersectingEntities = switch (entitiesType) {
            case HOUSE -> buildingsRepository.findIntersectingBuildingsInCRS84(minX, minY, maxX, maxY);
            case WATER -> riversRepository.findIntersectingRiversInCRS84(minX, minY, maxX, maxY);
            case ROADS -> roadsRepository.findIntersectingRoadsInCRS84(minX, minY, maxX, maxY);
            case FOREST -> forestsRepository.findIntersectingForestsInCRS84(minX, minY, maxX, maxY);
        };

        return intersectingEntities.stream()
                .map(entity -> new TopObjectDTO(entity.getId(), mapObjectToDto(entity.getGeometry())))
                .toList();
    }

    public List<TopObjectDTO> findAllInBoundingBoxWithEPSG2180(EntitiesType entitiesType,
                                                   Double minX,
                                                   Double minY,
                                                   Double maxX,
                                                   Double maxY) {

        var intersectingEntities = switch (entitiesType) {
            case HOUSE -> buildingsRepository.findIntersectingBuildingsInEPSG2180(minX, minY, maxX, maxY);
            case WATER -> riversRepository.findIntersectingRiversInEPSG2180(minX, minY, maxX, maxY);
            case ROADS -> roadsRepository.findIntersectingRoadsInEPSG2180(minX, minY, maxX, maxY);
            case FOREST -> forestsRepository.findIntersectingForestsInEPSG2180(minX, minY, maxX, maxY);
        };

        return intersectingEntities.stream()
                .map(entity -> new TopObjectDTO(entity.getId(), mapObjectToDto(entity.getGeometry())))
                .toList();
    }

    public TopEntity savePrediction(List<Double> x,
                                    List<Double> y,
                                    Double minX,
                                    Double minY,
                                    Double maxX,
                                    Double maxY,
                                    EntitiesType entitiesType) {

        var xStep = (maxX - minX) / WINDOW_WIDTH;
        var yStep = (maxY - minY) / WINDOW_WIDTH;
        System.out.println(xStep + " " + yStep);

        var geoX = x.stream().map(xCoord -> xCoord * xStep).toList();
        var geoY = y.stream().map(yCoord -> yCoord * yStep).toList();
        var coords = Streams.zip(geoX.stream(), geoY.stream(), Coordinate::new).toArray(Coordinate[]::new);

        var geometryFactory = new GeometryFactory();

        switch (entitiesType) {
            case HOUSE -> {
                var shell = new GeometryFactory().createLinearRing(coords);
                var polygon = new Polygon(shell, null, geometryFactory);
                var building = new PredictedBuilding();
                building.setGeometry(polygon);
                return buildingsRepository.save(building);
            }
            case FOREST -> {
                var shell = new GeometryFactory().createLinearRing(coords);
                var polygon = new Polygon(shell, null, geometryFactory);
                var forest = new PredictedForest();
                forest.setGeometry(polygon);
                return forestsRepository.save(forest);
            }
            case WATER -> {
                var lineString = geometryFactory.createLineString(coords);
                var river = new PredictedRiver();
                river.setGeometry(lineString);
                return riversRepository.save(river);
            }
            case ROADS -> {
                var lineString = geometryFactory.createLineString(coords);
                var road = new PredictedRoad();
                road.setGeometry(lineString);
                return roadsRepository.save(road);
            }
            default -> throw new IllegalStateException("Unexpected value: " + entitiesType);
        }
    }
}

