<%@page import="util.DBHelper"%>
<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="java.sql.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%
	String name = request.getParameter("name"); //用户名
	String password = request.getParameter("password"); //密码 
	//判断用户名或密码是否为空
	if (name == null || "".equals(name.trim()) || password == null || "".equals(password.trim())) { 
		out.println("用户名或密码不能为空！");
		response.sendRedirect("login.jsp");
		return;
	}

	boolean isValid = false;   //定义isValid变量为布尔值false
	PreparedStatement stmt = null; // 语句对象
	ResultSet rs = null; // 结果集对象
	try {
		String sql = "select * from administrators where name=? and password=?;";  //SQL语句
		stmt = DBHelper.getConnection().prepareStatement(sql);		//创建链接对象
		stmt.setString(1, name);		//设置SQL语句的第一个参数用户名的值
		stmt.setString(2, password);	//设置SQL语句第二个参数密码的值
		rs = stmt.executeQuery();		//获得数据集
		if (rs.next()) {				//如果rs有值,则执行
			isValid = true;				//变量isValid的值变为true
		}
	} catch (Exception e) {
		e.printStackTrace();
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
	//判断是否登录成功
	if (isValid) {
		out.println("登录成功！");
		session.setAttribute("name", name);
		response.sendRedirect("../sys/index.jsp");
		return;
	} else {
		out.println("登录失败！");
		response.sendRedirect("../sys/login.jsp");
		return;
	}
%>