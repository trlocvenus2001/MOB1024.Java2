package service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PersonErrorService {
	public static List<Integer> readIntegerFile(String filename) {
		List<Integer> data = new ArrayList<>();
		BufferedReader br = null;
		try {
			File file = new File(filename);
			if (!file.exists()) {
				System.out.println(">> Lỗi: File không tồn tại!");
				return data;
			}

			br = new BufferedReader(new FileReader(file));
			String line;
			int lineCount = 0;
			while ((line = br.readLine()) != null) {
				lineCount++;
				try {
					line = line.trim();
					if (line.isEmpty())
						continue;
					int value = Integer.parseInt(line);
					data.add(value);
				} catch (NumberFormatException nfEx) {
					System.out.println(">> Lỗi: Dữ liệu không đúng định dạng ở dòng: " + line);
				}
			}
			if (lineCount == 0) {
				System.out.println(">> Lỗi: File rỗng!");
			} else {
				System.out.println(">> Đọc file thành công, dữ liệu: " + data);
			}
		} catch (FileNotFoundException e) {
			System.out.println(">> Lỗi: Không tìm thấy file!");
		} catch (IOException e) {
			System.out.println(">> Lỗi đọc file: " + e.getMessage());
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					System.out.println(">> Lỗi khi đóng file: " + e.getMessage());
				}
			}
		}
		return data;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Nhập tên file cần đọc (ví dụ: test.txt): ");
		String filename = sc.nextLine();
		List<Integer> result = readIntegerFile(filename);
		if (result.isEmpty()) {
			System.out.println(">> Lỗi: Không đọc được giá trị nào từ file hoặc có lỗi khi đọc file!");
		} else {
			System.out.println(">> Danh sách giá trị đọc được: " + result);
		}
		System.out.println("Kết thúc chương trình demo đọc file!");
		sc.close();
	}
}
