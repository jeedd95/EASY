package exception;

public class SqlBoardException extends BoardException{
	public SqlBoardException() {
		super();
	};
	
	public SqlBoardException(String message) {
		super(message);
	}
}
