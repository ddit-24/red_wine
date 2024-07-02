<%@page import="entity.Knowledge"%>
<%@page import="entity.Information"%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<%@ page import="dao.HomeDAO" %>
<!DOCTYPE HTML>
<html lang="zh-cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Content-Type" content="text/html"; charset="utf-8"/>
    <meta content="yes" name="apple-mobile-web-app-capable"/>
    <meta content="telephone=no" name="format-detection"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"  />
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <!--<script type="text/javascript"  src="js/jquery-1.8.2.min.js"></script>-->
    <title>ChrosWine红酒有限公司</title>
    <link rel="stylesheet" href="css/orbit-1.2.3.css">
    <link rel="stylesheet" href="css/jq22.css">
    <script src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/jquery.orbit-1.2.3.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/base.css"/>
    <script type="text/javascript" src="js/base.js"></script>

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
	<!-- 网站Logo -->
    <img src="image/header.png" />
    <!-- 导航栏 -->
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
            <img src="image/banner.jpg" />
            <img src="image/banner2.jpg" />
            <img src="image/banner3.jpg" />
        </div>
    </div>

    <!--资讯与知识-->
    <div class="content_box">

        <!--最新资讯-->
        <div class="news_box">
            <div class="manage_products_title news_title">
                <img  src="image/news_sign.png" />
                <a href="${pageContext.request.contextPath }/servlet/HomeServlet?method=news"><div class="click_div"></div></a>
            </div>
            <ul class="news_list">
            <%
            //创建HomeDAO实例
            HomeDAO dao = new HomeDAO(); 
            //调用HomeDAO中的indexNews()方法得到最新公司资讯结果集
            ArrayList<Information> list = dao.indexNews();
            //数据集不为空时
            if (list != null && list.size() > 0)
            {
            //循环输出最新公司资讯结果集
            	for(int i=0;i<list.size();i++)
            	{
            		Information result = list.get(i);
             %>     
                <a href="${pageContext.request.contextPath }/servlet/HomeServlet?method=information_detail&id=<%=result.getId() %>"><li> >>  <%=result.getTitle() %></li></a>
            <%
            	}
            }
             %>
            </ul>
        </div>

        <!--红酒知识-->
        <div class="knowledge_box">
            <div class="manage_products_title news_title">
                <img  src="image/knowledge_sign.png" />
                <a href="${pageContext.request.contextPath }/servlet/HomeServlet?method=knowledge"><div class="click_div"></div></a>
            </div>
            <ul class="news_list">
                <%
               		//调用HomeDAO中的indexKnowledge()方法得到最新红酒知识结果集
		            ArrayList<Knowledge> lists = dao.indexKnowledge();
		            //数据集不为空时
		            if (lists != null && lists.size() > 0)
		            {
		            	//循环输出最新红酒知识结果集
		            	for(int i=0;i<lists.size();i++)
		            	{
		            		Knowledge result = lists.get(i);
		             %>     
		                <a href="${pageContext.request.contextPath }/servlet/HomeServlet?method=knowledge_detail&id=<%=result.getId() %>"><li> >>  <%=result.getTitle() %></li></a>
		            <%
		            	}
		            }
		             %>
            </ul>
        </div>

    </div>

    <!--底部-->
    <div class="foot">
        <div class="foot_img">
            <img src="image/foot_a.png" />
            <img src="image/foot_b.png" />
            <img src="image/foot_c.png" />
            <img src="image/foot_d.png" />
        </div>
        <p>ChrosWine红酒有限公司</p>
        <p>公司地址:北京市海淀区大钟寺华杰大厦4C9室 邮编:100098</p>
        <p>联系电话：010-62137141 (传真)62131450 电子邮箱:changchunying@tom.com 京ICP备14058053号</p>
    </div>

</div>
</body>
</html>