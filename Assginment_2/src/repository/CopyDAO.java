package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import connect.DBConnect;
import model.Copy;
import model.Status;
import util.ExceptionLogging;

public class CopyDAO {
	public boolean add(Copy copy) {
		String sql = "INSERT INTO document_copy (id, document_id, status) VALUES (?, ?, ?)";
		try (Connection c = DBConnect.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setString(1, copy.getId());
			ps.setString(2, copy.getDocumentId());
			ps.setString(3, copy.getStatus().name());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			DBConnect.logDataAccessException(e);
			return false;
		} catch (Exception e) {
			ExceptionLogging.logUnException("CopyDAO.add", e);
			return false;
		}
	}

	public List<Copy> getAll() {
		List<Copy> list = new ArrayList<>();
		Connection c = DBConnect.getConnection();
		if (c == null)
			return Collections.emptyList();
		try (c;
				PreparedStatement ps = c.prepareStatement("SELECT * FROM document_copy");
				ResultSet rs = ps.executeQuery()) {
			while (rs.next())
				list.add(new Copy(rs.getString("id"), rs.getString("document_id"),
						Status.valueOf(rs.getString("status").toUpperCase()), rs.getString("old_status")));
			return list;
		} catch (SQLException e) {
			DBConnect.logDataAccessException(e);
			return Collections.emptyList();
		} catch (Exception e) {
			ExceptionLogging.logUnException("CopyDAO.getAll", e);
			return Collections.emptyList();
		}
	}

	public Copy getById(String id) {
		Connection c = DBConnect.getConnection();
		if (c == null)
			return null;
		String sql = "SELECT * FROM document_copy WHERE id = ?";
		try (c; PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setString(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next())
					return new Copy(rs.getString("id"), rs.getString("document_id"),
							Status.valueOf(rs.getString("status").toUpperCase()), rs.getString("old_status"));
			}
			return null;
		} catch (SQLException e) {
			DBConnect.logDataAccessException(e);
			return null;
		} catch (Exception e) {
			ExceptionLogging.logUnException("CopyDAO.getById", e);
			return null;
		}
	}

	public boolean update(Copy copy) {
		String sql = "UPDATE document_copy SET document_id=?, old_status = status, status=? WHERE id=?";
		try (Connection c = DBConnect.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setString(1, copy.getDocumentId());
			ps.setString(2, copy.getStatus().name());
			ps.setString(3, copy.getId());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			DBConnect.logDataAccessException(e);
			return false;
		} catch (Exception e) {
			ExceptionLogging.logUnException("CopyDAO.update", e);
			return false;
		}
	}

	public boolean delete(String copyId) {
		String sql = "DELETE FROM document_copy WHERE id=?";
		try (Connection c = DBConnect.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setString(1, copyId);
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			DBConnect.logDataAccessException(e);
			return false;
		} catch (Exception e) {
			ExceptionLogging.logUnException("CopyDAO.delete", e);
			return false;
		}
	}

	public void addBatch(String docId, int quantity) {
		Connection c = DBConnect.getConnection();
		if (c == null)
			return;
		String sql = "INSERT INTO document_copy (id, document_id, status) VALUES (?, ?, 'GOOD')";
		try (c) {
			c.setAutoCommit(false);
			try (PreparedStatement ps = c.prepareStatement(sql)) {
				for (int i = 1; i <= quantity; i++) {
					ps.setString(1, docId + "_C" + System.currentTimeMillis() + "_" + i);
					ps.setString(2, docId);
					ps.addBatch();
				}
				ps.executeBatch();
				c.commit();
				System.out.println("✅ Đã thêm " + quantity + " bản sao.");
			} catch (SQLException e) {
				c.rollback();
				DBConnect.logDataAccessException(e);
			}
		} catch (SQLException e) {
			DBConnect.logDataAccessException(e);
		} catch (Exception e) {
			ExceptionLogging.logUnException("CopyDAO.addBatch", e);
		}
	}

	public boolean updateStatus(String copyId, Status newStatus) {
		String sql = "UPDATE document_copy SET old_status = status, status=? WHERE id=?";
		try (Connection c = DBConnect.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setString(1, newStatus.name());
			ps.setString(2, copyId);
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			DBConnect.logDataAccessException(e);
			return false;
		} catch (Exception e) {
			ExceptionLogging.logUnException("CopyDAO.updateStatus", e);
			return false;
		}
	}

	public boolean updateStatusByDoc(String docId, Status newStatus) {
		String sql = "UPDATE document_copy SET old_status = status, status=? WHERE document_id=?";
		try (Connection c = DBConnect.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setString(1, newStatus.name());
			ps.setString(2, docId);
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			DBConnect.logDataAccessException(e);
			return false;
		} catch (Exception e) {
			ExceptionLogging.logUnException("CopyDAO.updateStatusByDoc", e);
			return false;
		}
	}
}
