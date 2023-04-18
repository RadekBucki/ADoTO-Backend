package pl.ioad.adoto.communication.geoportal.api;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.stereotype.Component;
import retrofit2.Retrofit;
import retrofit2.converter.jaxb.JaxbConverterFactory;

import java.util.concurrent.TimeUnit;

@Component
public class BDot10kAPIBuilder {

    private BDot10kAPIBuilder() {
    }

    public static BDot10kAPI build() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://mapy.geoportal.gov.pl")
                .client(new OkHttpClient.Builder()
                        .connectTimeout(300, TimeUnit.SECONDS)
                        .readTimeout(300,TimeUnit.SECONDS)
                        .addInterceptor(chain -> {
                            Request requestBuilder = chain.request().newBuilder()
                                    .addHeader("Accept", "application/json")
                                    .build();
                            return chain.proceed(requestBuilder);
                        })
                        .build())
                .addConverterFactory(JaxbConverterFactory.create())
                .build();
        return retrofit.create(BDot10kAPI.class);
    }
}
