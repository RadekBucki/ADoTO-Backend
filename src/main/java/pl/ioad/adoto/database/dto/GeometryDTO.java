package pl.ioad.adoto.database.dto;

import org.springframework.data.util.Pair;

import java.util.List;

public record GeometryDTO(List<Pair<Double, Double>> coordinates, String type) {
}
