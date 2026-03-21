package Main;

import java.util.List;
import java.util.Scanner;

import Entity.EmployeeService_List;
import Entity.Employee_List;

public class mainLab2_3_List {
	static Scanner sc = new Scanner(System.in);
	static EmployeeService_List service_List = new EmployeeService_List();

	public static void main(String[] args) {
		int choice;
		do {
			try {
				System.out.println("\n========== QUẢN LÝ NHÂN VIÊN ==========");
				System.out.println("1. Thêm nhân viên");
				System.out.println("2. Hiển thị danh sách");
				System.out.println("3. Tìm nhân viên theo mã");
				System.out.println("4. Cập nhật lương theo mã");
				System.out.println("0. Thoát");
				System.out.print("Mời bạn chọn (0-4): ");

				choice = Integer.parseInt(sc.nextLine());
				sc.nextLine();

				switch (choice) {
				case 1:
					add();
					break;
				case 2:
					output();
					break;
				case 3:
					findById();
					break;
				case 4:
					updateSalary();
					break;
				case 0:
					System.out.println(">> Thoát chương trình!");
					break;
				default:
					System.out.println(">> Lỗi! Nhập (0-4) trong Menu");
				}
			} catch (NumberFormatException e) {
				System.out.println(">> Lỗi! Nhập (0-4) trong Menu");
				choice = -1;
			}
		} while (choice != 0);
	}

	public static void add() {
		try {
			System.out.print(">> ID: ");
			String id = sc.nextLine();
			System.out.print(">> Name: ");
			String name = sc.nextLine();
			System.out.print(">> Salary: ");
			double salary = sc.nextDouble();
			Employee_List add_E = new Employee_List(id, name, salary);
			service_List.add(add_E);
			System.out.println(">> Added");
		} catch (NumberFormatException e) {
			System.out.println(">> Error!");
		}
	}

	public static void output() {
		service_List.displayAll();
	}

//	public static void findById() {
//	    System.out.print(">> ID: ");
//	    String findId = sc.nextLine();
//	    
//	    Employee_List listEmp = service_List.findById(findId);
//	    if (listEmp != null) {
//	        System.out.println(">> Found: " + listEmp);
//	    } else {
//	        System.out.println(">> None: " + findId);
//	    }
//	}

	public static void findById() {
		System.out.print(">> ID: ");
		String findId = sc.nextLine();
		List<Employee_List> list_E = service_List.getAll();
		int index = -1;
		for (int i = 0; i < list_E.size(); i++) {
			if (list_E.get(i).getId().equalsIgnoreCase(findId)) {
				index = i;
				return;
			}
		}
		if (index == -1) {
			System.out.println(">> None!");
		} else {
			System.out.println(">> Found!" + list_E.get(index));
		}
	}

//	public static void updateSalary() {
//	    System.out.print(">> ID: ");
//	    String findId = sc.nextLine();
//	    
//	    Employee_List listEmp = service_List.findById(findId);
//	    if (listEmp != null) {
//	        try {
//	            System.out.print(">> New Salary: ");
//	            double newSalary = Double.parseDouble(sc.nextLine());
//	            emp.setSalary(newSalary);
//	            System.out.println(">> Updated!");
//	        } catch (NumberFormatException e) {
//	            System.out.println(">> Error!");
//	        }
//	    } else {
//	        System.out.println(">> None!");
//	    }
//	}

	public static void updateSalary() {
		System.out.print(">> ID: ");
		String findId = sc.nextLine();
		List<Employee_List> list_E = service_List.getAll();
		int index = -1;
		for (int i = 0; i < list_E.size(); i++) {
			if (list_E.get(i).getId().equalsIgnoreCase(findId)) {
				index = i;
				return;
			}
		}
		if (index == -1) {
			System.out.println(">> None");
		} else {
			System.out.print(">> New Salary");
			double newSalary = sc.nextDouble();
			list_E.get(index).setSalary(newSalary);
			System.out.println("Updated");
		}
	}
}
