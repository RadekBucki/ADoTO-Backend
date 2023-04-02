package pl.ioad.adoto.geoportal_communication;

import org.springframework.stereotype.Component;
import pl.ioad.adoto.geoportal_communication.api.ApiBuilder;
import pl.ioad.adoto.geoportal_communication.api.GeoportalAPI;

import java.io.IOException;
import java.util.Base64;

@Component
public class GeoportalCommunicationFacadeImpl implements GeoportalCommunicationFacade{
    @Override
    public void test() {
        GeoportalAPI geoportalAPI = ApiBuilder.build();
        try {
            String base64Image = Base64.getEncoder().encodeToString(geoportalAPI.getSatelliteImage(
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
            ).execute().body().bytes());
            System.out.println(base64Image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
