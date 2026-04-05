package service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class PersonService {
	public static <T> void saveFile(List<T> list, String filename) {
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(filename));
			oos.writeObject(list);
			oos.flush();
			System.out.println();
		} catch (IOException e) {
			// TODO: handle exception
			System.out.println(">> Lỗi ghi file: " + e.getMessage());
		} finally {
			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					// TODO: handle exception
					System.out.println(">> Lỗi đóng file: " + e.getMessage());
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> List<T> readFile(String filename) {
		ObjectInputStream ois = null;
		List<T> list = new ArrayList<>();
		try {
			ois = new ObjectInputStream(new FileInputStream(filename));
			list = (List<T>) ois.readObject();
		} catch (IOException e) {
			// TODO: handle exception
			System.out.println(">> Lỗi đọc file: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			System.out.println(">> Lỗi class không tồn tại: " + e.getMessage());
		} finally {
			// TODO: handle finally clause
			try {
				ois.close();
			} catch (IOException e) {
				// TODO: handle exception
				System.out.println(">> Lỗi đóng file: " + e.getMessage());
			}
		}
		return list;
	}
}
