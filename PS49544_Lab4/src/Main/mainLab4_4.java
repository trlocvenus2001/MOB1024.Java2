package Main;

import Entity.GenericManager;
import Exception.EmptyListException;

public class mainLab4_4 {
	public static void main(String[] args) {
		GenericManager<String> managerString = new GenericManager<>();
		try {
			System.out.println(">> Thử getFirst(): ");
			managerString.getFirst();
		} catch (EmptyListException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

		try {
			System.out.println(">> Thử getLast(): ");
			managerString.getLast();
		} catch (EmptyListException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		managerString.add("Lộc");
		managerString.add("Nhật");
		managerString.add("Quân");
		managerString.add("Thịnh");
		managerString.display();
		try {
			String first = managerString.getFirst();
			String last = managerString.getLast();
			System.out.println(">> Dữ liệu đầu tiên" + first);
			System.out.println(">> Dữ liệu cuối cùng" + last);
			System.out.println(">> Danh sách dữ liệu: " + managerString.getAll().size());
		} catch (EmptyListException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
}
