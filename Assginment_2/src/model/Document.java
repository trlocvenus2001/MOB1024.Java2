package model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Document {
	private String id;
	private String title;
	private String author;
	private String category;
	private String oldTitle;
	private String oldAuthor;
	private String oldCategory;
	private List<Copy> copies = new ArrayList<>();

	public Document(String id, String title, String author, String category) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.category = category;
	}

	public Document(String id, String title, String author, String category, String oldTitle, String oldAuthor,
			String oldCategory) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.category = category;
		this.oldTitle = oldTitle;
		this.oldAuthor = oldAuthor;
		this.oldCategory = oldCategory;
	}

	@Override
	public String toString() {
		String history = "";
		if (oldTitle != null || oldAuthor != null || oldCategory != null) {
			history = String.format("\n   └─ [Lịch sử chỉnh sửa] Tên cũ: %s | Tác giả cũ: %s | Thể loại cũ: %s",
					(oldTitle != null ? oldTitle : "N/A"), (oldAuthor != null ? oldAuthor : "N/A"),
					(oldCategory != null ? oldCategory : "N/A"));
		}

		return String.format("Sách: [%s] %s | TG: %s | Thể loại: %s %s", id, title, author, category, history);
	}
}