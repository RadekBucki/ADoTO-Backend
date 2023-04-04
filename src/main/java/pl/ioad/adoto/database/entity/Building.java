package pl.ioad.adoto.database.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Geometry;


@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "buildings")
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ogc_fid")
    private Integer id;

    @JsonIgnore
    @Column(name = "wkb_geometry", columnDefinition = "geometry")
    private Geometry geometry;

}
