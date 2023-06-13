package pl.ioad.adoto.communication.ai.api;

import org.springframework.stereotype.Component;

@Component
public class AiAPIBuilder implements RetrofitAiAPI {
    private AiAPIBuilder() {
        super();
    }

    public static AiAPI build() {
        return RetrofitAiAPI.api().create(AiAPI.class);
    }
}
