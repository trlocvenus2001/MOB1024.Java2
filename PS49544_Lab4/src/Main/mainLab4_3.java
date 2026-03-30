package Main;

import Entity.Employee;
import Entity.GenericManager;
import Entity.Student;

public class mainLab4_3 {
	public static void main(String[] args) {
		GenericManager<Student> managerStudent = new GenericManager<>();
		managerStudent.add(new Student("1", "Trường Lộc", 3.6, 25));
		managerStudent.add(new Student("2", "Minh Nhật", 3.2, 22));
		managerStudent.display();
		System.out.println(">> Tổng sinh viên: " + managerStudent.getAll().size());

		GenericManager<Employee> managerEmployee = new GenericManager<>();
		managerEmployee.add(new Employee("3", "Minh Quân", 12000000));
		managerEmployee.add(new Employee("4", "Ngọc Thịnh", 14000000));
		managerEmployee.display();
		System.out.println(">> Tổng nhân viên: " + managerEmployee.getAll().size());

	}
}
