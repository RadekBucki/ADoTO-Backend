package pl.ioad.adoto.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.ioad.adoto.database.entity.sample.Building;
import pl.ioad.adoto.database.entity.sample.River;
import pl.ioad.adoto.database.entity.TopEntity;

import java.util.List;


@Repository
public interface TopObjectRepository<T extends TopEntity> extends JpaRepository<T, Long> {

    String INTERSECTING_BUILDINGS_QUERY_WITH_CRS84 = "SELECT * FROM buildings WHERE ST_Intersects(wkb_geometry, ST_MakeEnvelope(:minX, :minY, :maxX, :maxY, 4326))";
    String INTERSECTING_BUILDINGS_QUERY_WITH_EPSG2180 = "SELECT * FROM buildings WHERE ST_Intersects(wkb_geometry, ST_Transform(ST_MakeEnvelope(:minX, :minY, :maxX, :maxY, 2180), 4326))";

    String INTERSECTING_RIVERS_QUERY_WITH_CRS84 = "SELECT * FROM rivers WHERE ST_Intersects(wkb_geometry, ST_MakeEnvelope(:minX, :minY, :maxX, :maxY, 4326))";
    String INTERSECTING_RIVERS_QUERY_WITH_EPSG2180 = "SELECT * FROM rivers WHERE ST_Intersects(wkb_geometry, ST_Transform(ST_MakeEnvelope(:minX, :minY, :maxX, :maxY, 2180), 4326))";

    String INTERSECTING_ROADS_QUERY_WITH_CRS84 = "SELECT * FROM roads WHERE ST_Intersects(wkb_geometry, ST_MakeEnvelope(:minX, :minY, :maxX, :maxY, 4326))";
    String INTERSECTING_ROADS_QUERY_WITH_EPSG2180 = "SELECT * FROM roads WHERE ST_Intersects(wkb_geometry, ST_Transform(ST_MakeEnvelope(:minX, :minY, :maxX, :maxY, 2180), 4326))";

    String INTERSECTING_FORESTS_QUERY_WITH_CRS84 = "SELECT * FROM forests WHERE ST_Intersects(wkb_geometry, ST_MakeEnvelope(:minX, :minY, :maxX, :maxY, 4326))";
    String INTERSECTING_FORESTS_QUERY_WITH_EPSG2180 = "SELECT * FROM forests WHERE ST_Intersects(wkb_geometry, ST_Transform(ST_MakeEnvelope(:minX, :minY, :maxX, :maxY, 2180), 4326))";

    @Query(value = INTERSECTING_BUILDINGS_QUERY_WITH_CRS84, nativeQuery = true)
    List<Building> findIntersectingBuildingsInCRS84(
            @Param("minX") double x1, @Param("minY") double y1,
            @Param("maxX") double x2, @Param("maxY") double y2);

    @Query(value = INTERSECTING_BUILDINGS_QUERY_WITH_EPSG2180, nativeQuery = true)
    List<Building> findIntersectingBuildingsInEPSG2180(
            @Param("minX") double x1, @Param("minY") double y1,
            @Param("maxX") double x2, @Param("maxY") double y2);


    @Query(value = INTERSECTING_RIVERS_QUERY_WITH_CRS84, nativeQuery = true)
    List<River> findIntersectingRiversInCRS84(
            @Param("minX") double x1, @Param("minY") double y1,
            @Param("maxX") double x2, @Param("maxY") double y2);

    @Query(value = INTERSECTING_RIVERS_QUERY_WITH_EPSG2180, nativeQuery = true)
    List<Building> findIntersectingRiversInEPSG2180(
            @Param("minX") double x1, @Param("minY") double y1,
            @Param("maxX") double x2, @Param("maxY") double y2);


    @Query(value = INTERSECTING_ROADS_QUERY_WITH_CRS84, nativeQuery = true)
    List<River> findIntersectingRoadsInCRS84(
            @Param("minX") double x1, @Param("minY") double y1,
            @Param("maxX") double x2, @Param("maxY") double y2);

    @Query(value = INTERSECTING_ROADS_QUERY_WITH_EPSG2180, nativeQuery = true)
    List<Building> findIntersectingRoadsInEPSG2180(
            @Param("minX") double x1, @Param("minY") double y1,
            @Param("maxX") double x2, @Param("maxY") double y2);


    @Query(value = INTERSECTING_FORESTS_QUERY_WITH_CRS84, nativeQuery = true)
    List<River> findIntersectingForestsInCRS84(
            @Param("minX") double x1, @Param("minY") double y1,
            @Param("maxX") double x2, @Param("maxY") double y2);
    @Query(value = INTERSECTING_FORESTS_QUERY_WITH_EPSG2180, nativeQuery = true)
    List<Building> findIntersectingForestsInEPSG2180(
            @Param("minX") double x1, @Param("minY") double y1,
            @Param("maxX") double x2, @Param("maxY") double y2);

}
