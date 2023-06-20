package pl.ioad.adoto.communication.ai.api;

import org.springframework.stereotype.Component;

@Component
public class AiAPIBuilder implements RetrofitAiAPI {
    private AiAPIBuilder() {
        super();
    }

    public static AiAPI build(String baseUrl) {
        return RetrofitAiAPI.api(baseUrl).create(AiAPI.class);
    }
}
