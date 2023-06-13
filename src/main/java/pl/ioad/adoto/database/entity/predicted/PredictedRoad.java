package pl.ioad.adoto.database.entity.predicted;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pl.ioad.adoto.database.entity.TopEntity;

@Entity
@Table(name = "predicted_roads")
@DiscriminatorColumn(name = "PredictedRoads")
@Getter
@Setter
public class PredictedRoad extends TopEntity {
}
