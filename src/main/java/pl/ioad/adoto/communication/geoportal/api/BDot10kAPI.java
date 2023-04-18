package pl.ioad.adoto.communication.geoportal.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BDot10kAPI {

    @GET("/wss/service/pub/guest/kompozycja_BDOT10k_WMS/MapServer/WMSServer")
    Call<ResponseBody> getSvgObjects(@Query("SERVICE") String service,
                                     @Query("REQUEST") String request,
                                     @Query("FORMAT") String format,
                                     @Query("VERSION") String version,
                                     @Query("LAYERS") String layers,
                                     @Query("STYLES") String styles,
                                     @Query("BBOX") String bbox,
                                     @Query("CRS") String crs,
                                     @Query("WIDTH") String width,
                                     @Query("HEIGHT") String height
    );
}
