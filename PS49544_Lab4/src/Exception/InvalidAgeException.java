package Exception;

@SuppressWarnings("serial")

public class InvalidAgeException extends Exception {
	private int InvalidAge;

	public InvalidAgeException(int age, String message) {
		super(message);
		this.InvalidAge = age;
	}

	public int getInvalidAge() {
		return InvalidAge;
	}
}
