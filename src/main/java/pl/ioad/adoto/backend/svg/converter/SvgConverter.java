package pl.ioad.adoto.backend.svg.converter;

import org.springframework.stereotype.Component;
import pl.ioad.adoto.backend.svg.converter.model.SvgConvertResponse;
import pl.ioad.adoto.communication.geoportal.model.SvgObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class SvgConverter {

    public List<List<SvgConvertResponse>> getCoordinates(List<SvgObject> svgObjects) {
        return svgObjects.parallelStream().map((this::getCoordinatesOfSpecificObject)).toList();
    }

    private List<SvgConvertResponse> getCoordinatesOfSpecificObject(SvgObject svgObject) {
        String transform = svgObject.transform().substring(7, svgObject.transform().length() - 1);
        String[] coefficientsArray = transform.split("\\s");
        List<BigDecimal> coefficientsList = new ArrayList<>();

        for (String s : coefficientsArray) {
            coefficientsList.add(new BigDecimal(s));
        }

        List<BigDecimal> coordinates = new ArrayList<>();
        List<String> d = svgObject.d();

        for (int i = 0; i < d.size() - 1; i++) {
            String result = d.get(i);
            if (i % 2 == 0) {
                result = d.get(i).substring(1, d.get(i).length() - 1);
            }
            coordinates.add(new BigDecimal(result));
        }

        List<SvgConvertResponse> response = new ArrayList<>();

        for (int i = 0; i < coordinates.size(); i += 2) {
            BigDecimal x = coordinates.get(i);
            x = x.multiply(coefficientsList.get(0));
            x = x.add(coefficientsList.get(4));

            BigDecimal y = coordinates.get(i + 1);
            y = y.multiply(coefficientsList.get(3));
            y = y.add(coefficientsList.get(5));

            response.add(new SvgConvertResponse(x, y));
        }

        return response;
    }
}
