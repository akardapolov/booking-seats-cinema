package org.test2gis.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.test2gis.model.annotation.ColumnBooking;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Seat {
  @ColumnBooking(name = "ID")
  private int id;
  @ColumnBooking(name = "SEAT_NUMBER")
  private String seatNumber;
  @ColumnBooking(name = "IS_BOOKING")
  private boolean isBooking;
  @ColumnBooking(name = "ROW_ID")
  private int rowId;
  @ColumnBooking(name = "HALL_ID")
  private int hallId;
  @ColumnBooking(name = "CINEMA_ID")
  private int cinemaId;
}
