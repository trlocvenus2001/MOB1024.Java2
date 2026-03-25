package Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Fix_4 {
	public static void main(String[] args) {
		List<String> students = Arrays.asList("Tuan", "Mai", "Hoa", new String("Mai"));

		List<String> result = students.stream().filter(s -> s.equals("Mai")) // Dùng equals() để so sánh nội dung chuỗi
				.collect(Collectors.toList());

		System.out.println("Tìm thấy: " + result.size() + " người tên Mai");
	}
}

//Giải thích: Toán tử == trong Java được dùng để kiểm tra xem hai đối tượng có trỏ đến cùng một địa chỉ bộ nhớ hay không. Khi làm việc với chuỗi (String) hoặc các Object khác trong Stream filter, bạn phải dùng phương thức .equals() để so sánh giá trị nội dung của chúng.
//Code sửa: Đổi == thành .equals().