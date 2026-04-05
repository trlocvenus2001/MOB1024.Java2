package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class mainLab5_1 {
	public static void main(String[] args) throws IOException {
		List<String> listName = Arrays.asList("Huỳnh Trường Lộc", "Nguyễn Minh Nhật", "Vũ Minh Quân",
				"Phạm Ngọc Thịnh");
		String filePath = "data.txt";
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(filePath));
			for (String name : listName) {
				bw.write(name);
				bw.newLine();
			}
			System.out.println(">> Đã ghi file");
			bw.close();
		} catch (IOException e) {
			// TODO: handle exception
			System.out.println(">> Lõi khi ghi file: " + e.getMessage());
		} finally {
			System.out.println(">> Kết thúc");
		}
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filePath));
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			System.out.println(">> Đã đọc file: " + filePath);
			// TODO: handle exception
			System.out.println(">> Lỗi khi đọc file: " + e.getMessage());
		} finally {
			br.close();
			System.out.println("Kết thúc");
		}
	}
}