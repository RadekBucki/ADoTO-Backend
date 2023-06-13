package pl.ioad.adoto.database.entity.sample;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import pl.ioad.adoto.database.entity.TopEntity;

@Entity
@Table(name = "roads")
@DiscriminatorValue("Road")
public class Road extends TopEntity {
}
