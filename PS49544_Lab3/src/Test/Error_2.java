package Test; //Đoạn code 2: Lỗi sử dụng lại Stream đã đóng

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Error_2 {
	public static void main(String[] args) {
		List<String> names = Arrays.asList("An", "Binh", "Chau");
		Stream<String> nameStream = names.stream();

		// Thao tác kết thúc (Terminal Operation) lần 1
		nameStream.forEach(System.out::println);

		// Cố gắng dùng lại Stream đó cho thao tác lần 2
		long count = nameStream.filter(n -> n.startsWith("A")).count();
		System.out.println("Số người tên bắt đầu bằng A: " + count);
	}
}

//Loại lỗi: Lỗi khi chạy chương trình (Runtime Error).
//Chi tiết: Bắn ra ngoại lệ java.lang.IllegalStateException: stream has already been operated upon or closed tại dòng tính count.