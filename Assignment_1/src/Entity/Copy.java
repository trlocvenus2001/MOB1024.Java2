package Entity;

import java.io.Serializable;

import Enum.Status;

public class Copy implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private Status stt;

	public Copy() {
	}

	public Copy(String id, Status stt) {
		this.id = id;
		this.stt = stt;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Status getStt() {
		return stt;
	}

	public void setStt(Status stt) {
		this.stt = stt;
	}

	@Override
	public String toString() {
		return "Copy [id=" + id + "]";
	}

}
