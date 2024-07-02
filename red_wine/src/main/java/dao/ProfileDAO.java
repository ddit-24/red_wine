package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Profile;
import util.DBHelper;

//公司简介
public class ProfileDAO {

	// 获取公司简介信息
	public Profile getAll() {
		Connection conn = null; // 连接对象
		PreparedStatement stmt = null; // 语句对象
		ResultSet rs = null; // 数据集
		try {
			conn = DBHelper.getConnection(); // 获得链接对象
			String sql = "select * from company_profile;"; // SQL语句
			stmt = conn.prepareStatement(sql); // 创建链接对象
			rs = stmt.executeQuery(); // 获得数据集
			if (rs.next()) {
				Profile lists = new Profile();
				lists.setId(rs.getInt("id"));
				lists.setContent(rs.getString("content"));
				return lists;
			} else {
				return null;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			// 释放数据集对象
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			// 释放语句对象
			if (stmt != null) {
				try {
					stmt.close();
					stmt = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	// 添加
	public void add(Profile profile) {
		String sql = "insert into company_profile(content) values(?);"; // 定义sql语句
		PreparedStatement pstmt = null;
		try {
			pstmt = DBHelper.getConnection().prepareStatement(sql); // 创建链接对象
			pstmt.setString(1, profile.getContent());
			pstmt.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 删除
	public void delete(Profile profile) {
		String sql = "delete from company_profile where id=?;"; // 定义sql语句
		PreparedStatement pstmt = null;
		try {
			pstmt = DBHelper.getConnection().prepareStatement(sql); // 创建链接对象
			pstmt.setInt(1, profile.getId());
			pstmt.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	// 获取更新信息
	public Profile getone(Profile profile) {
		PreparedStatement stmt = null; // 语句对象
		ResultSet rs = null; // 数据集
		try {
			String sql = "select * from company_profile where id=? ;"; // SQL语句
			stmt = DBHelper.getConnection().prepareStatement(sql); // 创建链接对象
			stmt.setInt(1, profile.getId());
			rs = stmt.executeQuery(); // 获得数据集
			if (rs.next()) {
				Profile lists = new Profile();
				lists.setId(rs.getInt("id"));
				lists.setContent(rs.getString("content"));
				return lists;
			} else {
				return null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// 释放数据集对象
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			// 释放语句对象
			if (stmt != null) {
				try {
					stmt.close();
					stmt = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		return null;
	}

	// 更新
	public void update(Profile profile) {
		String sql = "update company_profile set content=? where id=? ;";  // 定义sql语句
		PreparedStatement pstmt = null;
		try {
			pstmt = DBHelper.getConnection().prepareStatement(sql); // 创建链接对象
			pstmt.setString(1, profile.getContent());
			pstmt.setInt(2, profile.getId());
			pstmt.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}