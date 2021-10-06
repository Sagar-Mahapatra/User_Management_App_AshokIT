package in.ashokit.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorMsg> userNotFoundExceptionHandler(UserNotFoundException ex) {
		ErrorMsg errorMsg = new ErrorMsg(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), new Date());
		return new ResponseEntity<ErrorMsg>(errorMsg, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<ErrorMsg> nullPointerExceptionHandler(NullPointerException ex) {
		ErrorMsg errorMsg = new ErrorMsg(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), new Date());
		return new ResponseEntity<ErrorMsg>(errorMsg, HttpStatus.BAD_REQUEST);

	}

}
