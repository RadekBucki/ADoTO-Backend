package pl.ioad.adoto.database.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Geometry;

import java.sql.Date;


@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "first")
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ogc_fid")
    private Integer id;

    @JsonIgnore
    @Column(name = "wkb_geometry", columnDefinition = "geometry")
    private Geometry geometry;

    @Column(name = "teryt")
    private String teryt;

    @Column(name = "lokalnyid")
    private String localId;

    @Column(name = "wersjaid")
    private String idVersion;

    @Column(name = "pnazw")
    private String pName;

    @Column(name = "gmlid")
    private String gmlId;

    @Column(name = "x_kod")
    private String xCode;

    @Column(name = "x_skrkarto")
    private String xSkrkarto;

    @Column(name = "x_skrk_nil")
    private String xSkrkNil;

    @Column(name = "x_katdokg")
    private String xKatDokg;

    @Column(name = "x_doklgeom")
    private Double xDoklGeom;

    @Column(name = "x_zrodlog")
    private String xZdrodlog;

    @Column(name = "x_zrodloa")
    private String xZdroloa;

    @Column(name = "x_katist")
    private String xKatist;

    @Column(name = "x_kati_nil")
    private String xKatiNil;

    @Column(name = "x_rodzrepg")
    private String rodzRepg;

    @Column(name = "x_uwagi")
    private String xUwagi;

    @Column(name = "x_uzytkown")
    private String xUser;

    @Column(name = "x_aktg")
    private Date xAktg;

    @Column(name = "x_akta")
    private Date xAkta;

    @Column(name = "poczwers")
    private Date poczwers;

    @Column(name = "koniecwers")
    private Date koniecwers;

    @Column(name = "x_datautw")
    private Date xDatautw;

    @Column(name = "x_infdod")
    private String xInfDod;

    @Column(name = "kod10k")
    private String kod10k;

    @Column(name = "kod10k_nil")
    private String kod10k_nil;

    @Column(name = "kod25k")
    private String kod25k;

    @Column(name = "kod25k_nil")
    private String kod25k_nil;

    @Column(name = "kod50k")
    private String kod50k;

    @Column(name = "kod50k_nil")
    private String kod50k_nil;

    @Column(name = "kod100k")
    private String kod100k;

    @Column(name = "kod100_nil")
    private String kod100k_nil;

    @Column(name = "kod250k")
    private String kod250k;

    @Column(name = "kod250_nil")
    private String kod250_nil;

    @Column(name = "kod500k")
    private String kod500k;

    @Column(name = "kod500_nil")
    private String kod500_nil;

    @Column(name = "kod1000k")
    private String kod1000k;

    @Column(name = "kod1000_ni")
    private String kod1000k_nil;

    @Column(name = "rodzaj")
    private String rodzaj;

    @Column(name = "rodzaj_nil")
    private String rodzaj_nil;

    @Column(name = "gromadzsub")
    private String gromadzsub;

    @Column(name = "groms_nil")
    private String groms_nil;

    @Column(name = "rodzkonstr")
    private String rodzkonstr;

    @Column(name = "rodzko_nil")
    private String rodzko_nil;

    @Column(name = "bd500_id")
    private String bd500_id;

    @Column(name = "bd500_pna")
    private String bd500_pna;

    @Column(name = "bd500_weid")
    private String bd500_weid;

    @Column(name = "area")
    private String area;
}
