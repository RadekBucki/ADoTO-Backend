package pl.ioad.adoto.communication.ai.api;

import pl.ioad.adoto.communication.ai.model.AiResult;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

import java.util.List;

public interface AiAPI {
    @POST("/photo")
    Call<List<List<AiResult>>> getAiResults(@Query("width") double width,
                                            @Query("model") String layer,
                                            @Body String base64Image
    );
}
