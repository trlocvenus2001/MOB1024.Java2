package Main;

import java.util.ArrayList;

public class mainLab3_1 {
	public static void main(String[] args) {
		ArrayList<String> listName = new ArrayList<>();
		listName.add("Trường Lộc");
		listName.add("Minh Nhật");
		listName.add("Ngọc Thịnh");
		listName.add("Minh Quân");
		listName.add("Yến Nhi");
		listName.add("Kiều My");
		listName.add("Mỹ Duyên");
		listName.add("Thúy An");
		listName.add("Minh Ngọc");
		listName.add("Bảo Trân");
		// In danh sách bằng forEach
		listName.forEach(x -> System.out.println(x));
		// Lọc và in các tên có đồ dài > 5
		listName.stream()
				.filter(x -> x.length() > 5)
				.forEach(x -> System.out.println(x));
		// Sắp xếp tên theo thứ tự từ A - Z
		listName.stream()
				.sorted((x1, x2) -> x1.compareTo(x2))
				.forEach(x -> System.out.println(x));
		listName.stream()
				.sorted((x1, x2) -> x1.length() - x2.length())
				.forEach(x -> System.out.println(x));
	}
}
