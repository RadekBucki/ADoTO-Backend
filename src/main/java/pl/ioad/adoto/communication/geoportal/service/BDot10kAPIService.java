package pl.ioad.adoto.communication.geoportal.service;

import okhttp3.ResponseBody;
import org.apache.commons.lang3.StringUtils;
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
        StringBuilder style = new StringBuilder();
        int howMany = StringUtils.countMatches(layer, ",");
        style.append("default,".repeat(Math.max(1, howMany + 1)));
        style.deleteCharAt(style.length() - 1);
        try {
            Response<ResponseBody> response = bDot10kAPI.getSvgObjects(
                    "WMS",
                    "GetMap",
                    "image/svg+xml",
                    "1.3",
                    layer,
                    style.toString(),
                    minx + "," + miny + "," + maxx + "," + maxy,
                    "EPSG:2180",
                    String.valueOf(width),
                    String.valueOf(height)
            ).execute();
            if (!response.isSuccessful() || response.body() == null) {
                throw new ResponseFailedException("Response failed!");
            }

            String body = response.body().string();
            String[] split = body.split("<g id");
            List<String> elements = Arrays.asList(split);
            elements = elements.stream().filter(x -> x.contains("path")).toList();

            List<String> splitElements = new ArrayList<>();
            for (String elem : elements) {
                splitElements.addAll(List.of(elem.split("<g\\s")));
            }
            splitElements = splitElements.stream().filter(x -> !x.isEmpty()).toList();
            List<SvgObject> objects = new ArrayList<>();

            for (String element : splitElements) {
                try {
                    objects.add(SvgObject.builder()
                            .transform(getAttributeValue(element, "transform"))
                            .d(Arrays.stream(getAttributeValue(element, "d")
                                            .split("\\s"))
                                    .toList())
                            .build());
                } catch (Exception ignored) {
                    // ignored
                }
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
