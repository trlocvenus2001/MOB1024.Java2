package Main;

import java.util.Scanner;

import Exception.InvalidAgeException;
import Service.StudentService;

public class mainLab4_1 {
	public static Scanner sc = new Scanner(System.in);
	public static StudentService service = new StudentService();

	public static void main(String[] args) {
		int choice = -1;
		do {
			menu();
			try {
				choice = Integer.parseInt(sc.nextLine());
				if (choice < 0 || choice > 2) {
					System.out.println(">> Lỗi: Vui lòng nhập từ 0 đến 2");
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
				case 0:
					System.out.println(">> Thoát chương trình!");
					System.exit(0);
					break;
				}
			} catch (NumberFormatException e) {
				// TODO: handle exception
				System.out.println(">> Lỗi: Vui lòng nhập từ 0 đến 2");
				pauseScreen();
				continue;
			} catch (Exception e) {
				System.out.println(">> Lỗi hệ thống: " + e.getMessage());
				pauseScreen();
			}

		} while (choice != 0);
		sc.close();
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
				throw new IllegalArgumentException(">> Lỗi: Tên sinh viên không được để trống!");
			}
			System.out.print(">> GPA: ");
			double gpa = Double.parseDouble(sc.nextLine());
			System.out.print(">> Age: ");
			int age = Integer.parseInt(sc.nextLine());
			service.add(id, name, gpa, age);
		} catch (NumberFormatException e) {
			// TODO: handle exception
			System.out.println(">> Lỗi: Điểm GPA là 1 con số!");
		} catch (IllegalArgumentException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		} catch (InvalidAgeException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

	}

	private static void menu() {
		// TODO Auto-generated method stub
		cleanScreen();
		System.out.println("|---------------------------------------|");
		System.out.println("|			QUẢN LÝ SINH VIÊN			|");
		System.out.println("|---------------------------------------|");
		System.out.println("|1. Thêm sinh viên						|");
		System.out.println("|2. Hiển thị danh sách sinh viên		|");
		System.out.println("|0. Thoát								|");
		System.out.println("|---------------------------------------|");
		System.out.print("Chọn chức năng (0-2): ");
	}

	private static void cleanScreen() {
		for (int i = 0; i < 50; i++) {
			System.out.println();
		}
	}

	private static void pauseScreen() {
		System.out.println(">> Nhấn Enter để tiếp tục...");
		sc.nextLine();
	}
}
