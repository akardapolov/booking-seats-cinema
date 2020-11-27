package org.test2gis.model.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.test2gis.model.database.H2Database;
import org.test2gis.model.dto.SeatForBookDto;
import org.test2gis.model.entity.Seat;
import org.test2gis.utility.DtoMapper;

@Component
@Slf4j
public class SeatRepository implements Repository<Seat, SeatForBookDto, Integer> {

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

    h2Database.getData(query, new HashMap<>()).forEach(e -> {
      try {
        output.add(dtoMapper.mapToDto(e, Seat.class));
      } catch (Exception exception) {
        log.error(exception.getMessage());
      }
    });

    return output;
  }

  @Override
  public <S extends SeatForBookDto> List<S> bookAll(Iterable<S> var1) {
    var1.forEach(e -> h2Database.execute("update SEAT set IS_BOOKING=" + e.isBooking()
        + " where ID=" + e.getId()));
    h2Database.execute("COMMIT");

    return (List<S>) var1;
  }
}
