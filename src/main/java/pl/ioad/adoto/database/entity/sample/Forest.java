package pl.ioad.adoto.database.entity.sample;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import pl.ioad.adoto.database.entity.TopEntity;

@Entity
@Table(name = "forests")
@DiscriminatorValue("Forest")
public class Forest extends TopEntity {
}
