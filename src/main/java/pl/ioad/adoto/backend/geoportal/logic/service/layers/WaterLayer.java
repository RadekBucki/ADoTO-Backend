package pl.ioad.adoto.backend.geoportal.logic.service.layers;

import org.springframework.stereotype.Component;

@Component("WATER")
public class WaterLayer implements Layer {
    @Override
    public String getLayersSpell() {
        return "WPow";
    }
}
