package Main;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Entity.Employee;

public class mainLab3_2 {
	public static void main(String[] args) { // dùng filter, map, sorted, count.
		List<Employee> listEmployee = new ArrayList<>();
		listEmployee.add(new Employee("NV01", "Lộc", 16000000));
		listEmployee.add(new Employee("NV02", "Nhật", 14000000));
		listEmployee.add(new Employee("NV03", "Thinh", 20000000));
		listEmployee.add(new Employee("NV04", "Quân", 12000000));
		listEmployee.add(new Employee("NV05", "Nhi", 18000000));
		listEmployee.add(new Employee("NV06", "My", 15000000));
		listEmployee.add(new Employee("NV07", "Ngọc", 12000000));
		listEmployee.add(new Employee("NV08", "Trân", 25000000));
		// Lọc nhân viên có salary >= 15_000_000
		listEmployee.stream()
					.filter(x -> x.getSalary() >= 15000000)
					.forEach(x -> System.out.println(x));
		// Sắp xếp nhân viên theo salary giảm dần
		listEmployee.stream()
					.sorted((x1, x2) -> Double.compare(x2.getSalary(), x1.getSalary()))
					.forEach(x -> System.out.println(x));
		// Lấy danh sách tên nhân viên (List<String>) từ danh sách nhân viên (map)
		List<String> listName = listEmployee.stream()
				.map(x -> x.getName())
				.collect(Collectors.toList());	
		listName.forEach(name -> System.out.println(name));
		// Đếm nhân viên có tên bắt đầu bằng chữ "A" (không phân biệt bằng hoa thường)
		long count = listEmployee.stream()
				.filter(x -> x.getName() != null && x.getName().toLowerCase().startsWith("a"))
				.count();
		System.out.println("Số lượng nhân viên: " + count);
	}
}
