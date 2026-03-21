package Entity;

import java.util.ArrayList;
import java.util.List;

public class EmployeeService_List {
	List<Employee_List> listEmployee = new ArrayList<Employee_List>();

	public void add(Employee_List employee) {
		listEmployee.add(employee);
	}

	public void displayAll() {
		for (Employee_List employee : listEmployee) {
			System.out.println(employee);
		}
	}

	public Employee_List findById(String id) {
		for (Employee_List employee : listEmployee) {
			if (employee.getId().equalsIgnoreCase(id)) {
				return employee;
			}
		}
		return null;
	}

	public void updateSalary(String id, double newSalary) {
		Employee_List employeeFound = findById(id);
		if (employeeFound != null) {
			employeeFound.setSalary(newSalary);
			System.out.println(">> Cập nhật thành công!");
		} else {
			System.out.print("Lỗi! Chưa tìm thấy mã ID: " + id + " để cập nhật");
		}
	}

	public List<Employee_List> getAll() {
		return listEmployee;
	}
}
