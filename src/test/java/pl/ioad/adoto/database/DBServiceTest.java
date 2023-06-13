package pl.ioad.adoto.database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.ioad.adoto.backend.ai.logic.controller.AiController;
import pl.ioad.adoto.backend.ai.logic.service.AiService;
import pl.ioad.adoto.communication.ai.model.AiResult;
import pl.ioad.adoto.database.dto.TopObjectDTO;
import pl.ioad.adoto.database.entity.EntitiesType;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DBServiceTest {

    @Autowired
    DBService dbService;

    @Autowired
    AiService aiService;

    @Test
    @Disabled
    void findAllInBoundingBox1() {
        List<TopObjectDTO> buildings =
                dbService.findAllInBoundingBoxWithCRS84(EntitiesType.HOUSE, 19.450633, 51.748096,19.456443, 51.744854);
        assertEquals(48, buildings.size());
    }
    @Test
    @Disabled
    void findAllInBoundingBox2() {
        List<TopObjectDTO> buildings =
                dbService.findAllInBoundingBoxWithEPSG2180(EntitiesType.HOUSE, 531094.4, 431346.7 , 531497.8,431055.4);
        assertEquals(43, buildings.size());
    }

    @Test
    void saveBuildingsTest() {
        var minX = 430884.0;
        var minY = 531059.0;
        var maxX = 431414.0;
        var maxY = 531589.0;

        List<Double> X = List.of(
                0d, 0d, 1000d, 1000d
        );

        List<Double> Y = List.of(
                0d, 1000d, 1000d, 0d
        );

        var objects = aiService.getAiResults(1000, minX, minY, maxX, maxY, "BUILDING");
        for (var object : objects) {
            dbService.savePrediction(object.stream().map(AiResult::x).toList(), object.stream().map(AiResult::y).toList(),
                    minY, minX, maxY, maxX, "BUILDING");
        }
        if (objects.size() != 0)
            dbService.savePrediction(X, Y, minY, minX, maxY, maxX, "BUILDING");
    }

    @Test
    void saveRoadsTest() {
        var minX = 432004.42;
        var minY = 537973.68;
        var maxX = 432125.41;
        var maxY = 538086.42;

        List<Double> X = List.of(
                0d, 0d, 1000d, 1000d
        );

        List<Double> Y = List.of(
                0d, 1000d, 1000d, 0d
        );

        var objects = aiService.getAiResults(1000, minX, minY, maxX, maxY, "ROAD");
        for (var object : objects) {
            dbService.savePrediction(object.stream().map(AiResult::x).toList(), object.stream().map(AiResult::y).toList(),
                    minY, minX, maxY, maxX, "ROAD");
        }
        if (objects.size() != 0)
            dbService.savePrediction(X, Y, minY, minX, maxY, maxX, "BUILDING");
    }

    @Test
    void saveRiversTest() {
        var minX = 488084.9242510749;
        var minY = 637809.0460027386;
        var maxX = 489153.58379110415;
        var maxY = 638877.7055427679;

        List<Double> X = List.of(
                0d, 0d, 1000d, 1000d
        );

        List<Double> Y = List.of(
                0d, 1000d, 1000d, 0d
        );

        var objects = aiService.getAiResults(1000, minX, minY, maxX, maxY, "WATER");
        for (var object : objects) {
            dbService.savePrediction(object.stream().map(AiResult::x).toList(), object.stream().map(AiResult::y).toList(),
                    minY, minX, maxY, maxX, "WATER");
        }
        if (objects.size() != 0)
            dbService.savePrediction(X, Y, minY, minX, maxY, maxX, "BUILDING");
    }

    @Test
    void saveForestsTest() {
        var minX = 386139.6016806719;
        var minY = 618634.2071734095;
        var maxX = 386514.0410068406;
        var maxY = 618865.2862457229;

        List<Double> X = List.of(
                0d, 0d, 1000d, 1000d
        );

        List<Double> Y = List.of(
                0d, 1000d, 1000d, 0d
        );

        var objects = aiService.getAiResults(1000, minX, minY, maxX, maxY, "FOREST");
        for (var object : objects) {
            dbService.savePrediction(object.stream().map(AiResult::x).toList(), object.stream().map(AiResult::y).toList(),
                    minY, minX, maxY, maxX, "FOREST");
        }
        if (objects.size() != 0)
            dbService.savePrediction(X, Y, minY, minX, maxY, maxX, "BUILDING");
    }
}