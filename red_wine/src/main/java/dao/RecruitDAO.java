package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.Recruit;
import util.DBHelper;
import util.PageBean;

//招贤纳士
public class RecruitDAO {
	// 获取所有招贤纳士的信息
	public Map<String, Object> getAll(PageBean pb) throws Exception {
		PreparedStatement stmt = null; // 语句对象
		ResultSet rs = null; // 数据集
		List<Recruit> list = new ArrayList<Recruit>(); // 招贤纳士集合

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
			String sql = "select * from recruit limit ?,?;"; // SQL语句
			stmt = DBHelper.getConnection().prepareStatement(sql); // 创建链接对象
			stmt.setInt(1, index);
			stmt.setInt(2, count);
			rs = stmt.executeQuery(); // 获得数据集
			while (rs.next()) {
				Recruit lists = new Recruit();
				lists.setId(rs.getInt("id"));
				lists.setTitle(rs.getString("title"));
				lists.setJob_describe(rs.getString("job_describe"));
				lists.setRequirement(rs.getString("requirement"));
				list.add(lists); // 把一條招贤纳士信息加入集合
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
		String sql = "select count(*) from recruit"; // SQL语句
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
	public void add(Recruit recruit) {
		String sql = "insert into recruit(title, job_describe, requirement) values(?,?,?);"; // 定义sql语句
		PreparedStatement pstmt = null;

		try {
			pstmt = DBHelper.getConnection().prepareStatement(sql); // 创建链接对象
			pstmt.setString(1, recruit.getTitle());
			pstmt.setString(2, recruit.getJob_describe());
			pstmt.setString(3, recruit.getRequirement());
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
	public void delete(Recruit recruit) {
		String sql = "delete from recruit where id=?;"; // 定义sql语句
		PreparedStatement pstmt = null;

		try {
			pstmt = DBHelper.getConnection().prepareStatement(sql); // 创建链接对象
			pstmt.setInt(1, recruit.getId());
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

	// 获取单个招贤纳士信息
	public Recruit getone(Recruit recruit) {

		PreparedStatement stmt = null; // 语句对象
		ResultSet rs = null; // 数据集
		try {
			String sql = "select * from recruit where id=? ;"; // SQL语句
			stmt = DBHelper.getConnection().prepareStatement(sql); // 创建链接对象
			stmt.setInt(1, recruit.getId());
			rs = stmt.executeQuery(); // 获得数据集

			if (rs.next()) {
				Recruit lists = new Recruit();
				lists.setId(rs.getInt("id"));
				lists.setTitle(rs.getString("title"));
				lists.setJob_describe(rs.getString("job_describe"));
				lists.setRequirement(rs.getString("requirement"));
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

	// 查询招贤纳士的信息
	public Map<String, Object> search(PageBean pb, Recruit recruit) throws Exception {

		PreparedStatement stmt = null; // 语句对象
		ResultSet rs = null; // 数据集
		List<Recruit> list = new ArrayList<Recruit>(); // 招贤纳士集合

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
			String sql = "select * from recruit where title like concat('%',?,'%') limit ?,?;"; // SQL语句
			stmt = DBHelper.getConnection().prepareStatement(sql); // 创建链接对象
			stmt.setString(1, recruit.getTitle());
			stmt.setInt(2, index);
			stmt.setInt(3, count);
			rs = stmt.executeQuery(); // 获得数据集

			while (rs.next()) {
				Recruit lists = new Recruit();
				lists.setId(rs.getInt("id"));
				lists.setTitle(rs.getString("title"));
				lists.setJob_describe(rs.getString("job_describe"));
				lists.setRequirement(rs.getString("requirement"));
				list.add(lists); // 把一个招贤纳士信息加入集合
			}
			Map<String,Object> map = new HashMap<String, Object>();
		    map.put("PageDate",pb);
			map.put("list",list);
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
	public void update(Recruit recruit) {
		String sql = "update recruit set title=?,job_describe=?,requirement=? where id=? ;"; // 定义sql语句
		PreparedStatement pstmt = null;

		try {
			pstmt = DBHelper.getConnection().prepareStatement(sql); // 创建链接对象
			pstmt.setString(1, recruit.getTitle());
			pstmt.setString(2, recruit.getJob_describe());
			pstmt.setString(3, recruit.getRequirement());
			pstmt.setInt(4, recruit.getId());
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