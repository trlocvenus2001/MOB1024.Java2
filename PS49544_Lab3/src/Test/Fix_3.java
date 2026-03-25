package Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Fix_3 {
	public static void main(String[] args) {
		List<String> languages = Arrays.asList("Java", "Python", "C++");

		System.out.println("Bắt đầu xử lý...");
		languages.stream().map(lang -> {
			String upper = lang.toUpperCase();
			System.out.println("Đang biến đổi: " + upper);
			return upper;
		}).collect(Collectors.toList()); // Thêm Terminal Operation để kích hoạt luồng
		System.out.println("Hoàn thành!");
	}
}

//Giải thích: Stream API hoạt động theo cơ chế Lazy Evaluation (Thực thi lười biếng). Nghĩa là các thao tác trung gian (Intermediate Operations) như filter hay map sẽ chỉ được ghi nhận chứ không hề chạy thực tế, cho đến khi hệ thống bắt gặp một thao tác kết thúc (Terminal Operation) ở cuối cùng.
//Code sửa: Thêm một Terminal Operation như collect hoặc count vào cuối chuỗi.
