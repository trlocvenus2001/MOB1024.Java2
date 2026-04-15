package model;

@Developer(name = "Loc Polo", version = "1.0.0")
public class Manager extends Employee {
	@Override
	public double calculateSalary() {
		return super.calculateSalary() * 1.5;
	}
}
