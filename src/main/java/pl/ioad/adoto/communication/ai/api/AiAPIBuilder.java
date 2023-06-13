package pl.ioad.adoto.communication.ai.api;

import org.springframework.stereotype.Component;

@Component
public class AiAPIBuilder implements RetrofitAPI {
    private AiAPIBuilder() {
        super();
    }

    public static AiAPI build() {
        return RetrofitAPI.api().create(AiAPI.class);
    }
}
