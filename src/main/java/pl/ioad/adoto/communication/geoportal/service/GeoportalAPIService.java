package pl.ioad.adoto.communication.geoportal.service;

import lombok.AllArgsConstructor;
import okhttp3.ResponseBody;
import org.springframework.stereotype.Service;
import pl.ioad.adoto.backend.coordinates.converter.CoordinatesConverter;
import pl.ioad.adoto.communication.geoportal.api.GeoportalAPI;
import pl.ioad.adoto.communication.geoportal.api.GeoportalAPIBuilder;
import pl.ioad.adoto.communication.geoportal.exception.GeoportalTimeoutException;
import pl.ioad.adoto.communication.geoportal.exception.ResponseFailedException;
import pl.ioad.adoto.communication.geoportal.exception.WrongInputDataException;
import pl.ioad.adoto.communication.geoportal.model.SatelliteImage;
import retrofit2.Response;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
@AllArgsConstructor
public class GeoportalAPIService {

    private final GeoportalAPI geoportalAPI = GeoportalAPIBuilder.build();

    public List<SatelliteImage> getSatelliteImagesCropped(double width, double heightResult, double widthResult,
                                                          double minx, double miny, double maxx, double maxy) {
        double height = width * ((maxx - minx) / (maxy - miny));
        if (heightResult == 0) {
            heightResult = height;
        }
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
            if (!response.isSuccessful() || response.body() == null) {
                throw new ResponseFailedException("Response failed!");
            }
            InputStream is = new ByteArrayInputStream(response.body().bytes());
            BufferedImage image = ImageIO.read(is);
            List<SatelliteImage> satelliteImages = new ArrayList<>();
            for (int i = 0; i <= (int) (width - widthResult); i += (int) (widthResult / 2)) {
                for (int j = 0; j <= (int) (height - heightResult); j += (int) (heightResult / 2)) {
                    BufferedImage subImage = image.getSubimage(i, j, (int) widthResult, (int) heightResult);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(subImage, "jpg", baos);
                    satelliteImages.add(
                            SatelliteImage.builder()
                                    .base64(Base64.getEncoder().encodeToString(baos.toByteArray()))
                                    .build()
                    );
                }
            }
            return satelliteImages;
        } catch (IOException e) {
            throw new GeoportalTimeoutException(e.getMessage());
        }
    }
}
