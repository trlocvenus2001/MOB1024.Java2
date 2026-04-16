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
import model.Document;
import model.Status;
import util.ExceptionLogging;

public class DocumentDAO {
	public boolean add(Document doc) {
		String sql = "INSERT INTO document (id, title, author, category) VALUES (?, ?, ?, ?)";
		try (Connection c = DBConnect.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setString(1, doc.getId());
			ps.setString(2, doc.getTitle());
			ps.setString(3, doc.getAuthor());
			ps.setString(4, doc.getCategory());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			DBConnect.logDataAccessException(e);
			return false;
		} catch (Exception e) {
			ExceptionLogging.logUnException("DocumentDAO.add", e);
			return false;
		}
	}

	public boolean update(Document doc) {
		String sql = "UPDATE document SET " + "old_title = title, old_author = author, old_category = category, "
				+ "title = ?, author = ?, category = ? WHERE id = ?";
		try (Connection c = DBConnect.getConnection()) {
			c.setAutoCommit(false);
			try (PreparedStatement ps = c.prepareStatement(sql)) {
				ps.setString(1, doc.getTitle());
				ps.setString(2, doc.getAuthor());
				ps.setString(3, doc.getCategory());
				ps.setString(4, doc.getId());
				int rowsAffected = ps.executeUpdate();
				if (rowsAffected > 0) {
					c.commit();
					return true;
				} else {
					c.rollback();
					return false;
				}
			} catch (SQLException e) {
				c.rollback();
				DBConnect.logDataAccessException(e);
				return false;
			}
		} catch (Exception e) {
			ExceptionLogging.logUnException("DocumentDAO.update", e);
			return false;
		}
	}

	public boolean delete(String id) {
		String sql = "DELETE FROM document WHERE id=?";
		try (Connection c = DBConnect.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setString(1, id);
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			DBConnect.logDataAccessException(e);
			return false;
		} catch (Exception e) {
			ExceptionLogging.logUnException("DocumentDAO.delete", e);
			return false;
		}
	}

	public Document getById(String id) {
		Connection c = DBConnect.getConnection();
		if (c == null)
			return null;
		String sql = "SELECT * FROM document WHERE id = ?";
		try (c; PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setString(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next())
					return new Document(rs.getString("id"), rs.getString("title"), rs.getString("author"),
							rs.getString("category"));
			}
			return null;
		} catch (SQLException e) {
			DBConnect.logDataAccessException(e);
			return null;
		} catch (Exception e) {
			ExceptionLogging.logUnException("DocumentDAO.getById", e);
			return null;
		}
	}

	public List<Document> getAll() {
		List<Document> list = new ArrayList<>();
		Connection c = DBConnect.getConnection();
		if (c == null)
			return Collections.emptyList();
		try (c; PreparedStatement ps = c.prepareStatement("SELECT * FROM document"); ResultSet rs = ps.executeQuery()) {
			while (rs.next())
				list.add(new Document(rs.getString("id"), rs.getString("title"), rs.getString("author"),
						rs.getString("category")));
			return list;
		} catch (SQLException e) {
			DBConnect.logDataAccessException(e);
			return Collections.emptyList();
		} catch (Exception e) {
			ExceptionLogging.logUnException("DocumentDAO.getAll", e);
			return Collections.emptyList();
		}
	}

	public List<Document> search(String keyword) {
		List<Document> list = new ArrayList<>();
		Connection c = DBConnect.getConnection();
		if (c == null)
			return Collections.emptyList();
		String sql = "SELECT * FROM document WHERE title LIKE ? OR author LIKE ? OR category LIKE ?";
		try (c; PreparedStatement ps = c.prepareStatement(sql)) {
			String kw = "%" + keyword + "%";
			ps.setString(1, kw);
			ps.setString(2, kw);
			ps.setString(3, kw);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next())
					list.add(new Document(rs.getString("id"), rs.getString("title"), rs.getString("author"),
							rs.getString("category")));
			}
			return list;
		} catch (SQLException e) {
			DBConnect.logDataAccessException(e);
			return Collections.emptyList();
		} catch (Exception e) {
			ExceptionLogging.logUnException("DocumentDAO.search", e);
			return Collections.emptyList();
		}
	}

	public Document getWithCopies(String docId) {
		Connection c = DBConnect.getConnection();
		if (c == null)
			return null;
		String sql = "SELECT d.*, c.id AS copy_id, c.status, c.old_status FROM document d LEFT JOIN document_copy c ON d.id = c.document_id WHERE d.id = ?";
		Document doc = null;
		try (c; PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setString(1, docId);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					if (doc == null)
						doc = new Document(rs.getString("id"), rs.getString("title"), rs.getString("author"),
								rs.getString("category"));
					if (rs.getString("copy_id") != null)
						doc.getCopies().add(new Copy(rs.getString("copy_id"), docId,
								Status.valueOf(rs.getString("status").toUpperCase())));
				}
			}
			return doc;
		} catch (SQLException e) {
			DBConnect.logDataAccessException(e);
			return null;
		} catch (Exception e) {
			ExceptionLogging.logUnException("DocumentDAO.getWithCopies", e);
			return null;
		}
	}

	public List<Document> getAllWithCopies() {
		List<Document> list = new ArrayList<>();
		Connection c = DBConnect.getConnection();
		if (c == null)
			return Collections.emptyList();
		String sql = "SELECT d.id AS d_id, d.title, d.author, d.category, "
				+ "d.old_title, d.old_author, d.old_category, " + "c.id AS c_id, c.status, c.old_status "
				+ "FROM document d LEFT JOIN document_copy c ON d.id = c.document_id " + "ORDER BY d.id";
		try (c; PreparedStatement ps = c.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
			Document currentDoc = null;

			while (rs.next()) {
				String currentRowDocId = rs.getString("d_id");
				if (currentDoc == null || !currentDoc.getId().equals(currentRowDocId)) {
					currentDoc = new Document(currentRowDocId, rs.getString("title"), rs.getString("author"),
							rs.getString("category"), rs.getString("old_title"), rs.getString("old_author"),
							rs.getString("old_category"));
					list.add(currentDoc);
				}
				String copyId = rs.getString("c_id");
				if (copyId != null) {
					currentDoc.getCopies().add(new Copy(copyId, currentRowDocId,
							Status.valueOf(rs.getString("status").toUpperCase()), rs.getString("old_status")));
				}
			}
			return list;
		} catch (SQLException e) {
			DBConnect.logDataAccessException(e);
			return Collections.emptyList();
		} catch (Exception e) {
			ExceptionLogging.logUnException("DocumentDAO.getAllWithCopiesNoMap", e);
			return Collections.emptyList();
		}
	}
}
