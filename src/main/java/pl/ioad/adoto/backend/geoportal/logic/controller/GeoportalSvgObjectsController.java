package pl.ioad.adoto.backend.geoportal.logic.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ioad.adoto.backend.geoportal.logic.service.GeoportalService;
import pl.ioad.adoto.backend.svg.converter.model.SvgConvertResponse;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/geoportal/svgObjects")
public class GeoportalSvgObjectsController {

    private final GeoportalService geoportalService;

    @Operation(description = "Get svg objects of the place specified with EPSG:2180 coordinates")
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
    public ResponseEntity<List<List<SvgConvertResponse>>> get(
            @Schema(description = "Height of the area from which the objects will be retrieved", example = "1000", requiredMode = Schema.RequiredMode.REQUIRED)
            @RequestParam double height,
            @Schema(description = "Width of the area from which the objects will be retrieved", example = "1000", requiredMode = Schema.RequiredMode.REQUIRED)
            @RequestParam double width,
            @Schema(description = "Minimum X coordinate of the BBOX", example = "431970", requiredMode = Schema.RequiredMode.REQUIRED)
            @RequestParam double minx,
            @Schema(description = "Minimum Y coordinate of the BBOX", example = "538020", requiredMode = Schema.RequiredMode.REQUIRED)
            @RequestParam double miny,
            @Schema(description = "Maximum X coordinate of the BBOX", example = "432030", requiredMode = Schema.RequiredMode.REQUIRED)
            @RequestParam double maxx,
            @Schema(description = "Maximum Y coordinate of the BBOX", example = "538080", requiredMode = Schema.RequiredMode.REQUIRED)
            @RequestParam double maxy,
            @Schema(description = "Layer name", example = "BudMJ", requiredMode = Schema.RequiredMode.REQUIRED)
            @RequestParam String layer
    ) {
        return ResponseEntity.ok(geoportalService.getSvgObjects(height, width,  minx, miny, maxx, maxy, layer));
    }

}
