package pl.ioad.adoto.communication.ai.service;

import org.springframework.stereotype.Service;
import pl.ioad.adoto.communication.ai.api.AiAPI;
import pl.ioad.adoto.communication.ai.api.AiAPIBuilder;
import pl.ioad.adoto.communication.ai.exception.AiResponseFailedException;
import pl.ioad.adoto.communication.ai.model.AiResult;

import java.io.IOException;
import java.util.List;

@Service
public class AiAPIService {

    private final AiAPI aiAPI = AiAPIBuilder.build();

    public List<List<AiResult>> getAiResult(
            double width,
            double minx,
            double miny,
            double maxx,
            double maxy,
            String layer
    ) {
        try {
            return aiAPI.getAiResults(width, minx, miny, maxx, maxy, layer).execute().body();
        } catch (IOException e) {
            throw new AiResponseFailedException("Failed to get AI results");
        }
    }
}
