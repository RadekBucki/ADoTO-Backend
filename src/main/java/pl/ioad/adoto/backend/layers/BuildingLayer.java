package pl.ioad.adoto.backend.layers;

import org.springframework.stereotype.Component;

@Component("BUILDING")
public class BuildingLayer implements Layer {
    @Override
    public String getLayersSpell() {
        return "BudMW,BudMJ,BudMWy,BudU,BudUWy,BudPWy,BudP2,BudP1,BudGo,BudZr,SwCh,SwNch,Kap,Szkl,BudSp,BudCm";
    }

    @Override
    public String getAiSpell() {
        return "house";
    }
}
