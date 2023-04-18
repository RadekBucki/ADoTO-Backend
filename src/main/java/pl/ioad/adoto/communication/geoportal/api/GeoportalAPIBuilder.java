package pl.ioad.adoto.communication.geoportal.api;

import org.springframework.stereotype.Component;

@Component
public class GeoportalAPIBuilder extends RetrofitAPI {

    private GeoportalAPIBuilder() {
        super();
    }

    public static GeoportalAPI build() {
        return api().create(GeoportalAPI.class);
    }
}
