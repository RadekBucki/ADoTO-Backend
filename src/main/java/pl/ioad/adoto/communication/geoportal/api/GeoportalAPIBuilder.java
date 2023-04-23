package pl.ioad.adoto.communication.geoportal.api;

import org.springframework.stereotype.Component;

@Component
public class GeoportalAPIBuilder implements RetrofitAPI {

    private GeoportalAPIBuilder() {
        super();
    }

    public static GeoportalAPI build() {
        return RetrofitAPI.api().create(GeoportalAPI.class);
    }
}
