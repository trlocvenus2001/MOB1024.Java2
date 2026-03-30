package Main;

import java.util.Scanner;

import Exception.DataNotFoundException;
import Exception.DuplicateEmployeeException;
import Exception.InvalidSalaryException;
import Exception.SalaryOutOfRangeException;
import Service.EmployeeService;

public class mainLab4_2 {
	private static Scanner sc = new Scanner(System.in);
	private static EmployeeService service = new EmployeeService();

	public static void main(String[] args) {
		int choice = -1;
		do {
			menu();
			try {
				choice = Integer.parseInt(sc.nextLine());
				if (choice < 0 || choice > 3) {
					System.out.println(">> Lỗi: Vui lòng nhập từ 0 đến 3");
					pauseScreen();
					continue;
				}
				switch (choice) {
				case 1:
					input();
					pauseScreen();
					break;
				case 2:
					service.display();
					pauseScreen();
					break;
				case 3:
					search();
					pauseScreen();
					break;
				case 0:
					System.out.println(">> Thoát chương trình");
					System.exit(0);
				}
			} catch (NumberFormatException e) {
				// TODO: handle exception
				System.out.println(">> Lỗi: Vui lòng nhập từ 0 đến 3");
				pauseScreen();
				continue;
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(">> Lỗi hệ thống: " + e.getMessage());
			}

		} while (choice != 0);
		sc.close();
	}

	private static void search() {
		// TODO Auto-generated method stub
		System.out.println(">> ID: ");
		String searchId = sc.nextLine();
		try {
			service.findById(searchId);
		} catch (DataNotFoundException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

	private static void input() {
		// TODO Auto-generated method stub
		try {
			System.out.print(">> ID: ");
			String id = sc.nextLine();
			if (id.trim().isEmpty()) {
				throw new IllegalArgumentException(">> Lỗi: ID không được để trống!");
			}
			System.out.print(">> Name: ");
			String name = sc.nextLine();
			if (name.trim().isEmpty()) {
				throw new IllegalArgumentException(">> Lỗi: Tên nhân viên không được để trống!");
			}
			System.out.print(">> Salary: ");
			double salary = Double.parseDouble(sc.nextLine());
			service.add(id, name, salary);
		} catch (NumberFormatException e) {
			// TODO: handle exception
			System.out.println(">> Lỗi: Lương là 1 con số!");
		} catch (DuplicateEmployeeException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		} catch (InvalidSalaryException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		} catch (SalaryOutOfRangeException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			System.out.printf(">> Hệ thống từ chối mức lương: %,.0f VNĐ\n", e.getSalary());
		}
	}

	private static void menu() {
		// TODO Auto-generated method stub
		cleanScreen();
		System.out.println("|---------------------------------------|");
		System.out.println("|			QUẢN LÝ NHÂN VIÊN			|");
		System.out.println("|---------------------------------------|");
		System.out.println("|1. Thêm nhân viên						|");
		System.out.println("|2. Hiển thị danh sách nhân viên		|");
		System.out.println("|3. Tìm nhân viên theo mã				|");
		System.out.println("|0. Thoát								|");
		System.out.println("|---------------------------------------|");
		System.out.print(">> Chọn chức năng (0-3): ");
	}

	private static void cleanScreen() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 50; i++) {
			System.out.println();
		}
	}

	private static void pauseScreen() {
		// TODO Auto-generated method stub
		System.out.println(">> Nhấn Enter để tiếp tục...");
		sc.nextLine();

	}

}
