package pl.ioad.adoto.communication.geoportal.model;

import lombok.Builder;

import java.util.List;

@Builder
public record SvgObject(
      String transform,
      List<String> d
) {}
