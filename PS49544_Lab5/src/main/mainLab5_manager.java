package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Employee;
import model.Student;
import service.PersonService;

public class mainLab5_manager {
	public static final String STUDENT_FILE = "students.dat";
	public static final String EMPLOYEE_FILE = "employees.dat";
	public static final Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		ArrayList<Student> listStudent = new ArrayList<>();
		ArrayList<Employee> listEmployee = new ArrayList<>();
		menu(listStudent, listEmployee);
		sc.close();
	}

	// ------------ STUDENT MANAGEMENT ------------
	private static void managerStudent(ArrayList<Student> listStudent) {
		int subChoice;
		do {
			System.out.println("|------------------- QUẢN LÝ SINH VIÊN -------------------|");
			System.out.println("| 1. Nhập danh sách sinh viên                             |");
			System.out.println("| 2. Lưu sinh viên vào file                               |");
			System.out.println("| 3. Đọc và hiển thị sinh viên từ file                    |");
			System.out.println("| 4. Xuất danh sách sinh viên                             |");
			System.out.println("| 5. Sắp xếp sinh viên                                    |");
			System.out.println("| 6. Top 3 sinh viên xuất sắc                             |");
			System.out.println("| 7. Phân loại học lực sinh viên                          |");
			System.out.println("| 8. Cập nhật hoặc xóa sinh viên                          |");
			System.out.println("| 9. Tìm kiếm sinh viên theo tên                          |");
			System.out.println("| 0. Quay lại                                             |");
			System.out.println("|---------------------------------------------------------|");
			System.out.print(">> Chọn chức năng: ");
			String input = sc.nextLine();
			try {
				subChoice = Integer.parseInt(input);
			} catch (NumberFormatException e) {
				System.out.println(">> Vui lòng nhập số nguyên!");
				subChoice = -1;
			}
			switch (subChoice) {
			case 1:
				inputStudent(listStudent);
				break;
			case 2:
				saveStudent(listStudent);
				break;
			case 3:
				readStudent();
				break;
			case 4:
				outputStudent(listStudent);
				break;
			case 5:
				sortStudent(listStudent);
				break;
			case 6:
				top3Student(listStudent);
				break;
			case 7:
				resultStudent(listStudent);
				break;
			case 8:
				updateOrDeleteStudent(listStudent);
				break;
			case 9:
				searchStudent(listStudent);
				break;
			case 0:
				break;
			default:
				System.out.println(">> Lựa chọn không hợp lệ!");
				break;
			}
		} while (subChoice != 0);
	}

	// ------------ EMPLOYEE MANAGEMENT ------------
	private static void managerEmployee(ArrayList<Employee> listEmployee) {
		int subChoice;
		do {
			System.out.println("|------------------- QUẢN LÝ NHÂN VIÊN -------------------|");
			System.out.println("| 1. Nhập danh sách nhân viên                             |");
			System.out.println("| 2. Lưu nhân viên vào file                               |");
			System.out.println("| 3. Đọc và hiển thị nhân viên từ file                    |");
			System.out.println("| 4. Xuất danh sách nhân viên                             |");
			System.out.println("| 5. Sắp xếp nhân viên                                    |");
			System.out.println("| 6. Top 3 lương cao nhất                                 |");
			System.out.println("| 7. Độ dài tên ngắn nhất & dài nhất                      |");
			System.out.println("| 8. Cập nhật hoặc xóa nhân viên                          |");
			System.out.println("| 9. Tìm kiếm nhân viên theo tên                          |");
			System.out.println("| 0. Quay lại                                             |");
			System.out.println("|---------------------------------------------------------|");
			System.out.print(">> Chọn chức năng: ");
			String input = sc.nextLine();
			try {
				subChoice = Integer.parseInt(input);
			} catch (NumberFormatException e) {
				System.out.println(">> Vui lòng nhập số nguyên!");
				subChoice = -1;
			}
			switch (subChoice) {
			case 1:
				inputEmployee(listEmployee);
				break;
			case 2:
				saveEmployee(listEmployee);
				break;
			case 3:
				readEmployee();
				break;
			case 4:
				outputEmployee(listEmployee);
				break;
			case 5:
				sortEmployee(listEmployee);
				break;
			case 6:
				top3Employee(listEmployee);
				break;
			case 7:
				searchNameEmployee(listEmployee);
				break;
			case 8:
				updateOrDeleteEmployee(listEmployee);
				break;
			case 9:
				searchEmployee(listEmployee);
				break;
			case 0:
				break;
			default:
				System.out.println(">> Lựa chọn không hợp lệ!");
				break;
			}
		} while (subChoice != 0);
	}

	// ------------ MAIN MENU ------------
	private static void menu(ArrayList<Student> listStudent, ArrayList<Employee> listEmployee) {
		int mainChoice;
		do {
			System.out.println("|---------------------- MENU -----------------------|");
			System.out.println("| 1. Quản lý sinh viên                              |");
			System.out.println("| 2. Quản lý nhân viên                              |");
			System.out.println("| 0. Thoát chương trình                             |");
			System.out.println("|---------------------------------------------------|");
			System.out.print(">> Chọn chức năng: ");
			String input = sc.nextLine();
			try {
				mainChoice = Integer.parseInt(input);
			} catch (NumberFormatException e) {
				System.out.println(">> Vui lòng nhập số nguyên!");
				mainChoice = -1;
			}
			switch (mainChoice) {
			case 1:
				managerStudent(listStudent);
				break;
			case 2:
				managerEmployee(listEmployee);
				break;
			case 0:
				System.out.println(">> Thoát chương trình !");
				break;
			default:
				System.out.println(">> Lựa chọn không hợp lệ!");
				break;
			}
		} while (mainChoice != 0);
	}

	// ------------ CÁC CHỨC NĂNG SINH VIÊN ------------
	private static void inputStudent(ArrayList<Student> listStudent) {
		while (true) {
			Student student = new Student();
			System.out.print(">> Nhập thông tin sinh viên: ");
			student.inputInfo();
			listStudent.add(student);
			System.out.print(">> Tiếp tục? (N: dừng, nhập bất kỳ để tiếp tục): ");
			String nextStudent = sc.nextLine();
			if (nextStudent.trim().equalsIgnoreCase("N")) {
				break;
			}
		}
	}

	private static void saveStudent(ArrayList<Student> listStudent) {
		PersonService.saveFile(listStudent, STUDENT_FILE);
		System.out.println(">> Đã lưu danh sách sinh viên vào file.");
	}

	private static void readStudent() {
		List<Student> list = PersonService.readFile(STUDENT_FILE);
		System.out.println(">> Danh sách sinh viên từ file:");
		for (Student s : list) {
			System.out.println(s);
		}
	}

	private static void outputStudent(ArrayList<Student> listStudent) {
		for (Student s : listStudent) {
			System.out.println(s);
		}
		searchNameStudent(listStudent);
		averageGpa(listStudent);
	}

	private static void sortStudent(ArrayList<Student> listStudent) {
		System.out.println("1. Sắp xếp từ A-Z");
		System.out.println("2. Sắp xếp từ Z-A");
		System.out.print("Chọn kiểu sắp xếp: ");
		String opt = sc.nextLine();
		if ("2".equals(opt)) {
			sortStudentDesc(listStudent);
		} else {
			sortStudentAsc(listStudent);
		}
	}

	private static void top3Student(ArrayList<Student> listStudent) {
		List<Student> xs = listStudent.stream().filter(sv -> sv.getGpa() >= 9.0)
				.sorted((a, b) -> Double.compare(b.getGpa(), a.getGpa())).limit(3).toList();
		if (xs.size() == 0) {
			System.out.println(">> Không có sinh viên xuất sắc nào.");
			return;
		}
		for (Student s : xs) {
			System.out.println(s);
		}
	}

	private static void resultStudent(ArrayList<Student> listStudent) {
		for (Student s : listStudent) {
			double gpa = s.getGpa();
			String result;
			if (gpa >= 9.0)
				result = "Xuất sắc";
			else if (gpa >= 8.0)
				result = "Giỏi";
			else if (gpa >= 6.5)
				result = "Khá";
			else if (gpa >= 5.0)
				result = "Trung bình";
			else if (gpa >= 3.5)
				result = "Yếu";
			else
				result = "Kém";
			System.out.println(s + " - Học lực: " + result);
		}
	}

	private static void updateOrDeleteStudent(ArrayList<Student> listStudent) {
		System.out.println("1. Cập nhật thông tin sinh viên");
		System.out.println("2. Xóa sinh viên");
		System.out.print("Chọn chức năng: ");
		String opt = sc.nextLine();
		if ("2".equals(opt)) {
			deleteStudent(listStudent);
		} else {
			updateStudent(listStudent);
		}
	}

	private static void updateStudent(ArrayList<Student> listStudent) {
		System.out.print("Nhập mã sinh viên cần cập nhật: ");
		String id = sc.nextLine();
		int index = -1;
		for (int i = 0; i < listStudent.size(); i++) {
			Student s = listStudent.get(i);
			if (s.getId().equalsIgnoreCase(id)) {
				index = i;
				System.out.println(">> Thông tin sinh viên hiện tại:");
				System.out.println(s);
				System.out.println(">> Nhập lại thông tin mới cho sinh viên này:");
				s.inputInfo();
				System.out.println(">> Đã cập nhật thông tin sinh viên.");
				break;
			}
		}
		if (index == -1) {
			System.out.println(">> Không tìm thấy sinh viên với mã: " + id);
		}
	}

	private static void deleteStudent(ArrayList<Student> listStudent) {
		System.out.print(">> Nhập mã sinh viên cần xóa: ");
		String id = sc.nextLine();
		int removeCount = 0;
		for (int i = listStudent.size() - 1; i >= 0; i--) {
			if (listStudent.get(i).getId().equalsIgnoreCase(id)) {
				listStudent.remove(i);
				removeCount++;
			}
		}
		if (removeCount > 0)
			System.out.println(">> Đã xóa sinh viên có mã: " + id);
		else
			System.out.println(">> Không tìm thấy sinh viên với mã: " + id);
	}

	private static void searchStudent(ArrayList<Student> listStudent) {
		System.out.print(">> Nhập tên sinh viên cần tìm: ");
		String ten = sc.nextLine().trim();
		int foundCount = 0;
		System.out.println(">> Kết quả tìm kiếm sinh viên theo tên '" + ten + "':");
		for (Student s : listStudent) {
			if (s.getName() != null && s.getName().toLowerCase().contains(ten.toLowerCase())) {
				System.out.println(s);
				foundCount++;
			}
		}
		if (foundCount == 0) {
			System.out.println(">> Không tìm thấy sinh viên nào với tên: " + ten);
		}
	}

	// ------------ CÁC CHỨC NĂNG NHÂN VIÊN ------------
	private static void inputEmployee(ArrayList<Employee> listEmployee) {
		while (true) {
			Employee employee = new Employee();
			System.out.print(">> Nhập thông tin nhân viên: ");
			employee.inputInfo();
			listEmployee.add(employee);
			System.out.print(">> Tiếp tục? (N: dừng, nhập bất kỳ để tiếp tục): ");
			String nextEmployee = sc.nextLine();
			if (nextEmployee.trim().equalsIgnoreCase("N")) {
				break;
			}
		}
	}

	private static void saveEmployee(ArrayList<Employee> listEmployee) {
		PersonService.saveFile(listEmployee, EMPLOYEE_FILE);
		System.out.println(">> Đã lưu danh sách nhân viên vào file.");
	}

	private static void readEmployee() {
		List<Employee> list = PersonService.readFile(EMPLOYEE_FILE);
		System.out.println(">> Danh sách nhân viên từ file:");
		for (Employee e : list) {
			System.out.println(e);
		}
	}

	private static void outputEmployee(ArrayList<Employee> listEmployee) {
		for (Employee e : listEmployee) {
			System.out.println(e);
		}
		searchNameEmployee(listEmployee);
	}

	private static void sortEmployee(ArrayList<Employee> listEmployee) {
		System.out.println("1. Sắp xếp từ A-Z");
		System.out.println("2. Sắp xếp từ Z-A");
		System.out.print("Chọn kiểu sắp xếp: ");
		String opt = sc.nextLine();
		if ("2".equals(opt)) {
			sortEmployeeDesc(listEmployee);
		} else {
			sortEmployeeAsc(listEmployee);
		}
	}

	private static void top3Employee(ArrayList<Employee> listEmployee) {
		if (listEmployee.isEmpty()) {
			System.out.println("Không có nhân viên nào.");
			return;
		}
		List<Employee> sortedList = new ArrayList<>(listEmployee);
		sortedList.sort((a, b) -> Double.compare(b.getSalary(), a.getSalary()));

		List<Employee> topList = new ArrayList<>();
		double lastTopSalary = -1;
		int count = 0;
		for (Employee e : sortedList) {
			if (count < 3) {
				topList.add(e);
				lastTopSalary = e.getSalary();
				count++;
			} else if (e.getSalary() == lastTopSalary) {
				topList.add(e);
			} else {
				break;
			}
		}
		System.out.println(">> TOP NHÂN VIÊN LƯƠNG CAO NHẤT");
		for (Employee e : topList) {
			System.out.println(e);
		}
	}

	private static void updateOrDeleteEmployee(ArrayList<Employee> listEmployee) {
		System.out.println("1. Cập nhật thông tin nhân viên");
		System.out.println("2. Xóa nhân viên");
		System.out.print("Chọn chức năng: ");
		String opt = sc.nextLine();
		if ("2".equals(opt)) {
			deleteEmployee(listEmployee);
		} else {
			updateEmployee(listEmployee);
		}
	}

	private static void updateEmployee(ArrayList<Employee> listEmployee) {
		System.out.print("Nhập mã nhân viên cần cập nhật: ");
		String id = sc.nextLine();
		int index = -1;
		for (int i = 0; i < listEmployee.size(); i++) {
			Employee e = listEmployee.get(i);
			if (e.getId().equalsIgnoreCase(id)) {
				index = i;
				System.out.println(">> Thông tin nhân viên hiện tại:");
				System.out.println(e);
				System.out.println(">> Nhập lại thông tin mới cho nhân viên này:");
				e.inputInfo();
				System.out.println(">> Đã cập nhật thông tin nhân viên.");
				break;
			}
		}
		if (index == -1) {
			System.out.println(">> Không tìm thấy nhân viên với mã: " + id);
		}
	}

	private static void deleteEmployee(ArrayList<Employee> listEmployee) {
		System.out.print(">> Nhập mã nhân viên cần xóa: ");
		String id = sc.nextLine();
		int removeCount = 0;
		for (int i = listEmployee.size() - 1; i >= 0; i--) {
			if (listEmployee.get(i).getId().equalsIgnoreCase(id)) {
				listEmployee.remove(i);
				removeCount++;
			}
		}
		if (removeCount > 0)
			System.out.println(">> Đã xóa nhân viên có mã: " + id);
		else
			System.out.println(">> Không tìm thấy nhân viên với mã: " + id);
	}

	private static void searchEmployee(ArrayList<Employee> listEmployee) {
		System.out.print(">> Nhập tên nhân viên cần tìm: ");
		String ten = sc.nextLine().trim();
		int foundCount = 0;
		System.out.println(">> Kết quả tìm kiếm nhân viên theo tên '" + ten + "':");
		for (Employee e : listEmployee) {
			if (e.getName() != null && e.getName().toLowerCase().contains(ten.toLowerCase())) {
				System.out.println(e);
				foundCount++;
			}
		}
		if (foundCount == 0) {
			System.out.println(">> Không tìm thấy nhân viên nào với tên: " + ten);
		}
	}

	// ------------ TIỆN ÍCH BỔ SUNG ------------
	private static void searchNameStudent(ArrayList<Student> listStudent) {
		if (listStudent.isEmpty()) {
			System.out.println(">> Không có sinh viên nào để kiểm tra độ dài tên.");
			return;
		}
		int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
		for (Student s : listStudent) {
			String name = s.getName();
			if (name != null) {
				int len = name.trim().length();
				if (len < min)
					min = len;
				if (len > max)
					max = len;
			}
		}
		System.out.println(">> Độ dài tên ngắn nhất của sinh viên: " + min);
		System.out.println(">> Độ dài tên dài nhất của sinh viên: " + max);
	}

	private static void searchNameEmployee(ArrayList<Employee> listEmployee) {
		if (listEmployee.isEmpty()) {
			System.out.println(">> Không có nhân viên nào để kiểm tra độ dài tên.");
			return;
		}
		int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
		for (Employee e : listEmployee) {
			String name = e.getName();
			if (name != null) {
				int len = name.trim().length();
				if (len < min)
					min = len;
				if (len > max)
					max = len;
			}
		}
		System.out.println(">> Độ dài tên ngắn nhất của nhân viên: " + min);
		System.out.println(">> Độ dài tên dài nhất của nhân viên: " + max);
	}

	private static void averageGpa(ArrayList<Student> listStudent) {
		if (listStudent.isEmpty()) {
			System.out.println(">> Không có sinh viên nào để tính điểm trung bình.");
			return;
		}
		double tong = 0.0;
		double max = Double.NEGATIVE_INFINITY;
		double min = Double.POSITIVE_INFINITY;
		for (Student s : listStudent) {
			double gpa = s.getGpa();
			tong += gpa;
			if (gpa > max)
				max = gpa;
			if (gpa < min)
				min = gpa;
		}
		double avg = tong / listStudent.size();
		System.out.printf(">> Điểm trung bình của sinh viên: %.2f%n", avg);
		System.out.printf(">> Điểm trung bình cao nhất: %.2f%n", max);
		System.out.printf(">> Điểm trung bình thấp nhất: %.2f%n", min);
	}

	private static void sortStudentAsc(ArrayList<Student> listStudent) {
		listStudent.sort((a, b) -> a.getName().compareToIgnoreCase(b.getName()));
		System.out.println(">> Đã sắp xếp sinh viên từ A-Z.");
		outputStudent(listStudent);
	}

	private static void sortStudentDesc(ArrayList<Student> listStudent) {
		listStudent.sort((a, b) -> b.getName().compareToIgnoreCase(a.getName()));
		System.out.println(">> Đã sắp xếp sinh viên từ Z-A.");
		outputStudent(listStudent);
	}

	private static void sortEmployeeAsc(ArrayList<Employee> listEmployee) {
		listEmployee.sort((a, b) -> a.getName().compareToIgnoreCase(b.getName()));
		System.out.println(">> Đã sắp xếp nhân viên từ A-Z.");
		outputEmployee(listEmployee);
	}

	private static void sortEmployeeDesc(ArrayList<Employee> listEmployee) {
		listEmployee.sort((a, b) -> b.getName().compareToIgnoreCase(a.getName()));
		System.out.println(">> Đã sắp xếp nhân viên từ Z-A.");
		outputEmployee(listEmployee);
	}
}