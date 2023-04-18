package pl.ioad.adoto.communication.geoportal.service;

import okhttp3.ResponseBody;
import org.springframework.stereotype.Service;
import pl.ioad.adoto.communication.geoportal.api.BDot10kAPI;
import pl.ioad.adoto.communication.geoportal.api.BDot10kAPIBuilder;
import pl.ioad.adoto.communication.geoportal.exception.GeoportalTimeoutException;
import pl.ioad.adoto.communication.geoportal.exception.ResponseFailedException;
import pl.ioad.adoto.communication.geoportal.exception.WrongInputDataException;
import pl.ioad.adoto.communication.geoportal.model.SvgObject;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class BDot10kAPIService {

    private final BDot10kAPI bDot10kAPI = BDot10kAPIBuilder.build();

    public List<SvgObject> getSvgObjects(double height, double width,
                                         double minx, double miny, double maxx, double maxy,
                                         String layer) {
        if (((maxx - minx) / (maxy - miny)) != height / width) {
            throw new WrongInputDataException("BBOX and width/height ratio is different!");
        }
        try {
            Response<ResponseBody> response = bDot10kAPI.getSvgObjects(
                    "WMS",
                    "GetMap",
                    "image/svg+xml",
                    "1.3",
                    layer,
                    "default",
                    minx + "," + miny + "," + maxx + "," + maxy,
                    "EPSG:2180",
                    String.valueOf(width),
                    String.valueOf(height)
            ).execute();
            if (!response.isSuccessful() || response.body() == null) {
                throw new ResponseFailedException("Response failed!");
            }

            String[] split = response.body().string().split("<g id");
            int index = split[2].indexOf("\n");
            String filterMes = split[2].substring(index+1);
            List<String> elements = Arrays.stream(filterMes.split("<g\\s"))
                    .filter(x -> !x.isEmpty())
                    .toList();
            List<SvgObject> objects = new ArrayList<>();

            for (String element : elements) {
                objects.add(SvgObject.builder()
                        .transform(getAttributeValue(element, "transform"))
                        .d(Arrays.stream(getAttributeValue(element, "d")
                                .split("\\s"))
                                .toList())
                        .build());
            }

            return objects;
        } catch (IOException e) {
            throw new GeoportalTimeoutException(e.getMessage());
        }
    }

    public String getAttributeValue(String input, String attributeName) {
        String patternString = attributeName + "=\"(.*?)\"";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return null;
        }
    }
}
