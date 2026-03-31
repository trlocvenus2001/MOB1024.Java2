package Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Entity.Copy;
import Entity.Document;
import Enum.Status;
import Exception.DuplicateIdException;
import Service.DocumentService;
import Service.DocumentServiceImplements;
import Utils.InputValidator;

public class mainAssignment {
	// Đẩy service và filePath ra ngoài làm biến static để các hàm menu khác có thể
	// xài chung
	private static final DocumentService service = new DocumentServiceImplements();
	private static final String FILE_PATH = "dulieu.txt";
	private static final Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		loadData();
		int choice = -1;
		do {
			printMainMenu();
			String input = InputValidator.validateInput(2, "Mời bạn chọn chức năng (0-5): ");
			choice = Integer.parseInt(input);

			try {
				switch (choice) {
				case 1:
					addDocument();
					pauseScreen();
					break;
				case 2:
					readDocument();
					pauseScreen();
					break;
				case 3:
					updateDocument();
					pauseScreen();
					break;
				case 4:
					deleteDocument();
					pauseScreen();
					break;
				case 5:
					menuCopy();
					break;
				case 6:
					display();
					pauseScreen();
					break;
				case 0:
					exit();
					break;
				default:
					System.out.println(">> Lựa chọn không hợp lệ!");
					pauseScreen();
				}
			} catch (Exception e) {
				System.out.println(">> Lỗi hệ thống: " + e.getMessage());
				pauseScreen();
			}
		} while (choice != 0);
	}

	private static void display() {
		// TODO Auto-generated method stub
		List<Document> listDocument = service.getAllDocuments();
		for (Document doc : listDocument) {
			System.out.println(">> Document: [" + doc.getId() + "]" + doc.getTitle());
			for (Copy c : doc.getListCopy()) {
				System.out.println(">> Copy ID: " + c.getId() + " | Status:" + c.getStt());
			}
		}
	}

	private static void addDocument() {
		try {
			Document newDoc = new Document();
			newDoc.setId(InputValidator.validateInput(0, "Nhập mã tài liệu: "));
			newDoc.setTitle(InputValidator.validateInput(0, "Nhập tên sách: "));
			newDoc.setAuthor(InputValidator.validateInput(1, "Nhập tác giả: "));
			newDoc.setCategory(InputValidator.validateInput(1, "Nhập thể loại: "));
			service.addDocument(newDoc);
			System.out.println(">> Thêm tài liệu thành công!");
		} catch (DuplicateIdException e) {
			System.out.println(">> Lỗi trùng mã: " + e.getMessage());
		} catch (Exception e) {
			System.out.println(">> Lỗi nghiệp vụ: " + e.getMessage());
		}
	}

	private static void readDocument() {
		if (service.getAllDocuments().isEmpty()) {
			System.out.println(">> Kho sách đang trống!");
		} else {
			for (Document d : service.getAllDocuments()) {
				System.out.println(d.toString());
				List<Copy> listCopy = d.getListCopy();
				if (listCopy.isEmpty()) {
					System.out.println(">> Tài liệu chưa có bản sao nào trong kho!");
				} else {
					System.out.print(">> Chi tiết bản sao: ");
					for (Copy copy : listCopy) {
						System.out.println("[" + copy.getId() + " - " + copy.getStt() + "]");
					}
				}
			}
		}
	}

	private static void updateDocument() {
		try {
			Document updateDoc = new Document();
			updateDoc.setId(InputValidator.validateInput(0, "Nhập mã tài liệu cần sửa: "));
			updateDoc.setTitle(InputValidator.validateInput(0, "Nhập tên sách mới: "));
			updateDoc.setAuthor(InputValidator.validateInput(1, "Nhập tác giả mới: "));
			updateDoc.setCategory(InputValidator.validateInput(1, "Nhập thể loại mới: "));

			service.updateDocument(updateDoc);
			System.out.println(">> Cập nhật thành công!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void deleteDocument() {
		try {
			String delId = InputValidator.validateInput(0, "Nhập mã tài liệu cần xóa: ");
			service.deleteDocument(delId);
			System.out.println("=> Xóa thành công!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void menuCopy() {
		int choose = -1;
		do {
			printCopyMenu();
			String input = InputValidator.validateInput(2, "Chọn chức năng (0-4): ");
			choose = Integer.parseInt(input);
			try {
				switch (choose) {
				case 1:
					addCopy();
					pauseScreen();
					break;
				case 2:
					readCopy();
					pauseScreen();
					break;
				case 3:
					updateCopy();
					pauseScreen();
					break;
				case 4:
					deleteCopy();
					pauseScreen();
					break;
				case 0:
					break;
				default:
					System.out.println(">> Lựa chọn không hợp lệ.");
					pauseScreen();
				}
			} catch (Exception e) {
				System.out.println(">> Lỗi nghiệp vụ: " + e.getMessage());
				pauseScreen();
			}
		} while (choose != 0);
	}

	private static void addCopy() throws Exception {
		System.out.println(">> THÊM BẢN SAO");
		String docId = InputValidator.validateInput(0, "Nhập mã tài liệu gốc: ");
		String copyId = InputValidator.validateInput(0, "Nhập mã bản sao mới: ");
		Status stt = chooseStatus();

		service.addCopyDocument(docId, new Copy(copyId, stt));
		System.out.println(">> Thêm bản sao thành công!");
	}

	private static void readCopy() throws Exception {
		System.out.println(">> XEM BẢN SAO");
		String searchId = InputValidator.validateInput(0, "Nhập mã tài liệu: ");

		if (service.getCopyIdDocument(searchId).isEmpty()) {
			System.out.println("Tài liệu này chưa có bản sao nào.");
		} else {
			for (Copy copy : service.getCopyIdDocument(searchId)) {
				System.out.println(copy.toString());
			}
		}
	}

	private static void updateCopy() throws Exception {
		System.out.println("\n--- ĐỔI TRẠNG THÁI BẢN SAO ---");
		String uDocId = InputValidator.validateInput(0, "Nhập mã tài liệu: ");
		String uCopyId = InputValidator.validateInput(0, "Nhập mã bản sao cần đổi trạng thái: ");
		Status newStt = chooseStatus();

		service.updateCopyDocument(uDocId, uCopyId, newStt);
		System.out.println("=> Cập nhật trạng thái thành công!");
	}

	private static void deleteCopy() throws Exception {
		System.out.println("\n--- XÓA BẢN SAO ---");
		String dDocId = InputValidator.validateInput(0, "Nhập mã tài liệu: ");
		String dCopyId = InputValidator.validateInput(0, "Nhập mã bản sao cần xóa: ");

		service.deleteCopyDocument(dDocId, dCopyId);
		System.out.println("=> Xóa bản sao thành công!");
	}

	private static void loadData() {
		try {
			service.loadFile(FILE_PATH);
			if (service.getAllDocuments().isEmpty()) {
				System.out.println(">>> File trống hoặc chưa có dữ liệu, tự động tạo dữ liệu mẫu...");
				test();
			} else {
				System.out.println(">>> Đã tải dữ liệu thành công từ: " + FILE_PATH);
			}

		} catch (Exception e) {
			System.out.println(">>> Lỗi định dạng file, hệ thống tự động tạo dữ liệu mới...");
			test();
		}

	}

	private static void exit() {
		try {
			service.saveFile(FILE_PATH);
			System.out.println("=> Đã lưu dữ liệu. Tạm biệt!");
		} catch (Exception e) {
			System.out.println(">> Lỗi khi lưu file: " + e.getMessage());
		}
	}

	private static Status chooseStatus() {
		System.out.println(">> Trạng thái: 1. GOOD | 2. DAMAGE | 3. LOST");
		while (true) {
			String choice = InputValidator.validateInput(2, "Chọn trạng thái (1-3): ");
			switch (choice) {
			case "1":
				return Status.GOOD;
			case "2":
				return Status.DAMAGE;
			case "3":
				return Status.LOST;
			default:
				System.out.println(">> Vui lòng chỉ chọn từ 1 đến 3.");
			}
		}
	}

	private static void printMainMenu() {
		cleanScreen();
		System.out.println("=====================================");
		System.out.println("      HỆ THỐNG QUẢN LÝ THƯ VIỆN      ");
		System.out.println("=====================================");
		System.out.println("1. Thêm tài liệu mới");
		System.out.println("2. Xem danh sách tài liệu");
		System.out.println("3. Cập nhật tài liệu");
		System.out.println("4. Xóa tài liệu");
		System.out.println("5. Quản lý Bản Sao (Nhập kho/Báo hỏng)");
		System.out.println("6. Xuất toàn bộ tài liệu");
		System.out.println("0. Lưu dữ liệu & Thoát");
		System.out.println("-------------------------------------");
	}

	private static void printCopyMenu() {
		cleanScreen();
		System.out.println("--- QUẢN LÝ BẢN SAO ---");
		System.out.println("1. Thêm bản sao (Nhập kho)");
		System.out.println("2. Xem các bản sao của 1 tài liệu");
		System.out.println("3. Đổi trạng thái bản sao (Báo mất/hỏng)");
		System.out.println("4. Xóa bản sao");
		System.out.println("0. Quay lại menu chính");
		System.out.println("-----------------------");
	}

	private static void cleanScreen() {
		for (int i = 0; i < 50; i++) {
			System.out.println();
		}
	}

	private static void pauseScreen() {
		System.out.println("\n>> Nhấn Enter để tiếp tục...");
		sc.nextLine();
	}

	private static void test() {
		try {
			List<Document> doc = new ArrayList<>();
			Document d1 = new Document("DOC01", "Sức khỏe", "Y Dược", "Giáo trình");
			d1.addCopy(new Copy("C01.1", Status.GOOD));
			d1.addCopy(new Copy("C01.2", Status.DAMAGE));
			doc.add(d1);
			Document d2 = new Document("DOC02", "Đắc Nhân Tâm", "Dale Carnegie", "Kỹ năng");
			d2.addCopy(new Copy("C02.1", Status.GOOD));
			doc.add(d2);
			Document d3 = new Document("DOC03", "Bình tĩnh", "Bản thân.", "Nhật ký");
			doc.add(d3);
			for (Document d : doc) {
				service.addDocument(d);
			}
			System.out.println(">>> Đã tự động tạo danh sách dữ liệu mẫu thành công!");
		} catch (Exception e) {
			System.out.println(">> Lỗi khi tạo dữ liệu mẫu: " + e.getMessage());
		}
	}

}
