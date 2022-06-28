package lt.vtmc.GintautasButkus.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class WrongIsbnException extends IllegalArgumentException {
	private static final long serialVersionUID = 1L;
	
	public WrongIsbnException(String message) {
		super(message);
	}
}
