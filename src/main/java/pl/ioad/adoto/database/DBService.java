package pl.ioad.adoto.database;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.ioad.adoto.database.dto.TopObjectDTO;
import pl.ioad.adoto.database.entity.Building;
import pl.ioad.adoto.database.entity.EntitiesType;
import pl.ioad.adoto.database.entity.Field;
import pl.ioad.adoto.database.entity.Forest;
import pl.ioad.adoto.database.entity.River;
import pl.ioad.adoto.database.entity.Road;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static pl.ioad.adoto.database.dto.GeometryMapper.mapObjectToDto;

@RequiredArgsConstructor
@Service
public class DBService {

    private final TopObjectRepository<Building> buildingsRepository;
    private final TopObjectRepository<River> riversRepository;
    private final TopObjectRepository<Road> roadsRepository;
    private final TopObjectRepository<Forest> forestsRepository;
    private final TopObjectRepository<Field> fieldsRepository;

    public List<TopObjectDTO> findAllInBoundingBox(EntitiesType entitiesType,
                                                   Double minX,
                                                   Double minY,
                                                   Double maxX,
                                                   Double maxY) {

        downloadGeometries(minX, minY, maxX, maxY);

        var intersectingEntities = switch (entitiesType) {
            case BUILDINGS -> buildingsRepository.findIntersectingBuildings(minX, minY, maxX, maxY);
            case RIVERS -> riversRepository.findIntersectingRivers(minX, minY, maxX, maxY);
            case ROADS -> roadsRepository.findIntersectingRoads(minX, minY, maxX, maxY);
            case FORESTS -> forestsRepository.findIntersectingForests(minX, minY, maxX, maxY);
            case FIELDS -> fieldsRepository.findIntersectingFields(minX, minY, maxX, maxY);
        };

        return intersectingEntities.stream()
                .map(entity -> new TopObjectDTO(entity.getId(), mapObjectToDto(entity.getGeometry())))
                .toList();
    }

    private void downloadGeometries(Double minX, Double minY, Double maxX, Double maxY) {

        // Specify the URL endpoint for the GET request
        String url = "https://capap.gugik.gov.pl/api/fts/ref/qn";

        try {
            // Construct the query parameters
            String params = String.format("f=geojson&bbox=%f,%f,%f,%f",
                    minX, minY, maxX, maxY);

            // Append the query parameters to the URL
            URL fullUrl = new URL(url + "?" + params);

            // Create the HttpURLConnection object
            HttpURLConnection connection = (HttpURLConnection) fullUrl.openConnection();

            // Set the request method to GET
            connection.setRequestMethod("GET");

            // Get the response code
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // Read the response from the API
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();

            // Print the response
            System.out.println("Response: " + response.toString());

            // Close the connection
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        try {
//            var url = new URL("https://capap.gugik.gov.pl/api/fts/ref/qn");
//            var con = (HttpURLConnection) url.openConnection();
//            con.setRequestMethod("GET");
//            con.setRequestProperty("Content-Type", "application/json");
//
//            var parameters = new HashMap<String, String>();
//            parameters.put("bbox", String.join(",",
//                    String.valueOf(minX),
//                    String.valueOf(minY),
//                    String.valueOf(maxX),
//                    String.valueOf(maxY)));
//            parameters.put("f", "geojson");
//
//            con.setDoOutput(true);
//            var out = new DataOutputStream(con.getOutputStream());
//            out.writeBytes(getParamsString(parameters));
//            out.flush();
//            out.close();
//
//            BufferedReader in = new BufferedReader(
//                    new InputStreamReader(con.getInputStream()));
//            String inputLine;
//            StringBuffer content = new StringBuffer();
//            while ((inputLine = in.readLine()) != null) {
//                content.append(inputLine);
//            }
//            in.close();
//            con.disconnect();
//
//            System.out.println(content);
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

    }

    private static String getParamsString(Map<String, String> params) {
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            result.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8));
            result.append("&");
        }

        String resultString = result.toString();
        return resultString.length() > 0
                ? resultString.substring(0, resultString.length() - 1)
                : resultString;
    }
}

