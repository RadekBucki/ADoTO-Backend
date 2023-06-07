package pl.ioad.adoto.backend.geoportal.logic.service.layers;

import org.springframework.stereotype.Component;

@Component("ROAD")
public class RoadLayer implements Layer {
    @Override
    public String getLayersSpell() {
        return "JAu,JDrEk,DrEk,JDrG,JDrZTw,JDLNTw,JDrLNUt,DrLGr,DrDGr";
    }
}
