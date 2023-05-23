package pl.ioad.adoto.backend.geoportal.logic.controller;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ioad.adoto.backend.geoportal.logic.service.CoordinatesConverterService;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/convert/to")
public class CoordinatesConverterController {

    private final CoordinatesConverterService coordinatesConverterService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully converted coordinates from CRS84 to EPSG2180"),
    })
    @GetMapping("epsg2180")
    public ResponseEntity<Pair<Double, Double>> convertToEPSG2180(
            @Schema(description = "CRS84 N coordinate", example = "51.752825479702224", requiredMode = Schema.RequiredMode.REQUIRED)
            @RequestParam double x,
            @Schema(description = "CRS84 E coordinate", example = "19.55066849139178", requiredMode = Schema.RequiredMode.REQUIRED)
            @RequestParam double y
    ) {
        return ResponseEntity.ok(coordinatesConverterService.convertToEPSG2180(x, y));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully converted coordinates from EPSG2180 to CRS84"),
    })
    @GetMapping("crs84")
    public ResponseEntity<Pair<Double, Double>> convertToCRS84(
            @Schema(description = "EPSG2180 x coordinate", example = "431970", requiredMode = Schema.RequiredMode.REQUIRED)
            @RequestParam double x,
            @Schema(description = "EPSG2180 y coordinate", example = "538000", requiredMode = Schema.RequiredMode.REQUIRED)
            @RequestParam double y
    ) {
        return ResponseEntity.ok(coordinatesConverterService.convertToCRS84(x, y));
    }
}
