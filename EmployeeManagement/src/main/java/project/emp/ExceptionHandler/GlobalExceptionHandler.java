package project.emp.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
  //global exception should not extend any exception class
  // Handles 404 - Resource Not Found
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ProblemDetail> handleNotFound(ResourceNotFoundException ex) {

    ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
    //ProblemDetails provides a standardized error structure

    problemDetail.setTitle("Resource Not Found");
    problemDetail.setDetail(ex.getMessage());
    problemDetail.setProperty("timestamp", LocalDateTime.now());

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problemDetail);
  }

  // Handles 400 - Bad Request
  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<ProblemDetail> handleBadRequest(BadRequestException ex) {

    ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);

    problemDetail.setTitle("Bad Request");
    problemDetail.setDetail(ex.getMessage());
    problemDetail.setProperty("timestamp", LocalDateTime.now());

    return ResponseEntity.badRequest().body(problemDetail);
  }

  // Handles 500 - Internal Server Error
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ProblemDetail> handleGeneral(Exception ex) {

    ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);

    problemDetail.setTitle("Internal Server Error");
    problemDetail.setDetail(ex.getMessage());
    problemDetail.setProperty("timestamp", LocalDateTime.now());

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(problemDetail);
  }
}
