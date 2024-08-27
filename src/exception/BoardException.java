package exception;

import java.sql.SQLException;

public class BoardException extends SQLException{
	public BoardException() {
		
	};
	
	public BoardException(String message) {
		System.out.println(message);
	}
	
}
