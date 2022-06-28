package lt.vtmc.GintautasButkus.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class NoCategoryExistsException extends NullPointerException {
	private static final long serialVersionUID = 1L;
	
	public NoCategoryExistsException(String message) {
		super(message);
	}
}
