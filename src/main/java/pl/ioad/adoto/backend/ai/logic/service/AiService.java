package pl.ioad.adoto.backend.ai.logic.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.ioad.adoto.backend.layers.Layer;
import pl.ioad.adoto.communication.ai.AICommunicationFacade;
import pl.ioad.adoto.communication.ai.exception.AiResponseFailedException;
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

    public List<List<AiResult>> getAiResults(double width, double minx, double miny, double maxx, double maxy, String layer) {
        var aiResults = aiCommunicationFacade.getAiResults(width, minx, miny, maxx, maxy, layers.get(layer).getAiSpell());
        if (aiResults == null)
            throw new AiResponseFailedException("Failed occur while generating AI results");

        aiResults.forEach(res -> dbService.savePrediction(
                res.stream().map(AiResult::x).toList(),
                res.stream().map(AiResult::y).toList(),
                miny, minx, maxy, maxx,
                layer));
        return aiResults;
    }
}
