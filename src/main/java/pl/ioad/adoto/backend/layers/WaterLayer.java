package pl.ioad.adoto.backend.layers;

import org.springframework.stereotype.Component;

@Component("WATER")
public class WaterLayer implements Layer {
    @Override
    public String getLayersSpell() {
        return "WPow";
    }

    @Override
    public String getAiSpell() {
        return "water";
    }
}
