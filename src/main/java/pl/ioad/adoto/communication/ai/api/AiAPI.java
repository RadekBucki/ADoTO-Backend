package pl.ioad.adoto.communication.ai.api;

import pl.ioad.adoto.communication.ai.model.AiResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

public interface AiAPI {
    @GET("/photo")
    Call<List<List<AiResult>>> getAiResults(@Query("width") double width,
                                            @Query("minx") double minx,
                                            @Query("miny") double miny,
                                            @Query("maxx") double maxx,
                                            @Query("maxy") double maxy,
                                            @Query("model") String layer
    );
}
