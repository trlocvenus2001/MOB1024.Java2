package util;

import java.util.function.Consumer;

public class LambdaUtil {
	public static <T> Consumer<T> wrapWithLogging(ThrowingConsumer<T, DatabaseException> throwingConsumer) {
		return i -> {
			try {
				throwingConsumer.accept(i);
			} catch (DatabaseException ex) {
				System.err.println("LỖI NGHIỆP VỤ TRONG LUỒNG: " + ex.getMessage());
			}
		};
	}
}
