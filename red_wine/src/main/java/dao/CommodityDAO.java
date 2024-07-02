package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import entity.Commodity;
import util.DBHelper;
import util.PageBean;

//商品
public class CommodityDAO {

	// 获取所有商品的信息
	public Map<String, Object> getAll(PageBean pb) throws Exception {
		PreparedStatement stmt = null; // 语句对象
		ResultSet rs = null; // 数据集
		List<Commodity> list = new ArrayList<Commodity>(); // 商品集合
		
		// 查询总记录数；设置到pb对象中
		int totalCount = this.getTotalCount();
		pb.setTotalCount(totalCount);

		//判断（预防当前页为首页或末页时，点击上一页或下一页出错）
		if (pb.getCurrentPage() <= 0) {
			pb.setCurrentPage(1); 						//把当前页设置为1
		} else if (pb.getCurrentPage() > pb.getTotalPage()){
			pb.setCurrentPage(pb.getTotalPage());       //把当前页设置为最大页数
		}
		
		// 获取当前页：计算查询的起始行、返回的行数
		int currentPage = pb.getCurrentPage(); // 当前页
		int index = (currentPage - 1) * pb.getPageCount(); // 查询的起始行
		int count = pb.getPageCount(); // 查询返回的行数
		
		try {
			String sql = "select * from commodity limit ?,?;"; // SQL语句
			stmt = DBHelper.getConnection().prepareStatement(sql); // 创建链接对象
			stmt.setInt(1, index);
			stmt.setInt(2, count);
			rs = stmt.executeQuery(); // 获得数据集

			while (rs.next()) {
				Commodity lists = new Commodity();
				lists.setId(rs.getInt("id"));
				lists.setClass_id(rs.getInt("class_id"));
				lists.setTitle(rs.getString("title"));
				lists.setPrice(rs.getFloat("price"));
				lists.setSpecifications(rs.getString("specifications"));
				lists.setPlace(rs.getString("place"));
				lists.setAlcohol(rs.getString("alcohol"));
				lists.setPhoto(rs.getString("photo"));
				lists.setContent(rs.getString("content"));
				list.add(lists); // 把一个商品信息加入集合
			}
			
			Map<String,Object> map = new HashMap<String, Object>();
		    map.put("PageDate",pb);
			map.put("list",list);
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
		String sql = "select count(*) from commodity"; // SQL语句
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
	public void add(Commodity commodity) {
		String sql = "insert into commodity(class_id,title,price,specifications,place,alcohol,photo,content) values(?,?,?,?,?,?,?,?);"; // 定义sql语句
		PreparedStatement pstmt = null;

		try {
			pstmt = DBHelper.getConnection().prepareStatement(sql); // 创建链接对象
			pstmt.setInt(1, commodity.getClass_id());
			pstmt.setString(2, commodity.getTitle());
			pstmt.setFloat(3, commodity.getPrice());
			pstmt.setString(4, commodity.getSpecifications());
			pstmt.setString(5, commodity.getPlace());
			pstmt.setString(6, commodity.getAlcohol());
			pstmt.setString(7, commodity.getPhoto());
			pstmt.setString(8, commodity.getContent());
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
	public void delete(Commodity commodity) {
		String sql = "delete from commodity where id=?;"; // 定义sql语句
		PreparedStatement pstmt = null;

		try {
			pstmt = DBHelper.getConnection().prepareStatement(sql); // 创建链接对象
			pstmt.setInt(1, commodity.getId());
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

	// 获取单个商品的信息
	public Commodity getone(Commodity commodity) {

		PreparedStatement stmt = null; // 语句对象
		ResultSet rs = null; // 数据集
		try {
			String sql = "select * from commodity where id=? ;"; // SQL语句
			stmt = DBHelper.getConnection().prepareStatement(sql); // 创建链接对象
			stmt.setInt(1, commodity.getId());
			rs = stmt.executeQuery(); // 获得数据集

			if (rs.next()) {
				Commodity lists = new Commodity();
				lists.setId(rs.getInt("id"));
				lists.setClass_id(rs.getInt("class_id"));
				lists.setTitle(rs.getString("title"));
				lists.setPrice(rs.getFloat("price"));
				lists.setSpecifications(rs.getString("specifications"));
				lists.setPlace(rs.getString("place"));
				lists.setAlcohol(rs.getString("alcohol"));
				lists.setPhoto(rs.getString("photo"));
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

	// 查询商品的信息
	public Map<String, Object> search(PageBean pb,Commodity commodity) throws Exception {

		PreparedStatement stmt = null; // 语句对象
		ResultSet rs = null; // 数据集
		List<Commodity> list = new ArrayList<Commodity>(); // 商品集合
		
		// 查询总记录数；设置到pb对象中
		int totalCount = this.getTotalCount();
		pb.setTotalCount(totalCount);

		//判断（预防当前页为首页或末页时，点击上一页或下一页出错）
		if (pb.getCurrentPage() <= 0) {
			pb.setCurrentPage(1); 						//把当前页设置为1
		} else if (pb.getCurrentPage() > pb.getTotalPage()){
			pb.setCurrentPage(pb.getTotalPage());       //把当前页设置为最大页数
		}
		// 获取当前页：计算查询的起始行、返回的行数
		int currentPage = pb.getCurrentPage(); // 当前页
		int index = (currentPage - 1) * pb.getPageCount(); // 查询的起始行
		int count = pb.getPageCount(); // 查询返回的行数
		try {
			String sql = "select * from commodity where title like concat('%',?,'%') limit ?,? ;"; // SQL语句
			stmt = DBHelper.getConnection().prepareStatement(sql); // 创建链接对象
			stmt.setString(1, commodity.getTitle());
			stmt.setInt(2, index);
			stmt.setInt(3, count);
			rs = stmt.executeQuery(); // 获得数据集
			
			while (rs.next()) {
				Commodity lists = new Commodity();
				lists.setId(rs.getInt("id"));
				lists.setClass_id(rs.getInt("class_id"));
				lists.setTitle(rs.getString("title"));
				lists.setPrice(rs.getFloat("price"));
				lists.setSpecifications(rs.getString("specifications"));
				lists.setPlace(rs.getString("place"));
				lists.setAlcohol(rs.getString("alcohol"));
				lists.setPhoto(rs.getString("photo"));
				lists.setContent(rs.getString("content"));
				list.add(lists); // 把一个商品信息加入集合
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
	public void update(Commodity commodity) {
		
		PreparedStatement pstmt = null;
		try {
			String photo = commodity.getPhoto();
			if (photo == null || "".equals(photo.trim())) {
				String sql = "update commodity set class_id=?,title=?,price=?,specifications=?,place=?,alcohol=?,content=? where id=? ;"; // 定义sql语句
				pstmt = DBHelper.getConnection().prepareStatement(sql); // 创建链接对象
				pstmt.setInt(1, commodity.getClass_id());
				pstmt.setString(2, commodity.getTitle());
				pstmt.setFloat(3, commodity.getPrice());
				pstmt.setString(4, commodity.getSpecifications());
				pstmt.setString(5, commodity.getPlace());
				pstmt.setString(6, commodity.getAlcohol());
				pstmt.setString(7, commodity.getContent());
				pstmt.setInt(8, commodity.getId());
				pstmt.execute();
			} else {
				String sql = "update commodity set class_id=?,title=?,price=?,specifications=?,place=?,alcohol=?,photo=?,content=? where id=? ;"; // 定义sql语句
				pstmt = DBHelper.getConnection().prepareStatement(sql); // 创建链接对象
				pstmt.setInt(1, commodity.getClass_id());
				pstmt.setString(2, commodity.getTitle());
				pstmt.setFloat(3, commodity.getPrice());
				pstmt.setString(4, commodity.getSpecifications());
				pstmt.setString(5, commodity.getPlace());
				pstmt.setString(6, commodity.getAlcohol());
				pstmt.setString(7, photo);
				pstmt.setString(8, commodity.getContent());
				pstmt.setInt(9, commodity.getId());
				pstmt.execute();
			}
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
