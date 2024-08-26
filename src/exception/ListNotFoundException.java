package exception;

public class ListNotFoundException extends RuntimeException {
	public ListNotFoundException() {}
	
	public ListNotFoundException(String message) {
		super(message);
	}
}
