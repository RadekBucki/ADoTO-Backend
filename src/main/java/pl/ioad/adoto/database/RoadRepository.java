package pl.ioad.adoto.database;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ioad.adoto.database.entity.Road;

public interface RoadRepository extends JpaRepository<Road, Long> {
}
