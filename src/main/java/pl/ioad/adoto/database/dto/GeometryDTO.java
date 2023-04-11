package pl.ioad.adoto.database.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public record GeometryDTO (double[][] coordinates, String type) { }
