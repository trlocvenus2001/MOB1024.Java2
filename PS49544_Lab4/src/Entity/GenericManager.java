package Entity;

import java.util.ArrayList;
import java.util.List;

import Exception.EmptyListException;

public class GenericManager<T> {
	private List<T> list = new ArrayList<>();

	public void add(T item) {
		list.add(item);
	}

	public void display() {
		for (T t : list) {
			System.out.println(t);
		}
	}

	public List<T> getAll() {
		return list;
	}

	public T getFirst() throws EmptyListException {
		if (list.isEmpty()) {
			throw new EmptyListException(">> Lỗi: Danh sách trống!");
		}
		return list.getFirst();
	}

	public T getLast() throws EmptyListException {
		if (list.isEmpty()) {
			throw new EmptyListException(">> Lỗi: Danh sách trống!");
		}
		return list.getLast();
	}
}
