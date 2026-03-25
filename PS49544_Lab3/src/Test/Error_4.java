package Test; //Đoạn code 4: Lỗi so sánh chuỗi bằng == trong biểu thức Lambda

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Error_4 {
	public static void main(String[] args) {
		List<String> students = Arrays.asList("Tuan", "Mai", "Hoa", new String("Mai"));

		List<String> result = students.stream().filter(s -> s == "Mai") // Lỗi logic ở dòng này
				.collect(Collectors.toList());

		System.out.println("Tìm thấy: " + result.size() + " người tên Mai");
	}
}

//Loại lỗi: Lỗi logic.
//Chi tiết: Kết quả trả về có thể thiếu phần tử (size không bằng 2 như kỳ vọng) vì đối tượng new String("Mai") sẽ không được bộ lọc giữ lại.