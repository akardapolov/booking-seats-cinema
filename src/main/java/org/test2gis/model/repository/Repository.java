package org.test2gis.model.repository;

import java.util.List;

public interface Repository<T, ID> {
  List<T> findAll();
  <S extends T> List<S> saveAll(Iterable<S> var1);
}
