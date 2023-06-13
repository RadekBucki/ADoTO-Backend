package pl.ioad.adoto.backend.layers;

import org.springframework.stereotype.Component;

@Component("ROAD")
public class RoadLayer implements Layer {
    @Override
    public String getLayersSpell() {
        return "JAu,JDrEk,DrEk,JDrG,JDrZTw,JDLNTw,JDrLNUt,DrLGr,DrDGr";
    }

    @Override
    public String getAiSpell() {
        return "roads";
    }
}
