package pl.ioad.adoto.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.ioad.adoto.database.entity.Building;

import java.util.List;


@Repository
public interface TopObjectRepository extends JpaRepository<Building, Long> {
    @Query(value = "SELECT * FROM first " +
            "WHERE ST_Intersects(wkb_geometry, ST_MakeEnvelope(:minX, :minY, :maxX, :maxY, :srId))",
            nativeQuery = true)
    List<Building> findIntersectingEntities(@Param("minX") double x1, @Param("minY") double y1,
                                            @Param("maxX") double x2, @Param("maxY") double y2,
                                            @Param("srId") int srId);

}
