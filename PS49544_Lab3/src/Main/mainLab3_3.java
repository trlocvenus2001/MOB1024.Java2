package Main;

import java.util.ArrayList;
import java.util.List;

import Entity.Employee;

public class mainLab3_3 {
	public static void main(String[] args) { // mapToDouble, sum, max, average.
		List<Employee> listEmployee = new ArrayList<>();
		listEmployee.add(new Employee("NV01", "Lộc", 16000000));
		listEmployee.add(new Employee("NV02", "Nhật", 14000000));
		listEmployee.add(new Employee("NV03", "Thinh", 20000000));
		listEmployee.add(new Employee("NV04", "Quân", 12000000));
		listEmployee.add(new Employee("NV05", "Nhi", 18000000));
		listEmployee.add(new Employee("NV06", "My", 15000000));
		listEmployee.add(new Employee("NV07", "Ngọc", 12000000));
		listEmployee.add(new Employee("NV08", "Trân", 25000000));
		// Tính tổng lương tất cả nhân viên (sum)
		double sumSalary = listEmployee.stream()
				.mapToDouble(x -> x.getSalary())
				.sum();
		System.out.printf("Tổng lương nhân viên: %.2f", sumSalary);
		// Tính trung bình lương nhân viên
		double averageSalary = listEmployee.stream()
				.mapToDouble(x -> x.getSalary())
				.average()
				.orElse(0.0);
		System.out.printf("Trung bình lương nhân viên: %.2f", averageSalary);
		// Tìm nhân viên lương cao nhất
		Employee top1Staff = listEmployee.stream()
				.max((x1, x2) -> Double.compare(x1.getSalary(), x2.getSalary()))
				.orElse(null);
		System.out.println("Nhân viên có lương cao nhất: ");
		if (top1Staff != null) {
			System.out.println(top1Staff.toString());
		}
	}
}
