<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
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
    <link rel="stylesheet" href="${pageContext.request.contextPath }/Home/css/orbit-1.2.3.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/Home/css/jq22.css">
    <script src="${pageContext.request.contextPath }/Home/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/Home/js/jquery.orbit-1.2.3.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/Home/css/base.css"/>
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

    <!--内容部分-->
    <div class="content_boxs">

        <!--左侧导航-->
        <div class="nav_left">

            <!--经营产品-->
            <div class="manage_products_title">
                <img  src="${pageContext.request.contextPath }/Home/image/manage_products_title.png" />
            </div>
            <ul class="manage_products_list">
            <c:choose>
				<c:when test="${not empty result.clist}">
					<c:forEach var="result" items="${result.clist}">
					<a href="${pageContext.request.contextPath }/servlet/HomeServlet?method=search_commodity&cid=${result.id}"><li class="manage_products_unchecked">${result.title}</li></a>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<li>当前数据为空！</li>
				</c:otherwise>
			</c:choose>
            </ul>

            <!--产品搜索-->
            <div class="search_box">
                <div class="manage_products_title">
                    <img  src="${pageContext.request.contextPath }/Home/image/product_search_title.png" />
                </div>

                <div class="search">
	                <form method="post" action="${pageContext.request.contextPath }/servlet/HomeServlet?method=search_commodity" style="text-align:center">
	                   <div id="select" class="bg1">
	                       <div class="search_sign" id="country">请选择分类</div>
	                       <ul id="option" class="hide">
	                       <c:choose>
							<c:when test="${not empty result.clist}">
								<c:forEach var="result" items="${result.clist}">
								<li data-id="${result.id}">${result.title}</li>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<li>当前数据为空！</li>
							</c:otherwise>
						</c:choose>
	                       </ul>
	                   </div>
	                   <input type="text" name="cid" class="hide" id="cid" value=""/>
	                   <input type="text" placeholder="请输入名称" class="search_name" name="search_name" />
	                   <input class="search_click" value="搜索" type="submit" />
	                </form>
                </div>
            </div>

        </div>

        <!--右侧商品展示部分-->
        <div class="right_box">
            <!--标题-->
            <div class="manage_products_title" style="width: 850px">
                <img  src="${pageContext.request.contextPath }/Home/image/product_recommendation.png" />
            </div>

            <!--商品-->
            <c:choose>
				<c:when test="${not empty result.list}">
					<c:forEach var="result" items="${result.list}">
					<div class="goods">
		                <!-- <img src="${result.photo}"> -->
		                <a href="${pageContext.request.contextPath }/servlet/HomeServlet?id=${result.id}&method=commodity_detail"><img src="${pageContext.request.contextPath }/upload/${result.photo}"></a>
		                <p> ${result.title} </p>
		                <p>价格：${result.price} RMB</p>
		            </div>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<li>当前数据为空！</li>
				</c:otherwise>
			</c:choose>
            
            <!--分页-->
            <div class="page_box" id="${result.url}">
                 
					<!-- 正常展示 -->
						<ul>
		                    <li class="page_choice"><a href="${pageContext.request.contextPath }/servlet/HomeServlet?currentPage=1&method=${(not empty result.cid) ? result.url : 'commodity'}" class="list_a">首页</a></li>
		                    <li class="page_choice"><a href="${pageContext.request.contextPath }/servlet/HomeServlet?currentPage=${result.PageDate.currentPage-1}&method=${(not empty result.cid) ? result.url : 'commodity'}" class="list_a">上一页</a></li>
		                    <li class="page_num">${result.PageDate.currentPage}/${result.PageDate.totalPage}</li>
		                    <li class="page_choice"><a href="${pageContext.request.contextPath }/servlet/HomeServlet?currentPage=${result.PageDate.currentPage+1}&method=${(not empty result.cid) ? result.url : 'commodity'}" class="list_a">下一页</a></li>
		                    <li class="page_choice"><a href="${pageContext.request.contextPath }/servlet/HomeServlet?currentPage=${result.PageDate.totalPage}&method=${(not empty result.cid) ? result.url : 'commodity'}" class="list_a">尾页</a></li>
		                </ul>
            </div>
        </div>
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
        <p>联系电话：010-62137141 (传真)62131450 电子邮箱:changchunying@tom.com 京ICP备14058053号</p>
    </div>

</div>
</body>
<script>
    var $aLi = $("#option li");
    var $country = $("#country");
    var $select = $("#select");
    var $option = $("#option");

    $select.click(function(){
        $select.stop().toggleClass("bg2");
        $option.stop().slideToggle();
    })
    $aLi.each(function(){
        $(this).hover(function(){
            $(this).css({color:"#000"})
        },function(){
            $(this).css({backgroundColor:"#fff",color:"#9fa0a0"})
        })
        $(this).click(function(){
            $country.text($(this).text());
            $country.attr("value",$(this).text());
            var cid = $(this).attr("data-id");
            $("#cid").attr("value", cid);
        })
    })
</script>
</html>