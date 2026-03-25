package Test; //Đoạn code 1: Lỗi thay đổi biến cục bộ (Effectively Final)

import java.util.Arrays;
import java.util.List;

public class Error_1 {
	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
		int sum = 0;
		numbers.forEach(n -> {
			sum += n; // Dòng code gây lỗi
		});
		System.out.println("Tổng: " + sum);
	}
}

//Loại lỗi: Lỗi biên dịch (Compilation Error).
//Chi tiết: Java sẽ báo lỗi "Variable used in lambda expression should be final or effectively final" tại dòng sum += n;.
