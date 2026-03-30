package Exception;

@SuppressWarnings("serial")
public class DuplicateEmployeeException extends Exception {
	public DuplicateEmployeeException(String message) {
		super(message);
	}
}
