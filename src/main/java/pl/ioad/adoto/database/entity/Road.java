package pl.ioad.adoto.database.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "roads")
@DiscriminatorValue("Road")
public class Road extends TopEntity { }
