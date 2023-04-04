package pl.ioad.adoto.geoportal_communication.service;

import okhttp3.ResponseBody;
import org.springframework.stereotype.Service;
import pl.ioad.adoto.geoportal_communication.api.GeoportalAPIBuilder;
import pl.ioad.adoto.geoportal_communication.api.GeoportalAPI;
import pl.ioad.adoto.geoportal_communication.exception.GeoportalTimeoutException;
import pl.ioad.adoto.geoportal_communication.exception.WrongInputDataException;
import pl.ioad.adoto.geoportal_communication.model.SatelliteImage;
import retrofit2.Response;

import java.io.IOException;
import java.util.Base64;

@Service
public class GeoportalService {

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
            return new SatelliteImage(
                    base64Image
            );
        } catch (IOException e) {
            throw new GeoportalTimeoutException(e.getMessage());
        }
    }
}
