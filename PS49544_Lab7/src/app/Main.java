package app;

import java.util.List;
import java.util.Scanner;

import model.Employee;
import repo.Repository;

public class Main {
	private static Scanner sc = new Scanner(System.in);
	private static Repository repo = new Repository();

	public static void main(String[] args) {
		int mainChoice = -1;
		do {
			clearScreen();
			mainMenu();
			mainChoice = getChoice();
			switch (mainChoice) {
			case 1:
				runSubMenu("PREPARED STATEMENT", false);
				break;
			case 2:
				runSubMenu("STORED PROCEDURE", true);
				break;
			case 0:
				System.out.println("Exiting the application. Goodbye!");
				break;
			default:
				System.out.println("=> Invalid option. Please select a number between 0 and 2.");
				pauseScreen();
				break;
			}
		} while (mainChoice != 0);
		sc.close();
	}

	private static void mainMenu() {
		System.out.println("=================================");
		System.out.println("   EMPLOYEE MANAGEMENT SYSTEM    ");
		System.out.println("=================================");
		System.out.println("1. Use PreparedStatement (Basic)");
		System.out.println("2. Use Stored Procedure (Advanced)");
		System.out.println("0. Exit Application");
		System.out.println("=================================");
		System.out.print("Please select an option (0-2): ");
	}

	private static void runSubMenu(String modeName, boolean isProcedure) {
		int subChoice = -1;
		do {
			clearScreen();
			System.out.println("=================================");
			System.out.println("   MODE: " + modeName);
			System.out.println("=================================");
			System.out.println("1. Show all employees");
			System.out.println("2. Add new employee");
			System.out.println("3. Update employee details");
			System.out.println("4. Delete an employee");
			System.out.println("5. Find employee by ID");
			System.out.println("0. Return to Main Menu");
			System.out.println("=================================");
			System.out.print("Please select an option (0-5): ");
			subChoice = getChoice();
			switch (subChoice) {
			case 1:
				showAllEmployees(isProcedure);
				break;
			case 2:
				insertEmployee(isProcedure);
				break;
			case 3:
				updateEmployee(isProcedure);
				break;
			case 4:
				deleteEmployee(isProcedure);
				break;
			case 5:
				findEmployeeById(isProcedure);
				break;
			case 0:
				System.out.println("=> Returning to Main Menu...");
				break;
			default:
				System.out.println("=> Invalid option. Please select a number between 0 and 5.");
				pauseScreen();
				break;
			}
		} while (subChoice != 0);
	}

	private static int getChoice() {
		try {
			return Integer.parseInt(sc.nextLine());
		} catch (Exception e) {
			return -1;
		}
	}

	private static void clearScreen() {
		try {
			if (System.getProperty("os.name").contains("Windows")) {
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			} else {
				System.out.print("\033[H\033[2J");
				System.out.flush();
			}
		} catch (Exception e) {
			for (int i = 0; i < 50; i++)
				System.out.println();
		}
	}

	private static void pauseScreen() {
		System.out.print("\n>>> Press Enter to continue...");
		sc.nextLine();
	}

	private static void showAllEmployees(boolean isProcedure) {
		clearScreen();
		System.out.println("--- EMPLOYEE LIST ---");
		List<Employee> list = isProcedure ? repo.getAllProcedure() : repo.getAllPrepared();
		if (list.isEmpty()) {
			System.out.println("The list is currently empty.");
		} else {
			for (Employee e : list) {
				System.out.printf("ID: %d | Name: %s | Salary: %.2f\n", e.getEmpId(), e.getEmpName(), e.getEmpSalary());
			}
		}
		pauseScreen();
	}

	private static void insertEmployee(boolean isProcedure) {
		clearScreen();
		System.out.println("--- ADD NEW EMPLOYEE ---");
		try {
			System.out.print("Enter employee name: ");
			String name = sc.nextLine();
			System.out.print("Enter employee salary: ");
			double salary = Double.parseDouble(sc.nextLine());
			Employee emp = new Employee(0, name, salary);
			boolean result = isProcedure ? repo.insertProcedure(emp) : repo.insertPrepared(emp);
			System.out.println(result ? "=> Added successfully!" : "=> Failed to add employee!");
		} catch (Exception e) {
			System.out.println("=> Invalid input! Salary must be a number.");
		}
		pauseScreen();
	}

	private static void updateEmployee(boolean isProcedure) {
		clearScreen();
		System.out.println("--- UPDATE EMPLOYEE ---");
		try {
			System.out.print("Enter employee ID to update: ");
			int id = Integer.parseInt(sc.nextLine());
			Employee currentEmp = isProcedure ? repo.findByIdProcedure(id) : repo.findByIdPrepared(id);
			if (currentEmp == null) {
				System.out.println("=> No employee found with ID = " + id);
			} else {
				System.out.print("Enter new name: ");
				String name = sc.nextLine();
				System.out.print("Enter new salary: ");
				double salary = Double.parseDouble(sc.nextLine());
				Employee emp = new Employee(id, name, salary);
				boolean result = isProcedure ? repo.updateProcedure(emp) : repo.updatePrepared(emp);
				System.out.println(result ? "=> Updated successfully!" : "=> Failed to update employee!");
			}
		} catch (Exception e) {
			System.out.println("=> Invalid input! Please check your data format.");
		}
		pauseScreen();
	}

	private static void deleteEmployee(boolean isProcedure) {
		clearScreen();
		System.out.println("--- DELETE EMPLOYEE ---");
		try {
			System.out.print("Enter employee ID to delete: ");
			int id = Integer.parseInt(sc.nextLine());
			boolean result = isProcedure ? repo.deleteProcedure(id) : repo.deletePrepared(id);
			System.out.println(result ? "=> Deleted successfully!" : "=> Failed to delete (ID might not exist)!");
		} catch (Exception e) {
			System.out.println("=> Error: ID must be an integer.");
		}
		pauseScreen();
	}

	private static void findEmployeeById(boolean isProcedure) {
		clearScreen();
		System.out.println("--- FIND EMPLOYEE ---");
		try {
			System.out.print("Enter employee ID to find: ");
			int id = Integer.parseInt(sc.nextLine());
			Employee emp = isProcedure ? repo.findByIdProcedure(id) : repo.findByIdPrepared(id);
			if (emp != null) {
				System.out.println("\n=> RECORD FOUND:");
				System.out.printf("ID: %d | Name: %s | Salary: %.2f\n", emp.getEmpId(), emp.getEmpName(),
						emp.getEmpSalary());
			} else {
				System.out.println("=> No employee found with ID = " + id);
			}
		} catch (Exception e) {
			System.out.println("=> Error: ID must be an integer.");
		}
		pauseScreen();
	}
}