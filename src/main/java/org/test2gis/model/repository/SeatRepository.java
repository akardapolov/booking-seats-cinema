package org.test2gis.model.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.test2gis.model.database.H2Database;
import org.test2gis.model.entity.Seat;
import org.test2gis.utility.DtoMapper;

@Component
@Slf4j
public class SeatRepository implements Repository<Seat, Integer>{
  private H2Database h2Database;
  private DtoMapper dtoMapper;

  public SeatRepository(H2Database h2Database, DtoMapper dtoMapper) {
    this.h2Database = h2Database;
    this.dtoMapper = dtoMapper;
  }

  @Override
  public List<Seat> findAll() {
    List<Seat> output = new ArrayList<>();
    String query = "select * from SEAT";
    List<Map<String, Object>> data = h2Database.getData(query, h2Database.loadSqlColMetadataList(query));

    data.forEach(e -> {
      try {
        output.add(dtoMapper.mapToDto(e, Seat.class));
      } catch (Exception exception) {
        log.error(exception.getMessage());
      }
    });

    return output;
  }

  @Override
  public <S extends Seat> List<S> saveAll(Iterable<S> var1) {
    return null;
  }
}
