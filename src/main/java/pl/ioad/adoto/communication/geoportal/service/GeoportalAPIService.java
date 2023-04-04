package pl.ioad.adoto.communication.geoportal.service;

import okhttp3.ResponseBody;
import org.springframework.stereotype.Service;
import pl.ioad.adoto.communication.geoportal.api.GeoportalAPI;
import pl.ioad.adoto.communication.geoportal.api.GeoportalAPIBuilder;
import pl.ioad.adoto.communication.geoportal.exception.GeoportalTimeoutException;
import pl.ioad.adoto.communication.geoportal.exception.WrongInputDataException;
import pl.ioad.adoto.communication.geoportal.model.SatelliteImage;
import retrofit2.Response;

import java.io.IOException;
import java.util.Base64;

@Service
public class GeoportalAPIService {

    private final GeoportalAPI geoportalAPI = GeoportalAPIBuilder.build();

    public SatelliteImage getSatelliteImage(double height, double width, double minx, double miny, double maxx, double maxy) {
        if (((maxx - minx) / (maxy - miny)) != height / width) {
            throw new WrongInputDataException("BBOX and width/height ratio is different!");
        }
        try {
            Response<ResponseBody> response = geoportalAPI.getSatelliteImage(
                    "WMS",
                    "GetMap",
                    "image/png",
                    "1.3",
                    "Raster",
                    "default",
                    minx + "," + miny + "," + maxx + "," + maxy,
                    "EPSG:2180",
                    String.valueOf(width),
                    String.valueOf(height)
            ).execute();
            String base64Image = null;
            if (response.isSuccessful() && response.body() != null) {
                base64Image = Base64.getEncoder().encodeToString(response.body().bytes());
            }
            return SatelliteImage.builder()
                    .base64(base64Image)
                    .build();
        } catch (IOException e) {
            throw new GeoportalTimeoutException(e.getMessage());
        }
    }
}
