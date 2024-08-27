package exception;



public class DuplicationIdOrNickNameException extends RuntimeException {
	
    public DuplicationIdOrNickNameException(String message) {
    	
        super(message);
    }
}