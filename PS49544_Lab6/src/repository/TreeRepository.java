package repository; //root node

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connect.DBConnect;
import model.Tree;

public class TreeRepository {
	public List<Tree> findAll() {
		List<Tree> trees = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBConnect.getConnection();
			String sql = "SELECT * FROM tree";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Integer parentId = (Integer) rs.getObject("parent_id");
				Tree tree = new Tree(rs.getInt("node_id"), rs.getString("node_name"), parentId, rs.getInt("level"));
				trees.add(tree);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return trees;
	}

	public Tree findbyId(int id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Tree tr = null;
		try {
			conn = DBConnect.getConnection();
			String sql = "SELECT * FROM tree WHERE node_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				Integer parentId = (Integer) rs.getObject("parent_id");
				tr = new Tree(rs.getInt("node_id"), rs.getString("node_name"), parentId, rs.getInt("level"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return tr;
	}

	public boolean add(Tree tree) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBConnect.getConnection();
			String sql = "INSERT INTO tree (node_name, parent_id, level) VALUES (?, ?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, tree.getNode_name());
			if (tree.getParent_id() == null) {
				ps.setNull(2, java.sql.Types.INTEGER);
			} else {
				ps.setInt(2, tree.getParent_id());
			}
			ps.setInt(3, tree.getLevel());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

	public boolean update(Tree tree) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBConnect.getConnection();
			String sql = "UPDATE tree SET node_name=?, parent_id=?, level=? WHERE node_id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, tree.getNode_name());
			if (tree.getParent_id() == null) {
				ps.setNull(2, java.sql.Types.INTEGER);
			} else {
				ps.setInt(2, tree.getParent_id());
			}
			ps.setInt(3, tree.getLevel());
			ps.setInt(4, tree.getNode_id());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

	public boolean delete(int id) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBConnect.getConnection();
			String sql = "delete from tree where node_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
}