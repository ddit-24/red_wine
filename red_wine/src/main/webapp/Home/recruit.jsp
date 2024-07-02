<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="renderer" content="webkit">
<meta http-equiv="Content-Type" content="text/html" ; charset="utf-8" />
<meta content="yes" name="apple-mobile-web-app-capable" />
<meta content="telephone=no" name="format-detection" />
<meta name="apple-mobile-web-app-status-bar-style"
	content="black-translucent" />
<meta name="viewport"
	content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<!--<script type="text/javascript"  src="js/jquery-1.8.2.min.js"></script>-->
<title>ChrosWine红酒有限公司</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/Home/css/orbit-1.2.3.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/Home/css/jq22.css">
<script src="${pageContext.request.contextPath }/Home/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/Home/js/jquery.orbit-1.2.3.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/Home/css/base.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/Home/js/base.js"></script>

<!--[if IE]>
    <style type="text/css">
        .timer { display: none !important; }
        div.caption { background:transparent; filter:progid:DXImageTransform.Microsoft.gradient(startColorstr=#99000000,endColorstr=#99000000);zoom: 1; }
    </style>
    <![endif]-->
<!-- Run the plugin -->
<script type="text/javascript">
	$(window).load(function() {
		$('#featured').orbit();
	});
</script>
</head>
<body>
	<div class="red_wine_box">
		<img src="${pageContext.request.contextPath }/Home/image/header.png" />

		<!--导航-->
		<div class="navigation_box">
	        <a href="${pageContext.request.contextPath }/Home/index.jsp">网站首页</a>
	        <a href="${pageContext.request.contextPath }/Home/company_profile.jsp">公司简介</a>
	        <a href="${pageContext.request.contextPath }/servlet/HomeServlet?method=commodity">产品展示</a>
	        <a href="${pageContext.request.contextPath }/servlet/HomeServlet?method=knowledge">红酒知识</a>
	        <a href="${pageContext.request.contextPath }/servlet/HomeServlet?method=news">公司资讯</a>
	        <a href="${pageContext.request.contextPath }/servlet/HomeServlet?method=recruit">招贤纳士</a>
    	</div>

		<!--轮播-->
		<div class="container">
			<div id="featured">
				<img src="${pageContext.request.contextPath }/Home/image/banner.jpg" />
				<img src="${pageContext.request.contextPath }/Home/image/banner2.jpg" />
				<img src="${pageContext.request.contextPath }/Home/image/banner3.jpg" />
			</div>
		</div>

		<!--招贤纳士-->
		<div class="company_box">
			<div class="company_title" style="margin-bottom: 20px">
				<img src="${pageContext.request.contextPath }/Home/image/recruit_sign.png" />
			</div>
			
        <!-- 循环开始 -->
        <c:choose>
				<c:when test="${not empty requestScope.result}">
					<c:forEach var="result" items="${requestScope.result}">
						<div class="recruit_content">
				            <p><span>职位名称： </span>${result.title}</p>
				            <p><span>职位描述： </span>${result.job_describe}</p>
				            <p><span>招聘要求： </span>${result.requirement}</p>
				        </div>
					</c:forEach>
				</c:when>

				<c:otherwise>
						<li>当前数据为空！</li>
				</c:otherwise>
		</c:choose>
        <!-- 循环结束 -->
        
    </div>

		<div class="recruit_tel">
			<p>联 系 人：花倾城</p>
			<p>联系电话：400-1111-8888</p>
			<p>电子邮件：hhgj_hqc@163.com</p>
		</div>
		<img src="${pageContext.request.contextPath }/Home/image/recruit_logo.png"
			style="float: right;margin-right: 30px" />

	</div>
	<!--底部-->
	<div class="foot">
		<div class="foot_img">
			<img src="${pageContext.request.contextPath }/Home/image/foot_a.png" /> 
			<img src="${pageContext.request.contextPath }/Home/image/foot_b.png" /> 
			<img src="${pageContext.request.contextPath }/Home/image/foot_c.png" /> 
			<img src="${pageContext.request.contextPath }/Home/image/foot_d.png" />
		</div>
		<p>ChrosWine红酒有限公司</p>
		<p>公司地址:北京市海淀区大钟寺华杰大厦4C9室 邮编:100098</p>
		<p>联系电话：010-62137141 (传真)62131450 电子邮箱:changchunying@tom.com
			京ICP备14058053号</p>
	</div>


</body>
</html>