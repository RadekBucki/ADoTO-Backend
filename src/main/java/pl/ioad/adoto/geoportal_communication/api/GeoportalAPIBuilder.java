package pl.ioad.adoto.geoportal_communication.api;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.stereotype.Component;
import retrofit2.Retrofit;
import retrofit2.converter.jaxb.JaxbConverterFactory;

@Component
public class GeoportalAPIBuilder {

    private GeoportalAPIBuilder() {
    }

    public static GeoportalAPI build() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://mapy.geoportal.gov.pl")
                .client(new OkHttpClient.Builder()
                        .addInterceptor(chain -> {
                            Request requestBuilder = chain.request().newBuilder()
                                    .addHeader("Accept", "application/json")
                                    .build();
                            return chain.proceed(requestBuilder);
                        })
                        .build())
                .addConverterFactory(JaxbConverterFactory.create())
                .build();
        return retrofit.create(GeoportalAPI.class);
    }
}
