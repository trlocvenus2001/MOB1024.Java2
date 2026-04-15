package model;

class Employee {
	private double baseSalary;

	public boolean setBaseSalary(double baseSalary) {
		if (baseSalary > 0) {
			this.baseSalary = baseSalary;
			return true;
		}
		return false;
	}

	@Deprecated
	public double getSalary() {
		return baseSalary;
	}

	public double calculateSalary() {
		return baseSalary;
	}
}