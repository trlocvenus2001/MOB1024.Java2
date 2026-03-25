package Main;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Entity.Student;
import Entity.StudentType;

public class mainLab3_4 {
	public static void main(String[] args) { // Kết hợp OOP + Enum + Stream.
		List<Student> listStudent = new ArrayList<>(); 
		listStudent.add(new Student(1, "Lộc", StudentType.REGULAR, 3.0));
		listStudent.add(new Student(2, "Nhật", StudentType.PART_TIME, 2.8));
		listStudent.add(new Student(3, "Mie", StudentType.INTERNATIONAL, 3.5));
		listStudent.add(new Student(4, "Hương", StudentType.REGULAR, 3.2));
		listStudent.add(new Student(5, "Quân", StudentType.PART_TIME, 2.5));
		listStudent.add(new Student(6, "Lyly", StudentType.INTERNATIONAL, 3.1));
		listStudent.add(new Student(7, "Thịnh", StudentType.REGULAR, 2.9));
		listStudent.add(new Student(8, "Nhi", StudentType.PART_TIME, 3.9));
		listStudent.add(new Student(9, "Wang", StudentType.INTERNATIONAL, 3.4));
		listStudent.add(new Student(10, "Trân", StudentType.REGULAR, 3.7));
		// Lọc danh sách sinh viên INTERNATIONAL có gpa >= 3.2
		listStudent.stream()
				.filter(x -> x.getType() == StudentType.INTERNATIONAL && x.getGpa() >= 3.2)
				.forEach(x -> System.out.println(x));
		// Sắp xếp sinh viên theo gpa giảm dần và lấy top 3
		listStudent.stream()
				.sorted((x1, x2) -> Double.compare(x1.getGpa(), x2.getGpa()))
				.limit(3)
				.forEach(x -> System.out.println(x));
		// Tạo danh sách chỉ gồm name của sinh viên PART_TIME
		List<String> listPartTimeName = listStudent.stream()
				.filter(x -> x.getType() == StudentType.PART_TIME)
				.map(x -> x.getName())
				.collect(Collectors.toList());
		listPartTimeName.forEach(name -> System.out.println(name));
	}
}
