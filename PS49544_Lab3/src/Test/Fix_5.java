package Test;

import java.util.Arrays;
import java.util.List;

public class Fix_5 {
	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(5, 12, 8, 20);

		long count = numbers.stream().filter(n -> {
			System.out.println("Đang kiểm tra số: " + n);
			return n > 10; // Phải có từ khóa return khi nằm trong khối {}
		}).count();

		System.out.println("Số lượng phần tử > 10 là: " + count);
	}
}
