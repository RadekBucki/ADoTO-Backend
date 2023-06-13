package pl.ioad.adoto.backend.ai.logic.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
    private final Map<String, Layer> layers;

    public List<List<AiResult>> getAiResults(double width, double minx, double miny, double maxx, double maxy, String layer) {
        return aiCommunicationFacade.getAiResults(width, minx, miny, maxx, maxy, layers.get(layer).getAiSpell());
    }
}
