package util;

public class DatabaseException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int errorCode;

	public DatabaseException(int errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}
}
