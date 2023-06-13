package pl.ioad.adoto.database.entity.predicted;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import pl.ioad.adoto.database.entity.TopEntity;

@Entity
@Table(name = "predicted_buildings")
@DiscriminatorColumn(name = "PredictedBuildings")
@Getter
@Setter
public class PredictedBuilding extends TopEntity {
}
