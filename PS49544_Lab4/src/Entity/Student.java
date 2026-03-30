package Entity;

public class Student {
	private String id;
	private String name;
	private double gpa;
	private int age;

	public Student(String id, String name, double gpa, int age) {
		this.id = id;
		this.name = name;
		this.gpa = gpa;
		this.age = age;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getGpa() {
		return gpa;
	}

	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void inThongTin() {
		System.out.printf("ID: %-5s | Name: %-15s | GPA: %.2f\n", id, name, gpa);
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", gpa=" + gpa + "]";
	}

}
