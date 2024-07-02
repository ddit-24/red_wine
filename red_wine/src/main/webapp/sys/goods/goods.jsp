<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<!-- 包含公共的JSP代码片段 -->
	
<title>ChrosWine红酒有限公司管理系统</title>
<meta http-equiv="Content-Type" content="text/jsp; charset=utf-8" />
<script type="text/javascript" src="${pageContext.request.contextPath }/sys/style/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/sys/style/js/page_common.js"></script>
<link href="${pageContext.request.contextPath }/sys/style/css/common_style_blue.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/sys/style/css/index_1.css" />
<style type="text/css">
.TableDetail1 td{text-align: center}
</style>
</head>
<body>
<!-- 页面标题 -->
<div id="TitleArea">
	<div id="TitleArea_Head"></div>
	<div id="TitleArea_Title">
		<div id="TitleArea_Title_Content">
			<img border="0" width="13" height="13" src="${pageContext.request.contextPath }/sys/style/images/title_arrow.gif"/> 商品列表
		</div>
    </div>
	<div id="TitleArea_End"></div>
</div>


	<!-- 过滤条件 -->
	<div id="QueryArea">
		<form action="${pageContext.request.contextPath }/servlet/CommodityServlet?id=${result.id}&method=search" method="post">
			<input type="text" name="title" title="请输入商品名称">
			<input type="submit" value="搜索">
		</form>
	</div>
<!-- 主内容区域（数据列表或表单显示） -->
<div id="MainArea">
    <table class="MainArea_Content" align="center" cellspacing="0" cellpadding="0">
        <!-- 表头-->
        <thead>
            <tr align="center" valign="middle" id="TableTitle">
				<td>商品编号</td>
				<td>分类编号</td>
				<td>红酒名</td>
				<td>价格</td>
                <td>规格</td>
                <td>产地</td>
                <td>酒精度</td>
				<td>操作</td>
			</tr>
		</thead>	
		<!--显示数据列表 -->
        <tbody id="TableData">
		
		<c:choose>
			<c:when test="${not empty result.list}">
				
				<c:forEach var="result" items="${result.list}" >
					<tr class="TableDetail1">
						<td>${result.id}</td>
						<td>${result.class_id}</td>
						<td>${result.title}</td>					
						<td>${result.price}</td>
						<td>${result.specifications}</td>
						<td>${result.place}</td>
						<td>${result.alcohol}</td>
						<td>
							<a href="${pageContext.request.contextPath }/servlet/CommodityServlet?id=${result.id}&method=Jumpup" class="FunctionButton">更新</a> 
							<a href="${pageContext.request.contextPath }/servlet/CommodityServlet?id=${result.id}&method=delete" onClick="return delConfirm();" class="FunctionButton">删除</a>
						</td>
					</tr>
				</c:forEach>
			
			</c:when>
			
			<c:otherwise>
				<tr class="TableDetail1">
					<td colspan="8" style="text-align:center">没有你要找的数据，请先添加记录再查看！</td>
				</tr>
			</c:otherwise>
		</c:choose>
        </tbody>
    </table>
	
   <!-- 其他功能超链接 -->
	<div id="TableTail" align="center">
		<div class="FunctionButton"><a href="${pageContext.request.contextPath }/sys/goods/add.jsp">添加</a></div>
		<div class="page_box">
			<a href="${pageContext.request.contextPath }/servlet/CommodityServlet?currentPage=1&method=list">首页</a>
			<a href="${pageContext.request.contextPath }/servlet/CommodityServlet?currentPage=${result.PageDate.currentPage-1}&method=list">上一页</a>
			<a>${result.PageDate.currentPage}/${result.PageDate.totalPage}</a>
			<a href="${pageContext.request.contextPath }/servlet/CommodityServlet?currentPage=${result.PageDate.currentPage+1}&method=list">下一页</a>
			<a href="${pageContext.request.contextPath }/servlet/CommodityServlet?currentPage=${result.PageDate.totalPage}&method=list">末页</a>
		</div>
    </div> 
</div>
</body>
</html>