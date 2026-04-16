package model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Copy {
	private String id;
	private String documentId;
	private Status status;
	private String oldStatus;

	public Copy(String id, String documentId, Status status) {
		this.id = id;
		this.documentId = documentId;
		this.status = status;
	}

	public Copy(String id, String documentId, Status status, String oldStatus) {
		this.id = id;
		this.documentId = documentId;
		this.status = status;
		this.oldStatus = oldStatus;
	}

	@Override
	public String toString() {
		String old = (oldStatus != null && !oldStatus.isEmpty()) ? " (Trạng thái cũ: " + oldStatus + ")" : "";
		return "Mã bản sao: " + id + " | Trạng thái: " + status + old;
	}
}