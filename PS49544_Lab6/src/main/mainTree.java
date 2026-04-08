package main;

import java.util.List;
import java.util.Scanner;

import model.Tree;
import repository.TreeRepository;

public class mainTree {
	private static final TreeRepository treer = new TreeRepository();
	private static final Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int choice = -1;
		do {
			menu();
			try {
				choice = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				choice = -1;
			}
			switch (choice) {
			case 1:
				showAllTrees();
				break;
			case 2:
				addTree();
				break;
			case 3:
				updateTree();
				break;
			case 4:
				deleteTree();
				break;
			case 5:
				findbyIdTree();
				break;
			case 0:
				System.out.println(">> Đã thoát chương trình!");
				break;
			default:
				System.out.println(">> Lựa chọn không phù hợp!");
				break;
			}
		} while (choice != 0);
		sc.close();
	}

	private static void menu() {
		System.out.println("|---------------------------------------|");
		System.out.println("|              Quản lý Tree             |");
		System.out.println("|---------------------------------------|");
		System.out.println("| 1. Hiển thị danh sách Tree            |");
		System.out.println("| 2. Thêm Node mới                      |");
		System.out.println("| 3. Cập nhật Node                      |");
		System.out.println("| 4. Xóa Node                           |");
		System.out.println("| 5. Tìm Node theo ID                   |");
		System.out.println("| 0. Thoát                              |");
		System.out.println("|---------------------------------------|");
		System.out.print(">> Chọn chức năng: ");
	}

	private static void showAllTrees() {
		List<Tree> list = treer.findAll();
		if (list == null || list.isEmpty()) {
			System.out.println(">> Danh sách trống hoặc có lỗi lấy dữ liệu từ MySQL!");
			return;
		}
		for (Tree t : list) {
			String prefix = "";
			if (t.getLevel() > 1) {
				for (int i = 1; i < t.getLevel(); i++) {
					prefix += "   ";
				}
				prefix += "|_ ";
			}
			System.out.println(prefix + "[" + t.getNode_id() + "]" + t.getNode_name() + " (Level: " + t.getLevel()
					+ ", Parent: " + t.getParent_id() + ")");
		}
	}

	private static void addTree() {
		System.out.print(">> Tên Node mới: ");
		String name = sc.nextLine();

		System.out.print(">> Nhập ID của Node cha (Bấm Enter nếu đây là Menu Gốc - NULL): ");
		String parentInput = sc.nextLine().trim();
		Integer parentId = null;
		if (!parentInput.isEmpty()) {
			try {
				parentId = Integer.parseInt(parentInput);
			} catch (NumberFormatException ex) {
				System.out.println("Parent ID không hợp lệ!");
				return;
			}
		}

		System.out.print(">> Cấp độ (Level): ");
		int level;
		try {
			level = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException ex) {
			System.out.println("Cấp độ không hợp lệ!");
			return;
		}

		Tree t = new Tree(0, name, parentId, level);
		if (treer.add(t)) {
			System.out.println(">> Đã thêm thành công!");
		} else {
			System.out.println(">> Không thể thêm Node này.");
		}
		showAllTrees();
	}

	private static void updateTree() {
		System.out.print(">> ID Node cần cập nhật: ");
		int id;
		try {
			id = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException ex) {
			System.out.println("ID không hợp lệ!");
			return;
		}
		Tree exist = treer.findbyId(id);

		if (exist == null) {
			System.out.println(">> Không tìm thấy Node này!");
			return;
		}

		System.out.print(">> Tên mới (Enter để giữ nguyên [" + exist.getNode_name() + "]): ");
		String newName = sc.nextLine().trim();
		if (!newName.isEmpty()) {
			exist.setNode_name(newName);
		}

		System.out.print(">> Parent ID mới (Enter để giữ nguyên [" + exist.getParent_id() + "]): ");
		String newParent = sc.nextLine().trim();
		if (!newParent.isEmpty()) {
			if (newParent.equalsIgnoreCase("null")) {
				exist.setParent_id(null);
			} else {
				try {
					exist.setParent_id(Integer.parseInt(newParent));
				} catch (NumberFormatException ex) {
					System.out.println(">> Parent ID không hợp lệ!");
					return;
				}
			}
		}

		System.out.print(">> Level mới (Enter để giữ nguyên [" + exist.getLevel() + "]): ");
		String newLevel = sc.nextLine().trim();
		if (!newLevel.isEmpty()) {
			try {
				exist.setLevel(Integer.parseInt(newLevel));
			} catch (NumberFormatException ex) {
				System.out.println("Level không hợp lệ!");
				return;
			}
		}

		if (treer.update(exist)) {
			System.out.println(">> Cập nhật thành công!");
		} else {
			System.out.println(">> Cập nhật thất bại!");
		}
		showAllTrees();
	}

	private static void deleteTree() {
		System.out.print(">> ID Node cần xóa: ");
		int id;
		try {
			id = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException ex) {
			System.out.println("ID không hợp lệ!");
			return;
		}
		if (treer.delete(id)) {
			System.out.println(">> Xóa thành công!");
		} else {
			System.out.println(">> Xóa thất bại!");
		}
	}

	private static void findbyIdTree() {
		System.out.print(">> Nhập ID cần tìm: ");
		int id;
		try {
			id = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException ex) {
			System.out.println(">> ID không hợp lệ!");
			return;
		}
		Tree tr = treer.findbyId(id);
		if (tr != null) {
			System.out.println(tr.toString());
		} else {
			System.out.println(">> Không tìm thấy Node này.");
		}
	}
}