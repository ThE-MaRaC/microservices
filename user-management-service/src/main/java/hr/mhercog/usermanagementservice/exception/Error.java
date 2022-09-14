package hr.mhercog.usermanagementservice.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Error {

  private int code;

  private String message;
}
