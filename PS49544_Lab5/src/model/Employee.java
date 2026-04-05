package model;

public class Employee extends Person {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double salary;

	public Employee() {
		super();
	}

	public Employee(String id, String name, double salary) {
		super(id, name);
		this.salary = salary;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [id=" + getId() + ", name=" + getName() + ", salary=" + salary + "]";
	}

	@Override
	public void inputInfo() {
		super.inputInfo();
		while (true) {
			try {
				System.out.print(">> Salary: ");
				String input = sc.nextLine();
				double inputSalary = Double.parseDouble(input);
				if (inputSalary < 0) {
					throw new Exception(">> Lỗi: Lương không phải số âm.");
				}
				setSalary(inputSalary);
				System.out.println();
			} catch (NumberFormatException e) {
				// TODO: handle exception
				System.out.println(">> Lỗi: Lương phải là số.");
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(">> Lỗi: " + e.getMessage());
			} finally {
				// TODO: handle finally clause
				System.out.println(">> Kết thúc!");
			}
		}
	}
}
