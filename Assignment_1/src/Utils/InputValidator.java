package Utils;

import java.util.Scanner;

public class InputValidator {
	private static Scanner sc = new Scanner(System.in);

	public static String validateInput(int type, String str) {
		System.out.print(str);
		String text = "";
		while (true) {
			text = sc.nextLine().trim();
			if (text.isEmpty()) {
				System.out.println(">> Lỗi: Vui lòng không để trống.");
				System.out.print(str);
				continue;
			}

			if (type == 1 && text.matches(".*\\d.*")) {
				System.out.println(">> Lỗi: Trường này không được chứa chữ số.");
				System.out.print(str);
				continue;
			}

//			if (type == 2 && !text.matches("\\d+")) {
//				System.out.println(">> Lỗi: Vui lòng chỉ nhập số nguyên.");
//				System.out.print(str);
//				continue;
//			}
			return text;
		}
	}
}
