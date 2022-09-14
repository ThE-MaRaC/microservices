package hr.mhercog.usermanagementservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice(annotations = RestControllerAdvice.class)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleExceptionInternal(final Exception ex, final Object body,
      final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
    return new ResponseEntity<>(ex.getMessage(), headers, status);
  }

  @ExceptionHandler(ResponseStatusException.class)
  public ResponseEntity<?> responseStatusException(final ResponseStatusException ex) {
    return new ResponseEntity<>(ex.getReason(), ex.getStatus());
  }

  @ExceptionHandler(Throwable.class)
  public ResponseEntity<?> unhandledException(final Throwable ex) {
    return new ResponseEntity<>(this.getError(HttpStatus.INTERNAL_SERVER_ERROR, ex),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

  private Error getError(final HttpStatus status, final Throwable ex) {
    log.error("GLOBAL ERROR: {} {}", status, ex);
    return Error.builder().code(status.value()).message(ex.getMessage()).build();
  }
}
