package Test;

import java.util.Arrays;
import java.util.List;

public class Fix_1 {
	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
		// Dùng tính năng tính tổng tích hợp sẵn của Stream
		int sum = numbers.stream().mapToInt(Integer::intValue).sum();
		System.out.println("Tổng: " + sum);
	}
}

//Giải thích: Trong Java, biểu thức Lambda chỉ được phép đọc các biến cục bộ bên ngoài khối của nó, không được phép thay đổi (ghi đè) giá trị của chúng. Biến sum bị thay đổi giá trị liên tục nên vi phạm quy tắc "effectively final".
//Code sửa: Sử dụng toán tử của Stream API (như mapToInt và sum) thay vì cố gắng dùng vòng lặp để cộng dồn vào một biến bên ngoài.