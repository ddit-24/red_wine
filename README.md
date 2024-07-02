# red_wine是一个红酒类的项目，目录结构如下
SRC
Dao（接口设计与实现）
HomeDAO   					所有的前端
AdministratorsDAO     管理员
ClassificationDAO     商品分类
CommodityDAO           商品
InformationDAO			公司咨询
KnowledgeDAO				红酒知识
ProfileDAO					公司简介
RecruitDAO					招贤纳士
Entity（实体类设计，主要是用于封装数据）
Administrators					管理员
Classification    		商品分类
Commodity	            商品
Information				公司咨询
Knowledge					红酒知识
Profile					   公司简介
Recruit						招贤纳士


Servlet（主要功能在于交互式地浏览和修改数据）
HomeServlet					所有前端
AdministratorsServlet 	管理员
ClassificationServlet 	商品分类
CommodityServlet				商品
InformationServlet			公司资讯
KnowledgeServlet				红酒知识
ProfileServlet				公司简介
RecruitServlet				招贤纳士
UploadServlet			上传文件（商品管理中的添加和更新）

Util（常用工具类）
DBHelper  						数据库类
PageBean  						分页类
WebRoot
Home（前端）
Index								首页
company_profile					公司简介	
goods_detail						商品详情		
Knowledge     					红酒知识	
knowledge_detail    			红酒知识详情
news								公司资讯
news_detail						公司资讯详情
product_display					商品展示
recruit								招贤纳士

sys （后台）
index							首页
login							登录页
action							处理登录的方法
loginout						退出登录
public                   公用文件
bottom    	页面底部
left       	页面左侧
right      	右侧欢迎页
top		  		页面顶部	
style 		 （包括CSS、JS和图片）


以下文件夹中：
1．文件夹同名的均为其展示页。
2．add为添加页
3．update为更新页。
/*********************************************************/
administrators					管理员
classify							分类
goods								产品
information         		公司资讯
knowledge       			红酒知识
profile							公司简介
recruit    						招贤纳士
/*********************************************************/
Upload      存放上传图片的文件夹
WEB-INF
lib       引用的开源组件
web.xml	  配置文件
