package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.Knowledge;
import util.DBHelper;
import util.PageBean;

//红酒知识
public class KnowledgeDAO {
	// 获取所有红酒知识的信息
	public Map<String, Object> getAll(PageBean pb) throws Exception {
		PreparedStatement stmt = null; // 语句对象
		ResultSet rs = null; // 数据集
		List<Knowledge> list = new ArrayList<Knowledge>(); // 红酒知识集合

		// 查询总记录数；设置到pb对象中
		int totalCount = this.getTotalCount();
		pb.setTotalCount(totalCount);
		// 判断（预防当前页为首页或末页时，点击上一页或下一页出错）
		if (pb.getCurrentPage() <= 0) {
			pb.setCurrentPage(1); // 把当前页设置为1
		} else if (pb.getCurrentPage() > pb.getTotalPage()) {
			pb.setCurrentPage(pb.getTotalPage()); // 把当前页设置为最大页数
		}

		// 获取当前页：计算查询的起始行、返回的行数
		int currentPage = pb.getCurrentPage(); // 当前页
		int index = (currentPage - 1) * pb.getPageCount(); // 查询的起始行
		int count = pb.getPageCount(); // 查询返回的行数
		try {
			String sql = "select * from knowledge limit ?,?;"; // SQL语句
			stmt = DBHelper.getConnection().prepareStatement(sql); // 创建链接对象
			stmt.setInt(1, index);
			stmt.setInt(2, count);
			rs = stmt.executeQuery(); // 获得数据集
			while (rs.next()) {
				Knowledge lists = new Knowledge();
				lists.setId(rs.getInt("id"));
				lists.setTitle(rs.getString("title"));
				lists.setAuthor(rs.getString("author"));
				lists.setContent(rs.getString("content"));
				lists.setDate(rs.getString("date"));
				list.add(lists); // 把一个红酒知识加入集合
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("PageDate", pb);
			map.put("list", list);
			return map;
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

	// 获取总数
	public int getTotalCount() throws Exception {
		PreparedStatement pStatement = null; // 语句对象
		ResultSet rSet = null; // 数据集
		String sql = "select count(*) from knowledge"; // SQL语句
		int count = 0;
		try {
			pStatement = DBHelper.getConnection().prepareStatement(sql);
			rSet = pStatement.executeQuery();
			rSet.next();
			count = rSet.getInt(1); 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 释放数据集对象
			if (rSet != null) {
				try {
					rSet.close();
					rSet = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			// 释放语句对象
			if (pStatement != null) {
				try {
					pStatement.close();
					pStatement = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		return count;
	}

	// 添加
	public void add(Knowledge knowledge) {
		String sql = "insert into knowledge(title,author,content,date) values(?,?,?,?);"; // 定义sql语句
		PreparedStatement pstmt = null;

		try {
			pstmt = DBHelper.getConnection().prepareStatement(sql); // 创建链接对象
			pstmt.setString(1, knowledge.getTitle());
			pstmt.setString(2, knowledge.getAuthor());
			pstmt.setString(3, knowledge.getContent());
			pstmt.setString(4, knowledge.getDate());
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
	public void delete(Knowledge knowledge) {
		String sql = "delete from knowledge where id=?;"; // 定义sql语句
		PreparedStatement pstmt = null;

		try {
			pstmt = DBHelper.getConnection().prepareStatement(sql); // 创建链接对象
			pstmt.setInt(1, knowledge.getId());
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

	// 获取单个红酒知识的信息
	public Knowledge getone(Knowledge knowledge) {

		PreparedStatement stmt = null; // 语句对象
		ResultSet rs = null; // 数据集
		try {
			String sql = "select * from knowledge where id=? ;"; // SQL语句
			stmt = DBHelper.getConnection().prepareStatement(sql); // 创建链接对象
			stmt.setInt(1, knowledge.getId());
			rs = stmt.executeQuery(); // 获得数据集

			if (rs.next()) {
				Knowledge lists = new Knowledge();
				lists.setId(rs.getInt("id"));
				lists.setTitle(rs.getString("title"));
				lists.setAuthor(rs.getString("author"));
				lists.setContent(rs.getString("content"));
				lists.setDate(rs.getString("date"));
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

	// 查询红酒知识的信息
	public Map<String, Object> search(PageBean pb, Knowledge knowledge) throws Exception {

		PreparedStatement stmt = null; // 语句对象
		ResultSet rs = null; // 数据集
		List<Knowledge> list = new ArrayList<Knowledge>(); // 红酒知识集合

		// 查询总记录数；设置到pb对象中
		int totalCount = this.getTotalCount();
		pb.setTotalCount(totalCount);

		// 判断（预防当前页为首页或末页时，点击上一页或下一页出错）
		if (pb.getCurrentPage() <= 0) {
			pb.setCurrentPage(1); // 把当前页设置为1
		} else if (pb.getCurrentPage() > pb.getTotalPage()) {
			pb.setCurrentPage(pb.getTotalPage()); // 把当前页设置为最大页数
		}
		// 获取当前页：计算查询的起始行、返回的行数
		int currentPage = pb.getCurrentPage(); // 当前页
		int index = (currentPage - 1) * pb.getPageCount(); // 查询的起始行
		int count = pb.getPageCount(); // 查询返回的行数

		try {
			String sql = "select * from knowledge where title like concat('%',?,'%') limit ?,?;"; // SQL语句
			stmt = DBHelper.getConnection().prepareStatement(sql); // 创建链接对象
			stmt.setString(1, knowledge.getTitle());
			stmt.setInt(2, index);
			stmt.setInt(3, count);
			rs = stmt.executeQuery(); // 获得数据集

			while (rs.next()) {
				Knowledge lists = new Knowledge();
				lists.setId(rs.getInt("id"));
				lists.setTitle(rs.getString("title"));
				lists.setAuthor(rs.getString("author"));
				lists.setContent(rs.getString("content"));
				lists.setDate(rs.getString("date"));
				list.add(lists); // 把一个红酒知识加入集合
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("PageDate", pb);
			map.put("list", list);
			return map;
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
	public void update(Knowledge knowledge) {
		String sql = "update knowledge set title=?,author=?,content=?,date=? where id=? ;"; // 定义sql语句
		PreparedStatement pstmt = null;

		try {
			pstmt = DBHelper.getConnection().prepareStatement(sql); // 创建链接对象
			pstmt.setString(1, knowledge.getTitle());
			pstmt.setString(2, knowledge.getAuthor());
			pstmt.setString(3, knowledge.getContent());
			pstmt.setString(4, knowledge.getDate());
			pstmt.setInt(5, knowledge.getId());
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