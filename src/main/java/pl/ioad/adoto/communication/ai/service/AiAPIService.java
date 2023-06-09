package pl.ioad.adoto.communication.ai.service;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import pl.ioad.adoto.communication.ai.api.AiAPI;
import pl.ioad.adoto.communication.ai.api.AiAPIBuilder;
import pl.ioad.adoto.communication.ai.exception.AiResponseFailedException;
import pl.ioad.adoto.communication.ai.model.AiResult;

import java.io.IOException;
import java.util.List;

@Service
public class AiAPIService {
    private final AiAPI aiAPI;

    public AiAPIService(Environment environment) {

        this.aiAPI = AiAPIBuilder.build(environment.getProperty("AI_API_URL"));
    }

    public List<List<AiResult>> getAiResult(
            double width,
            String layer,
            String base64Image
    ) {
        try {
            return aiAPI.getAiResults(width, layer, base64Image).execute().body();
        } catch (IOException e) {
            throw new AiResponseFailedException("Failed to get AI results");
        }
    }
}
