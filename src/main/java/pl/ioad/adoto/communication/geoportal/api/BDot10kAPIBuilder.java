package pl.ioad.adoto.communication.geoportal.api;

import org.springframework.stereotype.Component;

@Component
public class BDot10kAPIBuilder implements RetrofitAPI {

    private BDot10kAPIBuilder() {
        super();
    }

    public static BDot10kAPI build() {
        return RetrofitAPI.api().create(BDot10kAPI.class);
    }
}
