package model;

public class Student extends Person {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double gpa;

	public Student() {
		super();
	}

	public Student(String id, String name, double gpa) {
		super(id, name);
		this.gpa = gpa;
	}

	public double getGpa() {
		return gpa;
	}

	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	@Override
	public String toString() {
		return "Student [id=" + getId() + ", name=" + getName() + ", GPA=" + gpa + "]";
	}

	@Override
	public void inputInfo() {
		super.inputInfo();
		while (true) {
			try {
				System.out.print(">> GPA: ");
				String input = sc.nextLine();
				double inputGpa = Double.parseDouble(input);
				if (inputGpa < 0 || inputGpa > 4) {
					throw new Exception(">> Lỗi: GPA phải nằm trong [0, 4]");
				}
				setGpa(inputGpa);
				break;
			} catch (NumberFormatException e) {
				// TODO: handle exception
				System.out.println(">> Lỗi: GPA phải là số");
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
