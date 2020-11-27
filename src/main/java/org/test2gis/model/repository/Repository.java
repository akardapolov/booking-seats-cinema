package org.test2gis.model.repository;

import java.util.List;

public interface Repository<T, DTO, ID> {
  List<T> findAll();
  <S extends DTO> List<S> bookAll(Iterable<S> var1);
}
