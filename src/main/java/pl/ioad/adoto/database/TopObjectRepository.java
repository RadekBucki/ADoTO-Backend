package pl.ioad.adoto.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.ioad.adoto.database.entity.TopEntity;

import java.util.List;


@Repository
public interface TopObjectRepository<T extends TopEntity> extends JpaRepository<T, Long> {
    @Query(value = "SELECT * FROM #{#entityName.toLowerCase() + 's'} " +
            "WHERE ST_Intersects(wkb_geometry, ST_MakeEnvelope(:minX, :minY, :maxX, :maxY, 4326))",
            nativeQuery = true)
    List<T> findIntersectingEntities(
            @Param("minX") double x1, @Param("minY") double y1,
            @Param("maxX") double x2, @Param("maxY") double y2);
}
