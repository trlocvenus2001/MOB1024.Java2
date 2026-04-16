package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLSyntaxErrorException;
import java.sql.SQLTimeoutException;

import util.ExceptionLogging;

public class DBConnect {
	private static final String HOSTNAME = "localhost";
	private static final String PORT = "3306";
	private static final String DBNAME = "Document_DOC";
	private static final String USER = "root";
	private static final String PASSWORD = "12345678";
	private static final String URL = "jdbc:mysql://" + HOSTNAME + ":" + PORT + "/" + DBNAME;

	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			logConnectionFailure(e);
			return null;
		} catch (Exception e) {
			ExceptionLogging.logUnException("DBConnect.getConnection", e);
			return null;
		}
	}

	private static void logConnectionFailure(SQLException e) {
		int code = e.getErrorCode();
		String msg = e.getMessage() != null ? e.getMessage() : "";
		if (code == 1045) {
			System.err.println("MySQL từ chối đăng nhập (1045): sai USER/PASSWORD hoặc user không có quyền.");
		} else if (code == 1049) {
			System.err.println("Không có database tên trong URL (1049): kiểm tra tên schema (ví dụ lab06_jdbc).");
		} else if (code == 0 && msg.contains("Communications link failure")) {
			System.err.println("Không kết nối được tới MySQL: server chưa chạy, sai host/port, hoặc firewall chặn.");
		} else if (msg.contains("Unknown database")) {
			System.err.println("Database chưa tạo hoặc tên sai trong chuỗi JDBC URL.");
		} else {
			System.err.println("Lỗi kết nối JDBC [SQLState=" + e.getSQLState() + ", errorCode=" + code + "]: " + msg);
		}
	}

	public static void logDataAccessException(SQLException e) {
		if (e instanceof SQLIntegrityConstraintViolationException) {
			System.err.println("Vi phạm ràng buộc: trùng khóa chính (INSERT), khóa ngoại, UNIQUE hoặc NOT NULL.");
		} else if (e instanceof SQLSyntaxErrorException) {
			System.err.println("Lỗi cú pháp SQL hoặc đối tượng CSDL không tồn tại (bảng/cột/thủ tục sai tên).");
		} else if (e instanceof SQLTimeoutException) {
			System.err.println("Hết thời gian chờ truy vấn (statement/query timeout).");
		} else {
			String state = e.getSQLState();
			int code = e.getErrorCode();
			if ("42S02".equals(state)) {
				System.err.println("Bảng hoặc view không tồn tại (SQLState 42S02). Kiểm tra tên bảng document.");
			} else if ("42000".equals(state) || code == 1305) {
				System.err.println("Thủ tục lưu trữ không tồn tại hoặc sai tham số (ví dụ sp_getDocumentByID).");
			} else if ("08S01".equals(state) || "08000".equals(state)) {
				System.err.println("Mất kết nối tới MySQL trong lúc thực thi (connection reset / network).");
			} else {
				System.err.println("Lỗi JDBC [SQLState=" + state + ", errorCode=" + code + "]: " + e.getMessage());
			}
		}
		SQLException next = e.getNextException();
		if (next != null) {
			System.err.println("   (nguyên nhân kèm theo) " + next.getMessage());
		}
	}
}