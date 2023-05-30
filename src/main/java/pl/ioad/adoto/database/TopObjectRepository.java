package pl.ioad.adoto.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.ioad.adoto.database.entity.Building;
import pl.ioad.adoto.database.entity.Field;
import pl.ioad.adoto.database.entity.Forest;
import pl.ioad.adoto.database.entity.River;
import pl.ioad.adoto.database.entity.Road;
import pl.ioad.adoto.database.entity.TopEntity;

import java.util.List;


@Repository
public interface TopObjectRepository<T extends TopEntity> extends JpaRepository<T, Long> {

    String INTERSECTING_BUILDINGS_QUERY = "SELECT * FROM buildings WHERE ST_Intersects(wkb_geometry, ST_MakeEnvelope(:minX, :minY, :maxX, :maxY, 4326))";
    String INTERSECTING_RIVERS_QUERY = "SELECT * FROM rivers WHERE ST_Intersects(wkb_geometry, ST_MakeEnvelope(:minX, :minY, :maxX, :maxY, 4326))";
    String INTERSECTING_ROADS_QUERY = "SELECT * FROM roads WHERE ST_Intersects(wkb_geometry, ST_MakeEnvelope(:minX, :minY, :maxX, :maxY, 4326))";
    String INTERSECTING_FORESTS_QUERY = "SELECT * FROM forests WHERE ST_Intersects(wkb_geometry, ST_MakeEnvelope(:minX, :minY, :maxX, :maxY, 4326))";
    String INTERSECTING_FIELDS_QUERY = "SELECT * FROM fields WHERE ST_Intersects(wkb_geometry, ST_MakeEnvelope(:minX, :minY, :maxX, :maxY, 4326))";

    @Query(value = INTERSECTING_BUILDINGS_QUERY, nativeQuery = true)
    List<Building> findIntersectingBuildings(
            @Param("minX") double x1, @Param("minY") double y1,
            @Param("maxX") double x2, @Param("maxY") double y2);

    @Query(value = INTERSECTING_RIVERS_QUERY, nativeQuery = true)
    List<River> findIntersectingRivers(
            @Param("minX") double x1, @Param("minY") double y1,
            @Param("maxX") double x2, @Param("maxY") double y2);

    @Query(value = INTERSECTING_ROADS_QUERY, nativeQuery = true)
    List<Road> findIntersectingRoads(
            @Param("minX") double x1, @Param("minY") double y1,
            @Param("maxX") double x2, @Param("maxY") double y2);

    @Query(value = INTERSECTING_FORESTS_QUERY, nativeQuery = true)
    List<Forest> findIntersectingForests(
            @Param("minX") double x1, @Param("minY") double y1,
            @Param("maxX") double x2, @Param("maxY") double y2);

    @Query(value = INTERSECTING_FIELDS_QUERY, nativeQuery = true)
    List<Field> findIntersectingFields(
            @Param("minX") double x1, @Param("minY") double y1,
            @Param("maxX") double x2, @Param("maxY") double y2);
}
