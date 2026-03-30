package Service;

import java.util.ArrayList;
import java.util.List;

import Entity.Student;
import Exception.InvalidAgeException;

public class StudentService {
	private List<Student> listStudent = new ArrayList<Student>();

	public void add(String id, String name, double gpa, int age) throws IllegalArgumentException, InvalidAgeException {
		if (id == null || name == null) {
			throw new IllegalArgumentException(">> Lỗi: ID & Tên không hợp lệ!");
		}
		if (gpa < 0 || gpa > 4) {
			throw new IllegalArgumentException(">> Lỗi: Điểm GPA phải nằm trong khoảng từ 0 đến 4!");
		}

		if (age < 18 || age > 60) {
			throw new InvalidAgeException(age, ">> Lỗi: Độ tuổi sinh viên phải từ 18 đến 60!");
		}
		Student student = new Student(id, name, gpa, age);
		listStudent.add(student);
		System.out.println(">> Đã thêm thông tin sinh viên");
	}

	public void display() {
		System.out.println(">> Hiển thị danh sách sinh viên");
		listStudent.forEach(System.out::println);
	}
}
