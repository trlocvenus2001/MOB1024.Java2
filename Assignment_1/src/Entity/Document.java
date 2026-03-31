package Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Document implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String title;
	private String author;
	private String category;
	private List<Copy> listCopy = new ArrayList<Copy>();

	public Document() {
	}

	public Document(String id, String title, String author, String category) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.category = category;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<Copy> getListCopy() {
		return listCopy;
	}

	public void setListCopy(List<Copy> listCopy) {
		this.listCopy = listCopy;
	}

	public void addCopy(Copy copy) {
		this.listCopy.add(copy);
	}

	@Override
	public String toString() {
		return "Document [id=" + id + ", title=" + title + ", author=" + author + ", category=" + category
				+ ", listCopy=" + listCopy + "]";
	}

}
