package Main;

import Record.RecordStudent;

public class ReportStudent {
	public static void main(String[] args) {
		RecordStudent st = new RecordStudent("SV01", "Loc Polo", 3.9);
		System.out.println("1. THÔNG BÁO CHÀO MỪNG: ");
		String welcome = """
				=============================================
				            * HỆ THỐNG QUẢN LÝ SINH VIÊN POLYTECHNIC  *
				            * PHÂN HỆ XUẤT BÁO CÁO TỰ ĐỘNG            *
				            =============================================
				""";
		System.out.println(welcome);
		System.out.println("2. EMAIL THÔNG BÁO HỌC BỔNG: ");
		String emailTemplate = """
				Thân gửi sinh viên %s,

				            Phòng Đào tạo chúc mừng bạn đã đạt điểm GPA %.2f trong kỳ học vừa qua.
				            Với thành tích này, bạn đã đủ điều kiện xét duyệt học bổng.
				            Vui lòng kiểm tra cổng thông tin để biết thêm chi tiết.

				            Trân trọng!
				""".formatted(st.name(), st.gpa());
		System.out.println(emailTemplate);
		System.out.println("3. HTML PROFILE: ");
		String htmlProfile = """
				<div class="card" style="width: 18rem;">
				              <div class="card-body">
				                <h5 class="card-title">Mã SV: %s</h5>
				                <p class="card-text">Họ và tên: <strong>%s</strong></p>
				                <p class="card-text text-success">Điểm tích lũy: %.2f</p>
				              </div>
				            </div>
				""".formatted(st.id(), st.name(), st.gpa());
		System.out.println(htmlProfile);
		System.out.println("4. SQL Query; ");
		String sqlQuery = """
				UPDATE students
				            SET name = '%s', gpa = %.2f
				            WHERE id = '%s';
				""".formatted(st.name(), st.gpa(), st.id());
		System.out.println(sqlQuery);
	}
}
