package pl.ioad.adoto.database.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BuildingDTO {
    private Integer id;
    private GeometryDTO geometry;
}
