package Entity;

public class Employee {
	private String id;
	private String name;
	private double basicSalary;
	public Employee() {
	}
	public Employee(String id, String name, double basicSalary) {
		this.setId(id);
		this.name = name;
		this.setBasicSalary(basicSalary);
	}
	public String getId() {
		return id;
	}
	public boolean setId(String id) {
		if (id != "") {
			this.id = id;
			return true;
		}
		return false;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getBasicSalary() {
		return basicSalary;
	}
	public boolean setBasicSalary(double basicSalary) {
		if (basicSalary >= 0) {
			this.basicSalary = basicSalary;
			return true;
		}
		return false;
	}
	public double income() {
		return basicSalary;
	}
	@Override
	public String toString() {
		return "NhanVien [id=" + id + ", name=" + name + ", basicSalary=" + basicSalary + "]";
	}
	
}
