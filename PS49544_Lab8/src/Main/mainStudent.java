package Main;

import java.util.ArrayList;
import java.util.Scanner;

public class mainStudent {
	public static void main(String[] args) {
		ArrayList<Integer> scores = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		System.out.println(">> Điểm: (gõ 'null' hoặc để trống nếu chưa có điểm");
		for (int i = 0; i < 5; i++) {
			System.out.println("Điểm SV" + (i + 1) + ": ");
			String inScore = sc.nextLine();
			if (inScore.equalsIgnoreCase("null") || (inScore.trim().isEmpty())) {
				scores.add(null);
			} else {
				scores.add(Integer.parseInt(inScore));
			}
		}
		System.out.println("Danh sách điểm ban đầu: " + scores);
		int sum = 0;
		int count = 0;
		for (Integer score : scores) {
			if (score != null) {
				sum += score;
				count++;
			}
		}

		if (count > 0) {
			double average = count > 0 ? (double) sum / count : 0;
			String classification = "";
			System.out.printf("Điểm trung bình: %.2f\n", average);
			if (average >= 9) {
				classification = "Xuất sắc";
			} else if (average >= 8) {
				classification = "Giỏi";
			} else if (average >= 6.5) {
				classification = "Khá";
			} else if (average >= 5) {
				classification = "Trung bình";
			} else if (average >= 3.5) {
				classification = "Yếu";
			} else {
				classification = "Kém";
			}
			String result = """
					=== KẾT QUẢ HỌC TẬP CỦA LỘC POLO ===
					           Danh sách điểm gốc: %s
					           Tổng số sinh viên có điểm: %d
					           Điểm trung bình: %.2f
					           Phân loại: %s
					           ====================================
					""".formatted(scores, count, average, classification);
			System.out.println(result);
		} else {
			System.out.println("Chưa có sinh viên nào có điểm để tính trung bình và xếp loại.");
		}
		sc.close();
	}
}
