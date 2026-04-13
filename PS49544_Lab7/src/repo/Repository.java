package repo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import config.DBConnect;
import model.Employee;

public class Repository {

	public List<Employee> getAllPrepared() {
		List<Employee> employees = new ArrayList<>();
		String sql = "SELECT * FROM Employee";
		try (Connection cn = DBConnect.getConnection();
				PreparedStatement ps = cn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				employees.add(new Employee(rs.getInt("empId"), rs.getString("empName"), rs.getDouble("empSalary")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employees;
	}

	public Employee findByIdPrepared(int empId) {
		String sql = "SELECT * FROM Employee WHERE empId = ?";
		try (Connection cn = DBConnect.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
			ps.setInt(1, empId);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return new Employee(rs.getInt("empId"), rs.getString("empName"), rs.getDouble("empSalary"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean insertPrepared(Employee emp) {
		String sql = "INSERT INTO Employee (empName, empSalary) VALUES (?, ?)";
		try (Connection cn = DBConnect.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
			ps.setString(1, emp.getEmpName());
			ps.setDouble(2, emp.getEmpSalary());
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updatePrepared(Employee emp) {
		String sql = "UPDATE Employee SET empName = ?, empSalary = ? WHERE empId = ?";
		try (Connection cn = DBConnect.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
			ps.setString(1, emp.getEmpName());
			ps.setDouble(2, emp.getEmpSalary());
			ps.setInt(3, emp.getEmpId());
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deletePrepared(int empId) {
		String sql = "DELETE FROM Employee WHERE empId = ?";
		try (Connection cn = DBConnect.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
			ps.setInt(1, empId);
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Employee> getAllProcedure() {
		List<Employee> employees = new ArrayList<>();
		String sql = "{call sp_getAllEmployees()}";
		try (Connection c = DBConnect.getConnection();
				CallableStatement cs = c.prepareCall(sql);
				ResultSet rs = cs.executeQuery()) {
			while (rs.next()) {
				employees.add(new Employee(rs.getInt("empId"), rs.getString("empName"), rs.getDouble("empSalary")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employees;
	}

	public Employee findByIdProcedure(int empId) {
		String sql = "{call sp_getEmployeeById(?)}";
		try (Connection c = DBConnect.getConnection(); CallableStatement cs = c.prepareCall(sql)) {
			cs.setInt(1, empId);
			try (ResultSet rs = cs.executeQuery()) {
				if (rs.next()) {
					return new Employee(rs.getInt("empId"), rs.getString("empName"), rs.getDouble("empSalary"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean insertProcedure(Employee emp) {
		String sql = "{call sp_insertEmployee(?, ?)}";
		try (Connection c = DBConnect.getConnection(); CallableStatement cs = c.prepareCall(sql)) {
			cs.setString(1, emp.getEmpName());
			cs.setDouble(2, emp.getEmpSalary());
			return cs.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateProcedure(Employee emp) {
		String sql = "{call sp_updateEmployee(?, ?, ?)}";
		try (Connection c = DBConnect.getConnection(); CallableStatement cs = c.prepareCall(sql)) {
			cs.setInt(1, emp.getEmpId());
			cs.setString(2, emp.getEmpName());
			cs.setDouble(3, emp.getEmpSalary());
			return cs.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteProcedure(int empId) {
		String sql = "{call sp_deleteEmployee(?)}";
		try (Connection c = DBConnect.getConnection(); CallableStatement cs = c.prepareCall(sql)) {
			cs.setInt(1, empId);
			return cs.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}