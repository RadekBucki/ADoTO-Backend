package pl.ioad.adoto.backend.ai.logic.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.ioad.adoto.backend.ai.logic.model.AiRequest;
import pl.ioad.adoto.backend.layers.Layer;
import pl.ioad.adoto.communication.ai.AICommunicationFacade;
import pl.ioad.adoto.communication.ai.model.AiResult;
import pl.ioad.adoto.database.DBService;


import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AiService {

    private final AICommunicationFacade aiCommunicationFacade;
    private final DBService dbService;
    private final Map<String, Layer> layers;

    public List<List<AiResult>> getAiResults(AiRequest aiRequest) {
        var aiResults = aiCommunicationFacade.getAiResults(aiRequest.width(),
                layers.get(aiRequest.layer()).getAiSpell(), aiRequest.base64Image());
        if (aiResults != null && !aiResults.isEmpty())
            aiResults.forEach(res -> dbService.savePrediction(
                    res.stream().map(AiResult::x).toList(),
                    res.stream().map(AiResult::y).toList(),
                    aiRequest.miny(), aiRequest.minx(), aiRequest.maxy(), aiRequest.maxx(),
                    aiRequest.layer()));

        return aiResults;
    }
}
