package pl.ioad.adoto.backend.layers;

import org.springframework.stereotype.Component;

@Component("FOREST")
public class ForestLayer implements Layer {
    @Override
    public String getLayersSpell() {
        return "TLes";
    }

    @Override
    public String getAiSpell() {
        return "forest";
    }
}
