package model;

import java.io.Serializable;
import java.util.Scanner;

abstract public class Person implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final Scanner sc = new Scanner(System.in);
	private String id;
	private String name;

	public Person() {
		super();
	}

	public Person(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void inputInfo() {
		while (true) {
			try {
				System.out.print("ID: ");
				String inputId = sc.nextLine();
				if (inputId.isEmpty())
					throw new Exception(">> Lỗi: ID không được trống");
				break;
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			}
		}
		while (true) {
			try {
				System.out.print("Name: ");
				String inputName = sc.nextLine();
				if (inputName.isEmpty())
					throw new Exception(">> Lỗi: Tên không được trống");
				break;
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			}
		}
	}
}
