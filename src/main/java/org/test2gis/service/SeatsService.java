package org.test2gis.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.test2gis.model.dto.SeatForBookDto;
import org.test2gis.model.entity.Seat;
import org.test2gis.model.repository.SeatRepository;

@Service
public class SeatsService {

  private SeatRepository seatRepository;

  public SeatsService(SeatRepository seatRepository) {
    this.seatRepository = seatRepository;
  }

  public List<Seat> getSeats() {
    return seatRepository.findAll();
  }

  public List<SeatForBookDto> bookSeats(List<SeatForBookDto> seatForBookDtoList) {
    return  seatRepository.bookAll(seatForBookDtoList);
  }
}
