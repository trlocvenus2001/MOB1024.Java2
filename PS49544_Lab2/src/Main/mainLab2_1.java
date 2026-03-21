package Main;

import java.util.ArrayList;
import java.util.List;

import Entity.Student;
import Entity.StudentType;

public class mainLab2_1 {
	public static void main(String[] args) {
		List<Student> listStudent = new ArrayList<Student>();
		listStudent.add(new Student("SV01", "Huỳnh Trường Lộc", StudentType.REGULAR));
		listStudent.add(new Student("SV02", "Nguyễn Minh Nhật", StudentType.INTERNATIONAL));
		listStudent.add(new Student("SV03", "Vũ Minh Quân", StudentType.INTERNATIONAL));
		listStudent.add(new Student("SV04", "Phạm Ngọc Thịnh", StudentType.INTERNATIONAL));
		listStudent.add(new Student("SV05", "Phạm Văn Gia Bảo", StudentType.PART_TIME));
		listStudent.add(new Student("SV06", "Phạm Nguyên Thiên Toàn", StudentType.PART_TIME));

//		Yêu cầu kỹ thuật:
//		- Sử dụng ArrayList/List
//		- Sử dụng Enum đúng mục đích
//		- Không dùng if-else rời rạc cho loại sinh viên (phải dùng Enum)

		// Hiển thị tất cả thông tin sinh viên
		for (Student student : listStudent) {
			System.out.println(student);
		}

		// Hiện thị từng loại thông tin sinh viên
		for (StudentType type : StudentType.values()) {
			System.out.println(">> Kiểm tra loại sinh viên: " + type);
			for (Student student : listStudent) {
				if (student.getType() == type) {
					System.out.println(" + " + student.getName());
				}
			}
		}
		// Đếm số lượng sinh viên của mỗi loại
		for (StudentType type : StudentType.values()) {
			System.out.println(">> Kiểm tra loại sinh viên: " + type);
			int count = 0;
			for (Student student : listStudent) {
				if (student.getType() == type) {
					count++;
				}
			}
			System.out.println(type + " + " + count + "students");
		}
//		Không yêu cầu kỹ thuật:	
// 		Hiển thị tất cả thông tin sinh viên
//		for (Student student : listStudent) {
//			System.out.println(student.toString());
//		}
//
//		for (Student student : listStudent) {
//			if (student.getType() == StudentType.REGULAR) {
//				System.out.println(student.getName());
//			}
//			if (student.getType() == StudentType.PART_TIME) {
//				System.out.println(student.getName());
//			}
//			if (student.getType() == StudentType.INTERNATIONAL) {
//				System.out.println(student.getName());
//			}
//		}
//
//		int countRegular = 0, countPartTime = 0, countInternational = 0;
//
//		for (Student student : listStudent) {
//			if (student.getType() == StudentType.REGULAR) {
//				countRegular++;
//			}
//			if (student.getType() == StudentType.PART_TIME) {
//				countPartTime++;
//			}
//			if (student.getType() == StudentType.INTERNATIONAL) {
//				countInternational++;
//			}
//		}
//
//		System.out.println("Regular: " + countRegular);
//		System.out.println("Part_time: " + countPartTime);
//		System.out.println("International: " + countInternational);
	}
}
