package pl.ioad.adoto.database.entity.predicted;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import pl.ioad.adoto.database.entity.TopEntity;

@Entity
@Table(name = "predicted_forests")
@DiscriminatorColumn(name = "PredictedForests")
@Getter
@Setter
public class PredictedForest extends TopEntity {
}
