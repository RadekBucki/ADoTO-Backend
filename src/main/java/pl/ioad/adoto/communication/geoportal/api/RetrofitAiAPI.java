package pl.ioad.adoto.communication.geoportal.api;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.jaxb.JaxbConverterFactory;

import java.util.concurrent.TimeUnit;

public interface RetrofitAiAPI {

    static Retrofit api() {
        return new Retrofit.Builder()
                .baseUrl("https://mapy.geoportal.gov.pl")
                .client(new OkHttpClient.Builder()
                        .connectTimeout(300, TimeUnit.SECONDS)
                        .readTimeout(300, TimeUnit.SECONDS)
                        .addInterceptor(chain -> {
                            Request requestBuilder = chain.request().newBuilder()
                                    .addHeader("Accept", "application/json")
                                    .build();
                            return chain.proceed(requestBuilder);
                        })
                        .build())
                .addConverterFactory(JaxbConverterFactory.create())
                .build();
    }
}
