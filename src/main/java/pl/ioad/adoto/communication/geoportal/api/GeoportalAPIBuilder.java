package pl.ioad.adoto.communication.geoportal.api;

import org.springframework.stereotype.Component;

@Component
public class GeoportalAPIBuilder implements RetrofitAiAPI {

    private GeoportalAPIBuilder() {
        super();
    }

    public static GeoportalAPI build() {
        return RetrofitAiAPI.api().create(GeoportalAPI.class);
    }
}
