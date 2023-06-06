package pl.ioad.adoto.backend.geoportal.logic.service.layers;

import org.springframework.stereotype.Component;

@Component("FOREST")
public class ForestLayer implements Layer {
    @Override
    public String getLayersSpell() {
        return "TLes";
    }
}
