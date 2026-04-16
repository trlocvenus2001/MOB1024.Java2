package main;

import static util.LambdaUtil.wrapWithLogging;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import model.Copy;
import model.Document;
import model.Status;
import repository.CopyDAO;
import repository.DocumentDAO;
import util.DatabaseException;

public class MainApp {
	private static final Scanner sc = new Scanner(System.in);
	private static final DocumentDAO docDAO = new DocumentDAO();
	private static final CopyDAO copyDAO = new CopyDAO();

	public static void main(String[] args) {
		String opt;
		do {
			clearScreen();
			displayMenu();
			opt = sc.nextLine();
			switch (opt) {
			case "1":
				addDoc();
				break;
			case "2":
				editDoc();
				break;
			case "3":
				delDoc();
				break;
			case "4":
				viewDoc();
				break;
			case "5":
				searchDoc();
				break;
			case "6":
				sortDocs();
				break;
			case "7":
				showAll();
				break;
			case "8":
				addCopies();
				break;
			case "9":
				editCopy();
				break;
			case "10":
				editAllCopies();
				break;
			case "11":
				delCopy();
				break;
			case "12":
				scanCopies();
				break;
			case "0":
				System.out.println("Đã thoát chương trình. Hẹn gặp lại!");
				break;
			default:
				System.out.println("Lựa chọn không hợp lệ! Vui lòng nhập từ 0 đến 12.");
			}
			if (!opt.equals("0"))
				pauseScreen();
		} while (!opt.equals("0"));
	}

	private static void displayMenu() {
		System.out.println("\n+------------------------------------------------------------+");
		System.out.println("|                HỆ THỐNG QUẢN LÝ THƯ VIỆN (JDBC)            |");
		System.out.println("+------+-------------------------+------+----------------------+");
		System.out.println("|  STT |     QUẢN LÝ TÀI LIỆU    |  STT |   QUẢN LÝ BẢN SAO    |");
		System.out.println("+------+-------------------------+------+----------------------+");
		System.out.println("|  1   | Thêm tài liệu mới       |  8   | Thêm bản sao mới     |");
		System.out.println("|  2   | Cập nhật tài liệu       |  9   | Cập nhật bản sao     |");
		System.out.println("|  3   | Xoá tài liệu            |  10  | Cập nhật theo mã gốc |");
		System.out.println("|  4   | Xem chi tiết (Join)     |  11  | Xoá bản sao          |");
		System.out.println("|  5   | Tìm kiếm theo tiêu chí  |  12  | Kiểm tra bản sao     |");
		System.out.println("|  6   | Sắp xếp danh sách       |      |                      |");
		System.out.println("|  7   | Hiển thị tất cả tài liệu|      |                      |");
		System.out.println("+------+-------------------------+------+----------------------+");
		System.out.println("|  0   | Thoát chương trình                                    |");
		System.out.println("+------+-------------------------------------------------------+");
		System.out.print("  ==> Lựa chọn của bạn: ");
	}

	private static void clearScreen() {
		for (int i = 0; i < 50; i++) {
			System.out.println();
		}
	}

	private static void pauseScreen() {
		System.out.print("\n[ Nhấn phím Enter để tiếp tục... ]");
		sc.nextLine();
	}

	// --- CÁC HÀM XỬ LÝ (TÊN NGẮN GỌN) ---

	private static void addDoc() {
		System.out.println("\n--- [1] THÊM TÀI LIỆU ---");
		System.out.print("Mã tài liệu: ");
		String id = sc.nextLine();
		System.out.print("Tên tài liệu: ");
		String title = sc.nextLine();
		System.out.print("Tác giả: ");
		String author = sc.nextLine();
		System.out.print("Thể loại: ");
		String cat = sc.nextLine();
		if (docDAO.add(new Document(id, title, author, cat)))
			System.out.println(" Thêm thành công!");
	}

	private static void editDoc() {
		System.out.println("\n--- [2] CẬP NHẬT TÀI LIỆU ---");
		System.out.print("Nhập mã cần sửa: ");
		String uId = sc.nextLine();
		if (docDAO.getById(uId) == null) {
			System.out.println(" Không tìm thấy!");
			return;
		}
		System.out.print("Tên mới: ");
		String uTitle = sc.nextLine();
		System.out.print("Tác giả mới: ");
		String uAuthor = sc.nextLine();
		System.out.print("Thể loại mới: ");
		String uCat = sc.nextLine();
		if (docDAO.update(new Document(uId, uTitle, uAuthor, uCat)))
			System.out.println(" Cập nhật thành công!");
	}

	private static void delDoc() {
		System.out.println("\n--- [3] XÓA TÀI LIỆU ---");
		System.out.print("Nhập mã cần xóa: ");
		String dId = sc.nextLine();
		if (docDAO.delete(dId))
			System.out.println(" Xóa thành công!");
	}

	private static void viewDoc() {
		System.out.println("\n--- [4] XEM CHI TIẾT ---");
		System.out.print("Nhập mã: ");
		String sId = sc.nextLine();
		Document d = docDAO.getWithCopies(sId);
		if (d != null) {
			System.out.println(d);
			if (d.getCopies().isEmpty())
				System.out.println("   (Chưa có bản sao)");
			else
				d.getCopies().forEach(System.out::println);
		} else
			System.out.println(" Không tìm thấy!");
	}

	private static void searchDoc() {
		System.out.println("\n--- [5] TÌM KIẾM ---");
		System.out.print("Từ khóa: ");
		String kw = sc.nextLine();
		List<Document> res = docDAO.search(kw);
		if (res.isEmpty())
			System.out.println("Không tìm thấy!");
		else
			res.forEach(System.out::println);
	}

	private static void sortDocs() {
		System.out.println("\n--- [6] SẮP XẾP ---");
		List<Document> list = docDAO.getAll();
		if (list.isEmpty()) {
			System.out.println("Trống!");
			return;
		}
		list.stream().sorted(Comparator.comparing(Document::getTitle)).forEach(System.out::println);
	}

	private static void showAll() {
		System.out.println("\n--- [7] HIỂN THỊ TẤT CẢ TÀI LIỆU & BẢN SAO ---");
		List<Document> list = docDAO.getAllWithCopies();
		if (list.isEmpty()) {
			System.out.println("📭 Thư viện trống.");
			return;
		}
		list.forEach(doc -> {
			System.out.println("Sách: " + doc);
			List<Copy> copiesOfThisDoc = doc.getCopies();
			if (copiesOfThisDoc.isEmpty()) {
				System.out.println("   (⚠️ Chưa có bản sao)");
			} else {
				for (int i = 0; i < copiesOfThisDoc.size(); i++) {
					System.out.println("   " + (i + 1) + ". " + copiesOfThisDoc.get(i));
				}
			}
			System.out.println("------------------------------------------");
		});
	}

	private static void addCopies() {
		System.out.println("\n--- [8] THÊM LÔ BẢN SAO ---");
		System.out.print("Mã sách gốc: ");
		String cId = sc.nextLine();
		System.out.print("Số lượng: ");
		try {
			copyDAO.addBatch(cId, Integer.parseInt(sc.nextLine()));
		} catch (NumberFormatException e) {
			System.out.println(" Vui lòng nhập số!");
		}
	}

	private static void editCopy() {
		System.out.println("\n--- [9] CẬP NHẬT 1 BẢN SAO ---");
		System.out.print("Mã bản sao: ");
		String cpId = sc.nextLine();
		System.out.print("Trạng thái (GOOD/DAMAGED/LOST): ");
		try {
			if (copyDAO.updateStatus(cpId, Status.valueOf(sc.nextLine().toUpperCase())))
				System.out.println(" Thành công!");
			else
				System.out.println(" Thất bại.");
		} catch (Exception e) {
			System.out.println(" Sai trạng thái!");
		}
	}

	private static void editAllCopies() {
		System.out.println("\n--- [10] CẬP NHẬT ĐỒNG LOẠT ---");
		System.out.print("Mã sách gốc: ");
		String docId = sc.nextLine();
		System.out.print("Trạng thái (GOOD/DAMAGED/LOST): ");
		try {
			if (copyDAO.updateStatusByDoc(docId, Status.valueOf(sc.nextLine().toUpperCase())))
				System.out.println("Thành công!");
			else
				System.out.println("Thất bại.");
		} catch (Exception e) {
			System.out.println("Sai trạng thái!");
		}
	}

	private static void delCopy() {
		System.out.println("\n--- [11] XÓA BẢN SAO ---");
		System.out.print("Mã bản sao: ");
		String cpId = sc.nextLine();
		if (copyDAO.delete(cpId))
			System.out.println("Xóa thành công!");
		else
			System.out.println("Thất bại.");
	}

	private static void scanCopies() {
		System.out.println("\n--- [12] QUÉT KIỂM TRA (STREAM API) ---");
		copyDAO.getAll().stream().forEach(wrapWithLogging(c -> {
			if (c.getStatus() == Status.LOST)
				throw new DatabaseException(404, "MẤT bản sao: " + c.getId());
			System.out.println(c.getId() + " - " + c.getStatus() + " -> OK");
		}));
	}
}