package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.Information;
import util.DBHelper;
import util.PageBean;

//公司资讯
public class InformationDAO {
	// 获取所有公司资讯类的信息
	public Map<String, Object> getAll(PageBean pb) throws Exception {
		PreparedStatement stmt = null; // 语句对象
		ResultSet rs = null; // 数据集
		List<Information> list = new ArrayList<Information>(); // 公司资讯集合
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
			String sql = "select * from information limit ?,?;"; // SQL语句
			stmt = DBHelper.getConnection().prepareStatement(sql); // 创建链接对象
			stmt.setInt(1, index);  //设置SQL语句第一个参数初始偏移量
			stmt.setInt(2, count);  //设置SQL语句第二个参数返回记录行最大数目
			rs = stmt.executeQuery(); //获得公司资讯数据集
			while (rs.next()) { //循环公司资讯数据集
				Information lists = new Information(); //创建一个Information对象
				lists.setId(rs.getInt("id")); //将公司资讯ID的值加入lists
				lists.setTitle(rs.getString("title")); //将公司资讯标题的值加入lists
				lists.setAuthor(rs.getString("author")); //将公司资讯作者的值加入lists
				lists.setContent(rs.getString("content")); //将公司资讯内容的值加入lists
				lists.setDate(rs.getString("date")); //将公司资讯发布时间的值加入lists
				list.add(lists); // 把一个公司资讯加入集合
			}
			//创建一个hashMap，key是String类型，value是Object类型（任意类型）
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("PageDate", pb); //将分页对象pb添加到map集合中
			map.put("list", list); //将公司资讯集合对象list添加到map集合中
			return map;	//返回map集合
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
		String sql = "select count(*) from information"; // SQL语句
		int count = 0;  //总数
		try {
			pStatement = DBHelper.getConnection().prepareStatement(sql); //创建连接对象
			rSet = pStatement.executeQuery(); //获得公司资讯数据集
			rSet.next();
			count = rSet.getInt(1);  //将总数的值赋给变量count
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
		return count;  //返回总数
	}

	// 添加
	public void add(Information information) {
		String sql = "insert into information(title,author,content,date) values(?,?,?,?);"; // 定义SQL语句
		PreparedStatement pstmt = null;  // 语句对象
		try {
			pstmt = DBHelper.getConnection().prepareStatement(sql); // 创建链接对象
			pstmt.setString(1, information.getTitle());	//设置SQL语句的第一个参数标题的值
			pstmt.setString(2, information.getAuthor()); //设置SQL语句的第二个参数作者的值
			pstmt.setString(3, information.getContent()); //设置SQL语句的第三个参数内容的值
			pstmt.setString(4, information.getDate()); //设置SQL语句的第四个参数发布时间的值
			pstmt.execute(); //执行SQL语句
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				pstmt.close(); //释放语句对象
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 删除
	public void delete(Information information) {
		String sql = "delete from information where id=?;"; // 定义SQL语句
		PreparedStatement pstmt = null;	//语句对象
		try {
			pstmt = DBHelper.getConnection().prepareStatement(sql); // 创建链接对象
			pstmt.setInt(1, information.getId()); //设置SQL语句的参数ID的值
			pstmt.execute();	//执行SQL语句
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				pstmt.close();  //释放语句对象
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 获取单个公司资讯的信息
	public Information getone(Information Information) {
		PreparedStatement stmt = null; // 语句对象
		ResultSet rs = null; // 数据集
		try {
			String sql = "select * from Information where id=? ;"; // SQL语句
			stmt = DBHelper.getConnection().prepareStatement(sql); // 创建链接对象
			stmt.setInt(1, Information.getId()); //设置SQL语句的参数ID的值
			rs = stmt.executeQuery(); //获得数据集
			if (rs.next()) {
				Information lists = new Information(); //创建一个Information对象
				lists.setId(rs.getInt("id")); //将公司资讯ID的值加入lists
				lists.setTitle(rs.getString("title")); //将公司资讯标题的值加入lists
				lists.setAuthor(rs.getString("author")); //将公司资讯作者的值加入lists
				lists.setContent(rs.getString("content")); //将公司资讯内容的值加入lists
				lists.setDate(rs.getString("date")); //将公司资讯发布时间的值加入lists
				return lists;  //返回lists对象
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

	// 查询公司资讯的信息
	public Map<String, Object> search(PageBean pb, Information Information) throws Exception {
		PreparedStatement stmt = null; // 语句对象
		ResultSet rs = null; // 数据集
		ArrayList<Information> list = new ArrayList<Information>(); // 公司资讯集合

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
			String sql = "select * from Information where title like concat('%',?,'%') limit ?,?;"; // SQL语句
			stmt = DBHelper.getConnection().prepareStatement(sql); // 创建链接对象
			stmt.setString(1, Information.getTitle()); //设置SQL语句第一个参数标题的值
			stmt.setInt(1, index);  //设置SQL语句第一个参数初始偏移量
			stmt.setInt(2, count);  //设置SQL语句第二个参数返回记录行最大数目
			rs = stmt.executeQuery(); // 获得数据集
			while (rs.next()) {
				Information lists = new Information();  //创建一个Information对象
				lists.setId(rs.getInt("id"));		//将公司资讯ID的值加入lists
				lists.setTitle(rs.getString("title"));	//将公司资讯标题的值加入lists
				lists.setAuthor(rs.getString("author"));	//将公司资讯作者的值加入lists
				lists.setContent(rs.getString("content"));	//将公司资讯内容的值加入lists
				lists.setDate(rs.getString("date"));	//将公司资讯发布时间的值加入lists
				list.add(lists); // 把一个公司资讯加入集合
			}
			//创建一个hashMap，key是String类型，value是Object类型（任意类型）
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("PageDate", pb); //将分页对象pb添加到map集合中
			map.put("list", list); //将公司资讯集合对象list添加到map集合中
			return map;	//返回map集合
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
	public void update(Information Information) {
		String sql = "update Information set title=?,author=?,content=?,date=? where id=? ;"; // 定义SQL语句
		PreparedStatement pstmt = null; // 语句对象
		try {
			pstmt = DBHelper.getConnection().prepareStatement(sql); //创建链接对象
			pstmt.setString(1, Information.getTitle()); //设置SQL语句的第一个参数标题的值
			pstmt.setString(2, Information.getAuthor()); //设置SQL语句的第二个参数作者的值
			pstmt.setString(3, Information.getContent()); //设置SQL语句的第三个参数内容的值
			pstmt.setString(4, Information.getDate()); //设置SQL语句的第四个参数发布时间的值
			pstmt.setInt(5, Information.getId()); //设置SQL语句的第五个参数ID的值
			pstmt.execute(); //执行SQL语句
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				pstmt.close();  //释放语句对象
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}