package in.ashokit.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorMsg> UserNotFoundExceptionHandler() {
		ErrorMsg errorMsg = new ErrorMsg(400, "NO USER FOUND WITH THIS ID", new Date());
		return new ResponseEntity<ErrorMsg>(errorMsg, HttpStatus.BAD_REQUEST);

	}

}
