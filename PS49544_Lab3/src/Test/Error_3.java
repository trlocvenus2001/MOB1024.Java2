package Test; //Đoạn code 3: Lỗi thiếu Terminal Operation (Cơ chế Lazy)

import java.util.Arrays;
import java.util.List;

public class Error_3 {
	public static void main(String[] args) {
		List<String> languages = Arrays.asList("Java", "Python", "C++");

		System.out.println("Bắt đầu xử lý...");
		languages.stream().map(lang -> {
			String upper = lang.toUpperCase();
			System.out.println("Đang biến đổi: " + upper);
			return upper;
		});
		System.out.println("Hoàn thành!");
	}
}

//Loại lỗi: Lỗi logic (Logic Error).
//Chi tiết: Code chạy bình thường không báo đỏ, nhưng dòng chữ "Đang biến đổi: ..." bên trong biểu thức map hoàn toàn không được in ra màn hình.