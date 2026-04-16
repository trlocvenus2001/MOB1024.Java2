package util;

public class ExceptionLogging {
	public static void logUnException(String context, Exception e) {
		System.err.println("[CRITICAL BUG] Lỗi runtime / không mong đợi tại " + context + ": " + e.getMessage());
		e.printStackTrace();
	}
}
