package pl.ioad.adoto.communication.ai;

import pl.ioad.adoto.communication.ai.model.AiResult;

import java.util.List;

public interface AICommunicationFacade {

    List<List<AiResult>> getAiResults(double width, double minx, double miny, double maxx, double maxy, String layer);
}
