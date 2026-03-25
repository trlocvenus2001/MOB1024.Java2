package Test; //Đoạn code 5: Lỗi thiếu từ khóa return trong khối lệnh Lambda nhiều dòng

import java.util.ArrayList;
import java.util.List;

public class Error_5 {
	public static void main(String[] args) {
        List<Integer> numbers = ArrayList.asList(5, 12, 8, 20);
        
        long count = numbers.stream()
            .filter(n -> {
                System.out.println("Đang kiểm tra số: " + n);
                n > 10; // Lỗi biên dịch ở dòng này
            })
            .count();
            
        System.out.println("Số lượng phần tử > 10 là: " + count);
    }
}

//Loại lỗi: Lỗi biên dịch (Compilation Error).
//Chi tiết: Trình biên dịch báo "Not a statement" tại n > 10; và "Missing return statement" tại khối lệnh của filter.