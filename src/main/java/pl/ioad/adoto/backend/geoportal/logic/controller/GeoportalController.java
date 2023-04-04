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

@RestController
@RequiredArgsConstructor
@RequestMapping("/satellite")
public class GeoportalController {

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
    @GetMapping
    public ResponseEntity<SatelliteImageDto> get(
            @Schema(description = "Height of the return image in pixels", example = "1000", requiredMode = Schema.RequiredMode.REQUIRED)
            @RequestParam double height,
            @Schema(description = "Width of the return image in pixels", example = "2000", requiredMode = Schema.RequiredMode.REQUIRED)
            @RequestParam double width,
            @Schema(description = "Bounding box bottom left corner x in EPSG:2180 standard", example = "431970", requiredMode = Schema.RequiredMode.REQUIRED)
            @RequestParam double minx,
            @Schema(description = "Bounding box bottom left corner y in EPSG:2180 standard", example = "538000", requiredMode = Schema.RequiredMode.REQUIRED)
            @RequestParam double miny,
            @Schema(description = "Bounding box top right corner x in EPSG:2180 standard", example = "432030", requiredMode = Schema.RequiredMode.REQUIRED)
            @RequestParam double maxx,
            @Schema(description = "Bounding box top right corner y in EPSG:2180 standard", example = "538120", requiredMode = Schema.RequiredMode.REQUIRED)
            @RequestParam double maxy) {
        return ResponseEntity.ok(geoportalService.getSatelliteImage(height, width, minx, miny, maxx, maxy));
    }

}
