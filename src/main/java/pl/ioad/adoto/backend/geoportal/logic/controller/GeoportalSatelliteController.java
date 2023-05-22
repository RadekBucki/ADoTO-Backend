package pl.ioad.adoto.backend.geoportal.logic.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.ioad.adoto.backend.geoportal.logic.model.dto.SatelliteImageDto;
import pl.ioad.adoto.backend.geoportal.logic.service.GeoportalService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/geoportal/satellite")
public class GeoportalSatelliteController {

    private final GeoportalService geoportalService;

    @Operation(description = "Get satellite image of the place specified with EPSG:2180 coordinates")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved question checks list for chosen audit"),
            @ApiResponse(responseCode = "400", description = "BBOX coordinates has different ratio than requested image", content = {
                    @Content(
                            examples = {
                                    @ExampleObject(
                                            value = "{" +
                                                    "\"message\": \"BBOX and width/height ratio is different!\"," +
                                                    "\"statusCode\": \"400\"," +
                                                    "\"errors\": {\"WrongInputDataException\": \"BBOX and width/height ratio is different!\"}" +
                                                    "}"
                                    )
                            }
                    )
            }),
            @ApiResponse(responseCode = "408", description = "Geoportal API timeout", content = {
                    @Content(
                            examples = {
                                    @ExampleObject(
                                            value = "{" +
                                                    "\"message\": \"Geoportal API timeout\"," +
                                                    "\"statusCode\": \"408\"," +
                                                    "\"errors\": {\"GeoportalTimeoutException\": \"Error message\"}" +
                                                    "}"
                                    )
                            }
                    )
            }),
    })
    @GetMapping("epsg2180/divide")
    public ResponseEntity<List<SatelliteImageDto>> get(
            @Schema(description = "Width of the initial image to be cropped in pixels", example = "4000", requiredMode = Schema.RequiredMode.REQUIRED)
            @RequestParam double width,
            @Schema(description = "Height of the return image in pixels", example = "1000", requiredMode = Schema.RequiredMode.REQUIRED)
            @RequestParam double heightResult,
            @Schema(description = "Width of the return image in pixels", example = "1000", requiredMode = Schema.RequiredMode.REQUIRED)
            @RequestParam double widthResult,
            @Schema(description = "Bounding box bottom left corner x in EPSG:2180 standard", example = "431970", requiredMode = Schema.RequiredMode.REQUIRED)
            @RequestParam double minx,
            @Schema(description = "Bounding box bottom left corner y in EPSG:2180 standard", example = "538000", requiredMode = Schema.RequiredMode.REQUIRED)
            @RequestParam double miny,
            @Schema(description = "Bounding box top right corner x in EPSG:2180 standard", example = "432170", requiredMode = Schema.RequiredMode.REQUIRED)
            @RequestParam double maxx,
            @Schema(description = "Bounding box top right corner y in EPSG:2180 standard", example = "538200", requiredMode = Schema.RequiredMode.REQUIRED)
            @RequestParam double maxy) {
        return ResponseEntity.ok(geoportalService.getSatelliteImagesCropped(width, heightResult, widthResult, minx, miny, maxx, maxy));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved question checks list for chosen audit"),
            @ApiResponse(responseCode = "400", description = "BBOX coordinates has different ratio than requested image", content = {
                    @Content(
                            examples = {
                                    @ExampleObject(
                                            value = "{" +
                                                    "\"message\": \"BBOX and width/height ratio is different!\"," +
                                                    "\"statusCode\": \"400\"," +
                                                    "\"errors\": {\"WrongInputDataException\": \"BBOX and width/height ratio is different!\"}" +
                                                    "}"
                                    )
                            }
                    )
            }),
            @ApiResponse(responseCode = "408", description = "Geoportal API timeout", content = {
                    @Content(
                            examples = {
                                    @ExampleObject(
                                            value = "{" +
                                                    "\"message\": \"Geoportal API timeout\"," +
                                                    "\"statusCode\": \"408\"," +
                                                    "\"errors\": {\"GeoportalTimeoutException\": \"Error message\"}" +
                                                    "}"
                                    )
                            }
                    )
            }),
    })
    @GetMapping("/epsg2180")
    public ResponseEntity<SatelliteImageDto> getInEPSG2180(
            @Schema(description = "Width of the initial image to be cropped in pixels", example = "1000", requiredMode = Schema.RequiredMode.REQUIRED)
            @RequestParam double width,
            @Schema(description = "Bounding box bottom left corner x in EPSG:2180 standard", example = "431970", requiredMode = Schema.RequiredMode.REQUIRED)
            @RequestParam double minx,
            @Schema(description = "Bounding box bottom left corner y in EPSG:2180 standard", example = "538000", requiredMode = Schema.RequiredMode.REQUIRED)
            @RequestParam double miny,
            @Schema(description = "Bounding box top right corner x in EPSG:2180 standard", example = "432170", requiredMode = Schema.RequiredMode.REQUIRED)
            @RequestParam double maxx,
            @Schema(description = "Bounding box top right corner y in EPSG:2180 standard", example = "538200", requiredMode = Schema.RequiredMode.REQUIRED)
            @RequestParam double maxy) {
        return ResponseEntity.ok(geoportalService.getSatelliteImageEPSG2180(width, minx, miny, maxx, maxy));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved question checks list for chosen audit"),
            @ApiResponse(responseCode = "400", description = "BBOX coordinates has different ratio than requested image", content = {
                    @Content(
                            examples = {
                                    @ExampleObject(
                                            value = "{" +
                                                    "\"message\": \"BBOX and width/height ratio is different!\"," +
                                                    "\"statusCode\": \"400\"," +
                                                    "\"errors\": {\"WrongInputDataException\": \"BBOX and width/height ratio is different!\"}" +
                                                    "}"
                                    )
                            }
                    )
            }),
            @ApiResponse(responseCode = "408", description = "Geoportal API timeout", content = {
                    @Content(
                            examples = {
                                    @ExampleObject(
                                            value = "{" +
                                                    "\"message\": \"Geoportal API timeout\"," +
                                                    "\"statusCode\": \"408\"," +
                                                    "\"errors\": {\"GeoportalTimeoutException\": \"Error message\"}" +
                                                    "}"
                                    )
                            }
                    )
            }),
    })
    @GetMapping("/crs84")
    public ResponseEntity<SatelliteImageDto> getInCRS84(
            @Schema(description = "Width of the initial image to be cropped in pixels", example = "1000", requiredMode = Schema.RequiredMode.REQUIRED)
            @RequestParam double width,
            @Schema(description = "Bounding box bottom left corner x in CRS:84 standard", example = "51.752842", requiredMode = Schema.RequiredMode.REQUIRED)
            @RequestParam double minx,
            @Schema(description = "Bounding box bottom left corner y in CRS:84 standard", example = "19.551108", requiredMode = Schema.RequiredMode.REQUIRED)
            @RequestParam double miny,
            @Schema(description = "Bounding box top right corner x in CRS:84 standard", example = "51.753617", requiredMode = Schema.RequiredMode.REQUIRED)
            @RequestParam double maxx,
            @Schema(description = "Bounding box top right corner y in CRS:84 standard", example = "19.551883", requiredMode = Schema.RequiredMode.REQUIRED)
            @RequestParam double maxy) {
        return ResponseEntity.ok(geoportalService.getSatelliteImageCRS84(width, minx, miny, maxx, maxy));
    }

}
