<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
.table{border-spacing: 0;width: 70%;text-align: left;margin-top:100px;border-top: 1px solid #ddd;}
.table th, .table td{border-right: 1px solid #ddd;border-left: 1px solid #ddd;border-bottom: 1px solid #ddd;padding: 8px;line-height: 20px; word-break: break-all;}
</style>
</head>
	<body>
		<center>
			<h1>ChrosWine红酒有限公司管理系统</h1>		
		<table class="table">
			<thead>
				<tr>
					<th colspan="2" scope="col" style="background-color: #f5fafe;">服务器信息</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>当前系统用户名 </td>
					<td>${sessionScope.name}</td>
				</tr>
				<tr>
					<td>域名</td>
					<td><% out.print(request.getHeader("Host")); %></td>
				</tr>
				<tr>
					<td>Tomcat</td>
					<td><%= application.getServerInfo() %></td>
				</tr>
				<tr>
					<td>物理路径</td>
					<td><% out.print(request.getRealPath("")); %></td>
				</tr>
				<tr>
					<td>登录时间</td>
					<td><%=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()) %></td>
				</tr>
			</tbody>
		</table>
		</center>
	</body>
</html>