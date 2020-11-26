package org.test2gis.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.test2gis.model.BaseResponse;
import org.test2gis.model.entity.Seat;
import org.test2gis.service.SeatsService;
import org.test2gis.utility.annotation.CustomExceptionHandler;

@RestController
@RequestMapping(value = "/seats")
@CustomExceptionHandler
public class SeatsController {
  private SeatsService seatsService;

  public SeatsController(SeatsService seatsService) {
    this.seatsService = seatsService;
  }

  @PostMapping()
  public ResponseEntity<BaseResponse> createSeat() {
    return ResponseEntity.ok(new BaseResponse());
  }

  @PutMapping()
  public ResponseEntity<BaseResponse<?>> bookSeat() {
    return ResponseEntity.ok(new BaseResponse());
  }

  @GetMapping()
  public ResponseEntity<BaseResponse<List<Seat>>> getSeats() {
    return ResponseEntity.ok(new BaseResponse<List<Seat>>()
        .getValidResponse("Список мест", seatsService.getSeats()));
  }

  @GetMapping("/{seatId}")
  public ResponseEntity<BaseResponse<Seat>> getSeat(@PathVariable String seatId) {
    return ResponseEntity.ok(new BaseResponse());
  }
}
