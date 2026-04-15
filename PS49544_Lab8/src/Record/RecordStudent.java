package Record;

import java.util.ArrayList;
import java.util.List;

public record RecordStudent(String id, String name, double gpa) {
	public RecordStudent {
		if (gpa < 0 || gpa > 4) {
			throw new IllegalArgumentException("Lỗi: GPA không hợp lệ");
		}
	}

	public boolean isScholarshopEligible() {
		return gpa >= 3.2;
	}

	public static void main(String[] args) {
		List<RecordStudent> students = new ArrayList<>();
		students.add(new RecordStudent("SV01", "Lộc A", 3.8));
		students.add(new RecordStudent("SV02", "Lộc B", 2.5));
		students.add(new RecordStudent("SV03", "Lộc C", 3.5));
//        students.add(new RecordStudent("SV03", "Lộc D", 4.5));
		System.out.println("Danh sách sinh viên đạt học bổng");
		for (RecordStudent rs : students) {
			if (rs.isScholarshopEligible()) {
				System.out.println(rs);
			}
		}
	}
}
