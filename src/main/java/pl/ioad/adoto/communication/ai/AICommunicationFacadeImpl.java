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
    public List<List<AiResult>> getAiResults(double width, String layer, String base64Image) {
        return aiAPIService.getAiResult(width, layer, base64Image);
    }
}
