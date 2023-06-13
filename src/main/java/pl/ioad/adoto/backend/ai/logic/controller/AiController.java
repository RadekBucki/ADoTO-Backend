package pl.ioad.adoto.backend.ai.logic.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ioad.adoto.backend.ai.logic.model.AiRequest;
import pl.ioad.adoto.backend.ai.logic.service.AiService;
import pl.ioad.adoto.communication.ai.model.AiResult;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/ai/svgObjects")
public class AiController {

    private final AiService aiService;

    @PostMapping
    public ResponseEntity<List<List<AiResult>>> get(
            @RequestBody AiRequest aiRequest
    ) {
        return ResponseEntity.ok(aiService.getAiResults(aiRequest));
    }
}
