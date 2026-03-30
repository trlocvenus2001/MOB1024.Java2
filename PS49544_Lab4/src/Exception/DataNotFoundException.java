package Exception;

@SuppressWarnings("serial")

public class DataNotFoundException extends Exception {
	private String searchedId;

	public DataNotFoundException(String id, String message) {
		super(message);
		this.searchedId = id;
	}

	public String getId() {
		return searchedId;
	}
}
