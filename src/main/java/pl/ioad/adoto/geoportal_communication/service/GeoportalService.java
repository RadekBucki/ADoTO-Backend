package pl.ioad.adoto.geoportal_communication.service;

import okhttp3.ResponseBody;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.ioad.adoto.geoportal_communication.api.ApiBuilder;
import pl.ioad.adoto.geoportal_communication.api.GeoportalAPI;
import pl.ioad.adoto.geoportal_communication.model.SatelliteImage;
import retrofit2.Response;

import java.io.IOException;
import java.util.Base64;

@Service
public class GeoportalService {

    private final GeoportalAPI geoportalAPI = ApiBuilder.build();

    public SatelliteImage getSatelliteImage() {
        String base64Image = null;
        try {
            Response<ResponseBody> response = geoportalAPI.getSatelliteImage(
                    "WMS",
                    "GetMap",
                    "image/png",
                    "1.3",
                    "Raster",
                    "default",
                    "431970,538020,432030,538080",
                    "EPSG:2180",
                    "1000",
                    "1000"
            ).execute();
            if (response.isSuccessful() && response.body() != null) {
                base64Image = Base64.getEncoder().encodeToString(response.body().bytes());
            }
        } catch (IOException ignored) {
            //ignored
        }
        return new SatelliteImage(
                base64Image
        );
    }
}
