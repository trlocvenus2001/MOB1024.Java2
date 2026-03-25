package Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import Entity.Student;
import Entity.StudentType;

public class mainLab3_5 {
	public static void main(String[] args) { // collect, groupingBy, counting, summarizingDouble.
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
		// Dếm số sinh viên theo từng StudentType
		Map<StudentType, Long> countByType = listStudent.stream()
				.collect(Collectors.groupingBy(x -> x.getType(), Collectors.counting()));
		countByType.forEach((type, count) -> System.out.println(" -> " + type + ":" + count + "người"));
		// Tính GPA trung bình theo từng StudentType (Map<StudentType, Double>)
		Map<StudentType, Double> avgGpaByType = listStudent.stream()
				.collect(Collectors.groupingBy(x -> x.getType(), Collectors.averagingDouble(x -> x.getGpa())));
		avgGpaByType.forEach((type, avgGpa) -> System.out.printf(" -> %s: %.2f\n", type, avgGpa));
		// In loại sinh viên có GPA trung bình cao nhất
		Map.Entry<StudentType, Double> maxEntry = avgGpaByType.entrySet().stream()
				.max((entry1, entry2) -> Double.compare(entry1.getValue(), entry2.getValue()))
				.orElse(null);
		if (maxEntry != null) {
			System.out.printf(" -> %s - Với mức điểm: %.2f\n", maxEntry.getKey(), maxEntry.getValue());
		}
	}
}
