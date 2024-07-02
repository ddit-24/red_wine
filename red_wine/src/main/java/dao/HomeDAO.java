package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.Classification;
import entity.Commodity;
import entity.Information;
import entity.Knowledge;
import entity.Recruit;
import util.DBHelper;
import util.PageBean;

/**
 * 前端集合
 */
public class HomeDAO {

	// 产品展示
	public Map<String, Object> getCommodity(PageBean pb) throws Exception {
		PreparedStatement stmt = null; // 产品语句对象
		ResultSet rs = null; // 产品数据集
		List<Commodity> list = new ArrayList<Commodity>(); // 产品集合

		PreparedStatement cstmt = null; // 分类语句对象
		ResultSet crs = null; // 分类数据集
		List<Classification> clist = new ArrayList<Classification>(); // 分类集合

		// 查询总记录数；设置到pb对象中
		int totalCount = this.getTotalCount("commodity");
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
			// 商品
			String sql = "select * from commodity limit ?,?;"; // SQL语句
			stmt = DBHelper.getConnection().prepareStatement(sql); // 创建链接对象
			stmt.setInt(1, index);
			stmt.setInt(2, count);
			rs = stmt.executeQuery(); // 获得数据集
			while (rs.next()) {
				Commodity lists = new Commodity();
				lists.setId(rs.getInt("id"));
				lists.setTitle(rs.getString("title"));
				lists.setPrice(rs.getFloat("price"));
				lists.setPhoto(rs.getString("photo"));
				list.add(lists); // 把一个商品信息加入集合
			}

			// 分类
			String sqls = "select * from commodity_class"; // SQL语句
			cstmt = DBHelper.getConnection().prepareStatement(sqls); // 创建链接对象
			crs = cstmt.executeQuery(); // 获得数据集
			while (crs.next()) {
				Classification lists = new Classification();
				lists.setId(crs.getInt("id"));
				lists.setTitle(crs.getString("title"));
				clist.add(lists); // 把一个分类信息加入集合
			}

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("PageDate", pb);
			map.put("list", list);
			map.put("clist", clist);
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
			if (crs != null) {
				try {
					crs.close();
					crs = null;
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
			if (cstmt != null) {
				try {
					cstmt.close();
					cstmt = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	/**
	 * 产品搜索
	 * 
	 * @param pb
	 *            分页对象
	 * @param cid
	 *            分类ID
	 * @param title
	 *            商品名
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> searchCommodity(PageBean pb, int cid, String title) throws Exception {
		PreparedStatement stmt = null; // 产品语句对象
		ResultSet rs = null; // 产品数据集
		List<Commodity> list = new ArrayList<Commodity>(); // 产品集合

		PreparedStatement cstmt = null; // 分类语句对象
		ResultSet crs = null; // 分类数据集
		List<Classification> clist = new ArrayList<Classification>(); // 分类集合
		// 查询总记录数；设置到pb对象中
		int totalCount = this.searchTotalCount(cid, title);
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
			// 商品

			// 判断 搜索条件为分类
			if (title == null || "".equals(title.trim())) {
				String sql = "select * from commodity where class_id=? limit ?,?;"; // SQL语句
				stmt = DBHelper.getConnection().prepareStatement(sql); // 创建链接对象
				stmt.setInt(1, cid);
				stmt.setInt(2, index);
				stmt.setInt(3, count);
			} else { // 搜索条件为分类与名称
				String sql = "select * from commodity where title like concat('%',?,'%') and class_id=? limit ?,?;"; // SQL语句
				stmt = DBHelper.getConnection().prepareStatement(sql); // 创建链接对象
				stmt.setString(1, title);
				stmt.setInt(2, cid);
				stmt.setInt(3, index);
				stmt.setInt(4, count);
			}

			while (rs.next()) {
				Commodity lists = new Commodity();
				lists.setId(rs.getInt("id"));
				lists.setTitle(rs.getString("title"));
				lists.setPrice(rs.getFloat("price"));
				lists.setPhoto(rs.getString("photo"));
				list.add(lists); // 把一个商品信息加入集合
			}

			// 分类
			String sqls = "select * from commodity_class"; // SQL语句
			cstmt = DBHelper.getConnection().prepareStatement(sqls); // 创建链接对象
			crs = cstmt.executeQuery(); // 获得数据集
			while (crs.next()) {
				Classification lists = new Classification();
				lists.setId(crs.getInt("id"));
				lists.setTitle(crs.getString("title"));
				clist.add(lists); // 把一个分类信息加入集合
			}

			String url = "search_commodity&cid=" + cid + "&title=" + title;
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("PageDate", pb);
			map.put("list", list);
			map.put("clist", clist);
			map.put("cid", cid); // 分类 用于判断分页与跳转
			map.put("url", url); // 搜索商品名 用于分页跳转
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
			if (crs != null) {
				try {
					crs.close();
					crs = null;
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
			if (cstmt != null) {
				try {
					cstmt.close();
					cstmt = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	// 红酒知识
	public Map<String, Object> getKnowledge(PageBean pb) throws Exception {
		PreparedStatement stmt = null; // 语句对象
		ResultSet rs = null; // 数据集
		List<Knowledge> list = new ArrayList<Knowledge>(); // 红酒知识集合

		// 查询总记录数；设置到pb对象中
		int totalCount = this.getTotalCount("knowledge");
		// System.out.println(totalCount);
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

	// 公司资讯
	public Map<String, Object> getNews(PageBean pb) throws Exception {
		PreparedStatement stmt = null; // 语句对象
		ResultSet rs = null; // 数据集
		List<Information> list = new ArrayList<Information>(); // 公司资讯集合

		// 查询总记录数；设置到pb对象中
		int totalCount = this.getTotalCount("information");
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
			stmt.setInt(1, index);
			stmt.setInt(2, count);
			rs = stmt.executeQuery(); // 获得数据集
			while (rs.next()) {
				Information lists = new Information();
				lists.setId(rs.getInt("id"));
				lists.setTitle(rs.getString("title"));
				lists.setDate(rs.getString("date"));
				list.add(lists); // 把一个公司咨訊加入集合
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

	/**
	 * 公司资讯 详情
	 * @param id    详情ID
	 * @throws Exception 
	 * @throws SQLException 
	 */
	public Information informationDetail(int id) throws SQLException, Exception {
		PreparedStatement stmt = null; // 语句对象
		ResultSet rs = null; // 数据集

		String sql = "select * from Information where id=? ;";  // SQL语句
		stmt = DBHelper.getConnection().prepareStatement(sql);  // 创建链接对象
		stmt.setInt(1, id);
		rs = stmt.executeQuery(); // 获得数据集
			
		try{
			if (rs.next()) {
				Information lists = new Information();
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
	
	/**
	 * 红酒知识 详情
	 * @param id    详情ID
	 * @throws Exception 
	 * @throws SQLException 
	 */
	public Knowledge knowledgeDetail(int id) throws SQLException, Exception {
		PreparedStatement stmt = null; // 语句对象
		ResultSet rs = null; // 数据集
		
		String sql = "select * from knowledge where id=? ;";  // SQL语句
		stmt = DBHelper.getConnection().prepareStatement(sql);  // 创建链接对象
		stmt.setInt(1, id);
		rs = stmt.executeQuery(); // 获得数据集
			
		try{
			if (rs.next()) {
				Knowledge lists = new Knowledge();
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

	/**
	 * 商品 详情
	 * @param id    详情ID
	 * @throws Exception 
	 * @throws SQLException 
	 */
	public Map<String, Object> commodityDetail(int id) throws SQLException, Exception {
		PreparedStatement stmt = null; // 语句对象
		ResultSet rs = null; // 数据集
		
		PreparedStatement cstmt = null; // 分类语句对象
		ResultSet crs = null; // 分类数据集
		List<Classification> clist = new ArrayList<Classification>(); // 分类集合
		Map<String, Object> map = new HashMap<String, Object>();
			
		try{
			// 分类
			String sqls = "select * from commodity_class"; // SQL语句
			cstmt = DBHelper.getConnection().prepareStatement(sqls); // 创建链接对象
			crs = cstmt.executeQuery(); // 获得数据集
			while (crs.next()) {
				Classification list = new Classification();
				list.setId(crs.getInt("id"));
				list.setTitle(crs.getString("title"));
				clist.add(list); // 把一个分类信息加入集合
				map.put("clist", clist);
			}
			// 商品
			String sql = "select * from commodity where id=? ;";  // SQL语句
			stmt = DBHelper.getConnection().prepareStatement(sql);  // 创建链接对象
			stmt.setInt(1, id);
			rs = stmt.executeQuery(); // 获得数据集
			while(rs.next()) {
				Commodity lists = new Commodity();
				lists.setTitle(rs.getString("title"));
				lists.setPrice(rs.getFloat("price"));
				lists.setSpecifications(rs.getString("specifications"));
				lists.setPlace(rs.getString("place"));
				lists.setAlcohol(rs.getString("alcohol"));
				lists.setPhoto(rs.getString("photo"));
				lists.setContent(rs.getString("content"));
				map.put("list", lists);
			} 
			
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
	
	// 首页展示最新动态（公司资讯）
	public ArrayList<Information> indexNews() {
		PreparedStatement stmt = null; // 语句对象
		ResultSet rs = null; // 数据集
		ArrayList<Information> list = new ArrayList<Information>(); // 公司资讯集合

		try {
			String sql = "select * from information order by id desc limit 0,5 "; // SQL语句
			stmt = DBHelper.getConnection().prepareStatement(sql); // 创建链接对象
			rs = stmt.executeQuery(); // 获得数据集
			while (rs.next()) {
				Information lists = new Information();
				lists.setId(rs.getInt("id"));
				lists.setTitle(rs.getString("title"));
				list.add(lists); 
			}
			return list;
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
	
	// 首页展示红酒知识
	public ArrayList<Knowledge> indexKnowledge() {
		PreparedStatement stmt = null; // 语句对象
		ResultSet rs = null; // 数据集
		ArrayList<Knowledge> list = new ArrayList<Knowledge>(); // 公司资讯集合

		try {
			String sql = "select * from knowledge order by id desc limit 0,5 "; // SQL语句
			stmt = DBHelper.getConnection().prepareStatement(sql); // 创建链接对象
			rs = stmt.executeQuery(); // 获得数据集
			while (rs.next()) {
				Knowledge lists = new Knowledge();
				lists.setId(rs.getInt("id"));
				lists.setTitle(rs.getString("title"));
				list.add(lists); 
			}
			return list;
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
	
	// 招贤纳士
	public ArrayList<Recruit> getRecruit() {
		PreparedStatement stmt = null; // 语句对象
		ResultSet rs = null; // 数据集
		ArrayList<Recruit> list = new ArrayList<Recruit>(); // 公司资讯集合

		try {
			String sql = "select * from recruit "; // SQL语句
			stmt = DBHelper.getConnection().prepareStatement(sql); // 创建链接对象
			rs = stmt.executeQuery(); // 获得数据集
			while (rs.next()) {
				Recruit lists = new Recruit();
				// lists.setId(rs.getInt("id"));
				lists.setTitle(rs.getString("title"));
				lists.setJob_describe(rs.getString("job_describe"));
				lists.setRequirement(rs.getString("requirement"));
				list.add(lists); // 把一个招聘信息加入集合
			}
			return list;
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

	/**
	 * 产品展示搜索 获取总数
	 * 
	 * @param cid
	 *            分类ID
	 * @param title
	 *            商品名
	 * @return
	 * @throws Exception
	 */
	public int searchTotalCount(int cid, String title) throws Exception {
		PreparedStatement pStatement = null; // 语句对象
		ResultSet rSet = null; // 数据集
		int count = 0;
		try {
			// 判断 搜索条件为分类
			if (title == null || "".equals(title.trim())) {
				String sql = "select * from commodity where class_id=? "; // SQL语句
				pStatement = DBHelper.getConnection().prepareStatement(sql);
				pStatement.setInt(1, cid);
			} else { // 搜索条件为分类与名称
				String sql = "select * from commodity where title like concat('%',?,'%') and class_id=? "; // SQL语句
				pStatement = DBHelper.getConnection().prepareStatement(sql); // 创建链接对象
				pStatement.setString(1, title);
				pStatement.setInt(2, cid);
			}
			rSet = pStatement.executeQuery();
			rSet.last(); // 移到最后一行
			count = rSet.getRow(); // 得到当前行号，也就是记录数
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

	// 获取总数
	public int getTotalCount(String table) throws Exception {
		PreparedStatement pStatement = null; // 语句对象
		ResultSet rSet = null; // 数据集
		String sql = "select count(*) from " + table; // SQL语句
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
}