package org.test2gis;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.test2gis.model.BaseResponse;
import org.test2gis.model.dto.SeatForBookDto;
import org.test2gis.model.entity.Seat;
import org.test2gis.service.SeatsService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = BookingSeatsCinemaApplication.class)
@AutoConfigureMockMvc
public class SeatsControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private SeatsService seatsService;

  private List<Seat> seats = new ArrayList<>();
  private List<SeatForBookDto> seatForBookDto = new ArrayList<>();

  public final MediaType APPLICATION_JSON_UTF8 =
      new MediaType(MediaType.APPLICATION_JSON.getType(),
      MediaType.APPLICATION_JSON.getSubtype()
  );

  @Test
  public void getListSeatsTest() throws Exception {
    ObjectMapper mapper = new ObjectMapper();

    seats.add(Seat.builder().id(1).seatNumber("A1").isBooking(false).rowId(1).cinemaId(1).hallId(1).build());
    seats.add(Seat.builder().id(2).seatNumber("A2").isBooking(false).rowId(1).cinemaId(1).hallId(1).build());

    when(seatsService.getSeats())
        .thenReturn(seats);

    BaseResponse<List<Seat>> baseResponse = new BaseResponse<>();

    mockMvc.perform(get("/seats"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(APPLICATION_JSON_UTF8))
        .andExpect(content().string(mapper.writeValueAsString(baseResponse
            .getValidResponse("Список всех мест", seats))));

    verify(seatsService, times(1)).getSeats();
    verifyNoMoreInteractions(seatsService);
  }

  @Test
  public void bookSeatTest() throws Exception {
    ObjectMapper mapper = new ObjectMapper();

    seatForBookDto.add(SeatForBookDto.builder().id(1).isBooking(true).build());
    seatForBookDto.add(SeatForBookDto.builder().id(2).isBooking(true).build());

    when(seatsService.bookSeats(seatForBookDto))
        .thenReturn(seatForBookDto);

    BaseResponse<List<SeatForBookDto>> baseResponse = new BaseResponse<>();

    mockMvc.perform(put("/seats")
        .content(mapper.writeValueAsString(seatForBookDto))
            .contentType(APPLICATION_JSON_UTF8)
            .accept(MediaType.APPLICATION_JSON)
            .characterEncoding("UTF-8"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(APPLICATION_JSON_UTF8))
        .andExpect(content().string(mapper.writeValueAsString(baseResponse
            .getValidResponse("Бронирование мест", seatForBookDto))));

    verify(seatsService, times(1)).bookSeats(seatForBookDto);
    verifyNoMoreInteractions(seatsService);
  }

}
