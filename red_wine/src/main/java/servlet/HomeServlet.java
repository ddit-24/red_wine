package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.HomeDAO;
import entity.Information;
import entity.Knowledge;
import entity.Recruit;
import util.PageBean;

public class HomeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// 跳转资源
	private String uri;

	/**
	 * Constructor of the object.
	 */
	public HomeServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 设置编码
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// 获取操作的类型
		String method = request.getParameter("method");

		// 判断
		if ("knowledge".equals(method)) {
			// 红酒知识
			knowledge(request, response);
		} else if ("news".equals(method)) {
			// 公司资讯
			news(request, response);
		} else if ("recruit".equals(method)) {
			// 招贤纳士
			recruit(request, response);
		} else if ("commodity".equals(method)) {
			// 产品展示
			commodity(request, response);
		} else if ("search_commodity".equals(method)) {
			// 产品展示搜索
			search_commodity(request, response);
		} else if ("commodity_detail".equals(method)) {
			// 商品详情
			commodity_detail(request, response);
		} else if ("knowledge_detail".equals(method)) {
			// 红酒知识详情
			knowledge_detail(request, response);
		} else if ("information_detail".equals(method)) {
			// 公司资讯详情
			information_detail(request, response);
		}

	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	// 产品展示
	public void commodity(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			// 获取“当前页”参数；（当第一次访问当前页为null）
			String currPage = request.getParameter("currentPage");
			// 判断
			if (currPage == null || "".equals(currPage.trim())) {
				currPage = "1"; // 第一次访问，设置当前页为1；
			}
			// 转换
			int currentPage = Integer.parseInt(currPage);

			// 创建dao对象，设置当前页参数；
			PageBean pageBean = new PageBean();
			pageBean.setCurrentPage(currentPage);

			// 调用dao对象，获取结果
			HomeDAO dao = new HomeDAO();
			Map<String, Object> result = dao.getCommodity(pageBean);

			// 保存
			request.setAttribute("result", result);
			// 跳转
			uri = "/Home/product_display.jsp";
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 转发
		request.getRequestDispatcher(uri).forward(request, response);

	}

	// 产品搜索
	public void search_commodity(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			// 获取查询参数
			String search_name = request.getParameter("search_name");
			String cids = request.getParameter("cid");
			// 获取“当前页”参数（当第一次访问当前页为null）
			String currPage = request.getParameter("currentPage");
			// 判断
			if (currPage == null || "".equals(currPage.trim())) {
				currPage = "1"; // 第一次访问，设置当前页为1；
			}
			// 转换
			int currentPage = Integer.parseInt(currPage);
			int cid = Integer.parseInt(cids);

			// 创建dao对象，设置当前页参数
			PageBean pageBean = new PageBean();
			pageBean.setCurrentPage(currentPage);

			// 调用dao对象，获取结果
			HomeDAO dao = new HomeDAO();
			Map<String, Object> result = dao.searchCommodity(pageBean,cid,search_name);

			// 保存
			request.setAttribute("result", result);
			// 跳转
			uri = "/Home/product_display.jsp";
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 转发
		request.getRequestDispatcher(uri).forward(request, response);

	}

	// 红酒知识展示
	public void knowledge(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			// 获取“当前页”参数；（当第一次访问当前页为null）
			String currPage = request.getParameter("currentPage");
			// 判断
			if (currPage == null || "".equals(currPage.trim())) {
				currPage = "1"; // 第一次访问，设置当前页为1；
			}
			// 转换
			int currentPage = Integer.parseInt(currPage);

			// 创建dao对象，设置当前页参数；
			PageBean pageBean = new PageBean();
			pageBean.setCurrentPage(currentPage);

			// 调用dao对象，获取结果
			HomeDAO dao = new HomeDAO();
			Map<String, Object> result = dao.getKnowledge(pageBean);

			// 保存
			request.setAttribute("result", result);
			// 跳转
			uri = "/Home/knowledge.jsp";
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 转发
		request.getRequestDispatcher(uri).forward(request, response);

	}

	// 公司资讯
	public void news(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			// 获取“当前页”参数；（当第一次访问当前页为null）
			String currPage = request.getParameter("currentPage");
			// 判断
			if (currPage == null || "".equals(currPage.trim())) {
				currPage = "1"; // 第一次访问，设置当前页为1；
			}
			// 转换
			int currentPage = Integer.parseInt(currPage);

			// 创建dao对象，设置当前页参数；
			PageBean pageBean = new PageBean();
			pageBean.setCurrentPage(currentPage);

			// 调用dao对象，获取结果
			HomeDAO dao = new HomeDAO();
			Map<String, Object> result = dao.getNews(pageBean);

			// 保存
			request.setAttribute("result", result);

			// 跳转
			uri = "/Home/news.jsp";
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 转发
		request.getRequestDispatcher(uri).forward(request, response);
	}

	// 招贤纳士
	public void recruit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {

			// 调用dao对象，获取结果
			HomeDAO dao = new HomeDAO();
			ArrayList<Recruit> result = dao.getRecruit();

			// 保存
			request.setAttribute("result", result);

			// 跳转
			uri = "/Home/recruit.jsp";
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 转发
		request.getRequestDispatcher(uri).forward(request, response);
	}
	
	//商品详情
	public void commodity_detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {

			// 获取请求数据封装
			int commodityId = Integer.parseInt(request.getParameter("id"));
			
			// 调用dao对象的方法，得到结果
			HomeDAO dao = new HomeDAO();
			Map<String, Object> result = dao.commodityDetail(commodityId);

			// 保存
			request.setAttribute("result", result);
			// 跳转商品分类页
			uri = "/Home/goods_detail.jsp";
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 转发
		request.getRequestDispatcher(uri).forward(request, response);

	}
	
	//红酒知识详情
	public void knowledge_detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			// 获取请求数据封装
			int KnowledgeId = Integer.parseInt(request.getParameter("id"));
			
			// 调用dao对象方法，得到结果
			HomeDAO dao = new HomeDAO();
			Knowledge result = dao.knowledgeDetail(KnowledgeId);

			// 保存
			request.setAttribute("result", result);
			// 跳转
			uri = "/Home/knowledge_detail.jsp";
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 转发
		request.getRequestDispatcher(uri).forward(request, response);

	}
	
	//公司资讯详情
	public void information_detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			// 获取请求数据封装
			int Id = Integer.parseInt(request.getParameter("id"));
			
			// 调用dao对象方法，得到结果
			HomeDAO dao = new HomeDAO();
			Information result = dao.informationDetail(Id);

			// 保存
			request.setAttribute("result", result);
			// 跳转
			uri = "/Home/news_detail.jsp";
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 转发
		request.getRequestDispatcher(uri).forward(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
