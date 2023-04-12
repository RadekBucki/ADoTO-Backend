package pl.ioad.adoto.database.entity;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name = "rivers")
@DiscriminatorValue("River")
public class River extends TopEntity { }
