package mainPackage;

import java.util.ArrayList;

import Entity.*;

public class mainLab1_2 {
	public static void main(String[] args) {
		ArrayList<Employee> listEmployee = new ArrayList<Employee>();
		Employee e1 = new Employee("E01", "Lã Thế Nam", 900);
		listEmployee.add(e1);
		listEmployee.add(new FullTimeEmployee("FT01", "Huỳnh Trường Lộc", 1000, 500, 50));
		listEmployee.add(new FullTimeEmployee("FT02", "Nguyễn Minh Nhật", 1200, 200, 0));
		listEmployee.add(new PartTimeEmployee("PT01", "Vũ Minh Quân", 0, 80, 20));
		listEmployee.add(new PartTimeEmployee("PT02", "Phạm Ngọc Thịnh", 0, 100, 15));
		
		for (Employee e : listEmployee) {
			System.out.println(e.toString());
		}
		Employee maxEmployee = listEmployee.get(0);
		for (Employee e : listEmployee) {
			if (e.income() > maxEmployee.income()) {
				maxEmployee = e;				
			}
		}
		System.out.println("Nhân viên có thu nhập cao nhất là: " + maxEmployee.toString());
	}
}
