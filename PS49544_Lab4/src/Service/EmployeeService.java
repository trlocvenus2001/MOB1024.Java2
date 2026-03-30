package Service;

import java.util.ArrayList;
import java.util.List;

import Entity.Employee;
import Exception.DataNotFoundException;
import Exception.DuplicateEmployeeException;
import Exception.InvalidSalaryException;
import Exception.SalaryOutOfRangeException;

public class EmployeeService {
	private List<Employee> listEmployee = new ArrayList<Employee>();

	public void add(String id, String name, double salary)
			throws DuplicateEmployeeException, InvalidSalaryException, SalaryOutOfRangeException {
		for (Employee employee : listEmployee) {
			if (employee.getId().equalsIgnoreCase(id)) {
				throw new DuplicateEmployeeException(">> Lỗi: ID '" + id + "' đã tồn tại!");
			}
		}
		if (salary < 0) {
			throw new InvalidSalaryException(">> Lỗi: Lương không thể âm! Bạn đã nhập: " + salary);
		}

		if (salary < 3000000 || salary > 100000000) {
			throw new SalaryOutOfRangeException(salary, ">> Lỗi: Lương phải từ 3 triệu đến 100 triệu!");
		}
		Employee employee = new Employee(id, name, salary);
		listEmployee.add(employee);
		System.out.println(">> Đã thêm nhân viên!");
	}

	public void display() {
		System.out.println(">> Hiển thị danh sách nhân viên: ");
		if (listEmployee.isEmpty()) {
			System.out.println(">> Danh sách trống");
			return;
		}
		listEmployee.forEach(System.out::println);
	}

	public void findById(String id) throws DataNotFoundException {
		for (Employee employee : listEmployee) {
			if (employee.getId().equalsIgnoreCase(id)) {
				System.out.println(">> Đã tìm thấy nhân viên!");
				employee.inThongTin();
				return;
			}
		}
		throw new DataNotFoundException(id, ">> Lỗi: Không tìm thấy mã nhân viên!");
	}
}
