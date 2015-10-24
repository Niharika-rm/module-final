package n.rrs.exception;

public class AppException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4999293520312688249L;

	public AppException(String message){
		super(message);
		
	}
	
	public AppException (String message, Throwable cause) {
		super(message, cause);

}
}