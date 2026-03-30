package Exception;

@SuppressWarnings("serial")

public class SalaryOutOfRangeException extends Exception {
	private double inputSalary;

	public SalaryOutOfRangeException(double salary, String message) {
		super(message);
		this.inputSalary = salary;
	}

	public double getSalary() {
		return inputSalary;
	}
}