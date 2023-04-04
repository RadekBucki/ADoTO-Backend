package pl.ioad.adoto.backend.geoportal.logic.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record SatelliteImageDto(
        @Schema(description = "BASE64 encoded satellite image",
                example = "iVBORw0KGgoAAAANSUhEUgAAB9AAAAPoCAIAAAAJPPL9AAAAAXNSR0IB2cksfwAAAAlwSFlzAAAOxAAADsQBlSsO...",
                requiredMode = Schema.RequiredMode.REQUIRED)
        String base64
) {
}
