package pl.ioad.adoto.backend.ai.logic.model;

import io.swagger.v3.oas.annotations.media.Schema;

public record AiRequest(
        @Schema(description = "Width of the area from which the objects will be retrieved", example = "1000", requiredMode = Schema.RequiredMode.REQUIRED)
        double width,
        @Schema(description = "Layer name", example = "BUILDING", requiredMode = Schema.RequiredMode.REQUIRED)
        String layer,
        @Schema(description = "Base64 satellite image", requiredMode = Schema.RequiredMode.REQUIRED)
        String base64Image,
        @Schema(description = "Minimum X coordinate of the BBOX", example = "431970", requiredMode = Schema.RequiredMode.REQUIRED)
        double minx,
        @Schema(description = "Minimum Y coordinate of the BBOX", example = "538020", requiredMode = Schema.RequiredMode.REQUIRED)
        double miny,
        @Schema(description = "Maximum X coordinate of the BBOX", example = "432030", requiredMode = Schema.RequiredMode.REQUIRED)
        double maxx,
        @Schema(description = "Maximum Y coordinate of the BBOX", example = "538080", requiredMode = Schema.RequiredMode.REQUIRED)
        double maxy
) {
}
