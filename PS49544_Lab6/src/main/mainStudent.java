package main;

import java.util.List;
import java.util.Scanner;

import model.Student;
import repository.StudentRepository;

public class mainStudent {
	private static final StudentRepository stur = new StudentRepository();
	private static final Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int choice = -1;
		do {
			menu();
			try {
				choice = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				// TODO: handle exception
				choice = -1;
			}
			switch (choice) {
			case 1:
				showAllStudents();
				break;
			case 2:
				addStudent();
				break;
			case 3:
				updateStudent();
				break;
			case 4:
				deleteStudent();
				break;
			case 5:
				findbyIdStudent();
				break;
			default:
				System.out.println(">> Lựa chọn không phù hợp!");
				break;
			}
		} while (choice != -1);
		sc.close();
	}

	private static void menu() {
		// TODO Auto-generated method stub
		System.out.println("|---------------------------------------|");
		System.out.println("|           Quản lý sinh viên           |");
		System.out.println("|---------------------------------------|");
		System.out.println("| 1. Hiển thị danh sách sinh viên       |");
		System.out.println("| 2. Thêm sinh viên mới                 |");
		System.out.println("| 3. Cập nhật sinh viên                 |");
		System.out.println("| 4. Xóa sinh viên                      |");
		System.out.println("| 5. Tìm sinh viên theo ID              |");
		System.out.println("| 0. Thoát                              |");
		System.out.println("|---------------------------------------|");
		System.out.print(">> Chọn chức năng: ");
	}

	private static void showAllStudents() {
		List<Student> list = stur.findAll();
		list.stream().forEach(System.out::println);
	}

	private static void addStudent() {
		// TODO Auto-generated method stub
		System.out.print(">> Name: ");
		String name = sc.nextLine();
		System.out.print(">> Gender: ");
		String gender = sc.nextLine();
		System.out.print(">> GPA: ");
		float gpa = Float.parseFloat(sc.nextLine());
		Student st = new Student(0, name, gender, gpa);
		if (stur.add(st)) {
			System.out.println(">> Đã thêm!");
		} else {
			System.out.println(">> Không thể thêm");
		}
		showAllStudents();
	}

	private static void updateStudent() {
		// TODO Auto-generated method stub
		System.out.print(">> ID cần cập nhật: ");
		int id = Integer.parseInt(sc.nextLine());
		Student exist = stur.findbyId(id);
		if (exist == null) {
			System.out.println(">> Không tì thấy");
			return;
		}
		System.out.print(">> New name: ");
		String newName = sc.nextLine().trim();
		if (!newName.isEmpty()) {
			exist.setStudent_name(newName);
		}
		System.out.print(">> New gender: ");
		String newGender = sc.nextLine().trim();
		if (!newGender.isEmpty()) {
			exist.setGender(newGender);
		}
		System.out.print(">> New GPA: ");
		String inputGpa = sc.nextLine().trim();
		if (!inputGpa.isEmpty()) {
			exist.setGpa(Float.parseFloat(inputGpa));
		}
		if (stur.update(exist)) {
			System.out.println(">> Cập nhật thành công!");
		} else {
			System.out.println(">> Cập nhật thất bại!");
		}
		showAllStudents();
	}

	private static void deleteStudent() {
		// TODO Auto-generated method stub
		System.out.println(">> ID cần xóa: ");
		int id = Integer.parseInt(sc.nextLine());
		if (stur.delete(id)) {
			System.out.println(">> Xóa thành công");
		} else {
			System.out.println(">> Xóa thất bại");
		}
	}

	private static void findbyIdStudent() {
		System.out.print(">> ID: ");
		Student stu = stur.findbyId(sc.nextInt());
		if (stu != null) {
			System.out.println(stu.toString());
		} else {
			System.out.println(">> Không tìm thấy sinh viên");
		}
	}
}
