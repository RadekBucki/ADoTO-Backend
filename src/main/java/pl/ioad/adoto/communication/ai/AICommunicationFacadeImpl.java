package pl.ioad.adoto.communication.ai;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.ioad.adoto.communication.ai.model.AiResult;
import pl.ioad.adoto.communication.ai.service.AiAPIService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AICommunicationFacadeImpl implements AICommunicationFacade {

    private final AiAPIService aiAPIService;
    @Override
    public List<List<AiResult>> getAiResults(double width, double minx, double miny, double maxx, double maxy, String layer) {
        return aiAPIService.getAiResult(width, minx, miny, maxx, maxy, layer);
    }
}
