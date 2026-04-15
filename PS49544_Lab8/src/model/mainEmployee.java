package model;

public class mainEmployee { // Reflection
	public static void main(String[] args) {
		Class<?> clazz = Manager.class;

		if (clazz.isAnnotationPresent(Developer.class)) {
			Developer dev = clazz.getAnnotation(Developer.class);
			System.out.println("Coder: " + dev.name());
			System.out.println("Version: " + dev.version());
		} else {
			System.out.println("Không tìm thấy thông tin Developer!");
		}
		Manager mgr = new Manager();
		if (mgr.setBaseSalary(10000)) {
			System.out.println("Gán lương thành công! Lương Manager: " + mgr.calculateSalary());
		} else {
			System.out.println("Gán lương thất bại do dữ liệu không hợp lệ!");
		}
	}
}
