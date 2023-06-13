package pl.ioad.adoto.backend.ai.logic.controller;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ioad.adoto.backend.ai.logic.service.AiService;
import pl.ioad.adoto.backend.geoportal.logic.validation.LayerEnum;
import pl.ioad.adoto.backend.geoportal.logic.validation.ValueOfEnumExist;
import pl.ioad.adoto.communication.ai.model.AiResult;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/ai/svgObjects")
public class AiController {

    private final AiService aiService;

    @GetMapping
    public ResponseEntity<List<List<AiResult>>> get(
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
            @Schema(description = "Layer name", example = "BUILDING", requiredMode = Schema.RequiredMode.REQUIRED)
            @RequestParam @ValueOfEnumExist(enumClass = LayerEnum.class) String layer
    ) {
        var result = aiService.getAiResults(width, minx, miny, maxx, maxy, layer);

        return ResponseEntity.ok(result);
    }
}
