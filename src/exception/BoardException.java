package exception;

import java.sql.SQLException;

public class BoardException extends RuntimeException{
	public BoardException() {
		
	};
	
	public BoardException(String message) {
		super(message);
	}
	
}
