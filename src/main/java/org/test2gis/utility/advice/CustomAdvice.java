package org.test2gis.utility.advice;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.test2gis.model.BaseResponse;
import org.test2gis.utility.annotation.CustomExceptionHandler;
import org.test2gis.utility.exception.BookTicketException;

@ControllerAdvice(annotations = CustomExceptionHandler.class)
public class CustomAdvice {

  @ExceptionHandler(BookTicketException.class)
  public ResponseEntity<BaseResponse> handleException(int statusCode, BookTicketException e) {
    String message = String.format("%s %s", LocalDateTime.now(), e.getMessage());
    BaseResponse response = new BaseResponse().getErrorResponse(statusCode, message);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

}
