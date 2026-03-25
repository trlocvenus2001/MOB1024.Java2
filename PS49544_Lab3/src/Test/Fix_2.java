package Test;

import java.util.Arrays;
import java.util.List;

public class Fix_2 {
	public static void main(String[] args) {
		List<String> names = Arrays.asList("An", "Binh", "Chau");

		names.stream().forEach(System.out::println);
		// Khởi tạo lại một Stream mới bằng names.stream()
		long count = names.stream().filter(n -> n.startsWith("A")).count();
		System.out.println("Số người tên bắt đầu bằng A: " + count);
	}
}

//Giải thích: Một đối tượng Stream trong Java chỉ có thể được tiêu thụ một lần duy nhất. Ngay khi phương thức kết thúc (Terminal Operation) như forEach được gọi, Stream đó sẽ bị đóng lại. Bạn không thể sử dụng lại nó để thực hiện filter hay count được nữa.
//Code sửa: Phải tạo một luồng Stream mới từ Collection ban đầu (danh sách names).