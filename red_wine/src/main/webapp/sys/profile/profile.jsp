<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<!-- 包含公共的JSP代码片段 -->

<title>ChrosWine红酒有限公司管理系统</title>

<meta http-equiv="Content-Type" content="text/jsp; charset=utf-8" />
<script type="text/javascript"
	src="${pageContext.request.contextPath }/sys/style/js/jquery.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/sys/style/js/page_common.js"></script>
<link
	href="${pageContext.request.contextPath }/sys/style/css/common_style_blue.css"
	rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/sys/style/css/index_1.css" />
<style type="text/css">
.TableDetail1 td {
	text-align: center
}
</style>
</head>
<body>
	<!-- 页面标题 -->
	<div id="TitleArea">
		<div id="TitleArea_Head"></div>
		<div id="TitleArea_Title">
			<div id="TitleArea_Title_Content">
				<img border="0" width="13" height="13"
					src="${pageContext.request.contextPath }/sys/style/images/title_arrow.gif" />
				公司简介
			</div>
		</div>
		<div id="TitleArea_End"></div>
	</div>

	<!-- 主内容区域  -->
	<div id="MainArea">
	<!-- 其他功能超链接 -->
		<div id="TableTail" align="center">
		<c:choose>
			<c:when test="${not empty result}">
				<div class="FunctionButton">
					<a href="${pageContext.request.contextPath }/servlet/ProfileServlet?id=${result.id}&method=Jump">更新</a>
				</div>
				<div class="FunctionButton">
					<a href="${pageContext.request.contextPath }/servlet/ProfileServlet?id=${result.id}&method=delete">删除</a>
				</div>
			</c:when>
			<c:otherwise>
				<div class="FunctionButton">
					<a href="${pageContext.request.contextPath }/sys/profile/add.jsp">添加</a>
				</div>
			</c:otherwise>
		</c:choose>
		</div>
		<div class="profile_box">
			<c:choose>
				<c:when test="${not empty result}">
					<textarea name="content" class="TextareaStyle" style="width: 80%;height: 300px;padding: 10px;border: 1px solid #91C0E3;">${result.content}</textarea>
					<input style="display:none" name="id" value="${result.id}" />
				</c:when>
				<c:otherwise>
					<textarea name="content" style="width: 80%;height: 300px;padding: 10px;border: 1px solid #91C0E3;" class="TextareaStyle"> 暂无数据！</textarea>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</body>
</html>