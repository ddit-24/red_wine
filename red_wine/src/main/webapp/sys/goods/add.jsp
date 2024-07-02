<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="entity.Classification"%>
<%@ page import="util.DBHelper"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.ResultSet"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
   <!-- 包含公共的JSP代码片段 -->
	
<title>ChrosWine红酒有限公司管理系统</title>



<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="${pageContext.request.contextPath }/sys/style/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/sys/style/js/page_common.js"></script>
<link href="${pageContext.request.contextPath }/sys/style/css/common_style_blue.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/sys/style/css/index_1.css" />
</head>
<body>

<!-- 页面标题 -->
<div id="TitleArea">
	<div id="TitleArea_Head"></div>
	<div id="TitleArea_Title">
		<div id="TitleArea_Title_Content">
			
				
				
					<img border="0" width="13" height="13" src="${pageContext.request.contextPath }/sys/style/images/title_arrow.gif"/> 添加新商品
				
			
		</div>
    </div>
	<div id="TitleArea_End"></div>
</div>

<!-- 主内容区域（数据列表或表单显示） -->
<div id="MainArea">
	<!-- 表单内容 -->
	<form action="${pageContext.request.contextPath }/servlet/UploadServlet?method=add" method="post" enctype="multipart/form-data">
		<!-- 本段标题（分段标题） -->
		<div class="ItemBlock_Title">
        	<img width="4" height="7" border="0" src="${pageContext.request.contextPath }/sys/style/images/item_point.gif"> 商品信息&nbsp;
        </div>
		<!-- 本段表单字段 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
				<div class="ItemBlock2">
					<table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
							<td width="150px">红酒分类</td>
							<td>
                            <select name="class_id" style="width:150px">
                            <%
                            PreparedStatement stmt = null; // 语句对象
							ResultSet rs = null; // 数据集
							List<Classification> list = new ArrayList<Classification>(); // 商品分類集合
                            String sql = "select * from commodity_class"; // SQL语句
							stmt = DBHelper.getConnection().prepareStatement(sql); // 创建链接对象
							rs = stmt.executeQuery(); // 获得数据集
								while (rs.next()) {
									Classification lists = new Classification();
									lists.setId(rs.getInt("id"));
									lists.setTitle(rs.getString("title"));
									list.add(lists); // 把一个分類信息加入集合
								}
								if (list != null && list.size() > 0) {
									for (int i = 0; i < list.size(); i++) {
										Classification lists = list.get(i);
							%>             
			   						<option value="<%=lists.getId()%>"><%=lists.getTitle()%></option>	
		   					<%
			   						}
			   					}
		   					 %>
			   						
			   					
                            </select>
                             *</td>
						</tr>
						<tr>
							<td width="80px">红酒名称</td>
							<td><input type="text" name="title" class="InputStyle" value=""/> *</td>
						</tr>
						<tr>
							<td>价格</td>
							<td><input type="text" name="price" class="InputStyle" value=""/> *</td>
						</tr>
                        <tr>
							<td>规格</td>
							<td><input type="text" name="specifications" class="InputStyle" value=""/> *</td>
						</tr>
						<tr>
							<td>产地</td>
							<td><input type="text" name="place" class="InputStyle" value=""/> *</td>
						</tr>
						<tr>
							<td>酒精度</td>
							<td><input type="text" name="alcohol" class="InputStyle" value=""/> *</td>
						</tr>
						
						<tr>
							<td>简介</td>
							<td><textarea name="content" class="TextareaStyle"></textarea></td>
						</tr>
						<tr>
							<td width="80px">菜品图片</td>
							<td>
								<input type="file" name="photo"/> *
							</td>
						</tr>
					</table>
				</div>
            </div>
        </div>
		
		
		<!-- 表单操作 -->
		<div id="InputDetailBar">
            
				
				
					 <input type="submit" value="添加" class="FunctionButtonInput">
				
			
            
            <a href="javascript:history.go(-1);" class="FunctionButton">返回</a>
        </div>
	</form>
</div>
</body>
</html>
