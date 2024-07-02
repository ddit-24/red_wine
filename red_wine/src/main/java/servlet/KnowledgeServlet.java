package servlet;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.KnowledgeDAO;
import entity.Knowledge;
import util.PageBean;

public class KnowledgeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 跳转资源
	private String uri;

	/**
	 * Constructor of the object.
	 */
	public KnowledgeServlet() {
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
		if ("add".equals(method)) {
			// 添加
			add(request, response);
		}

		else if ("list".equals(method)) {
			// 列表展示
			list(request, response);
		}

		else if ("search".equals(method)) {
			// 搜索
			search(request, response);
		}

		else if ("delete".equals(method)) {
			// 删除
			delete(request, response);
		}

		else if ("Jumpup".equals(method)) {
			// 跳转更新页
			Jumpup(request, response);
		}

		else if ("update".equals(method)) {
			// 更新
			update(request, response);
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

	// a.添加红酒知识
	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			// 1. 获取请求数据封装
			String knowledgeTitle = request.getParameter("title");
			String knowledgeAuthor = request.getParameter("author");
			String knowledgeContent = request.getParameter("content");
			String knowledgeDate = request.getParameter("date");

			Knowledge cc = new Knowledge();
			cc.setTitle(knowledgeTitle);
			cc.setAuthor(knowledgeAuthor);
			cc.setContent(knowledgeContent);
			cc.setDate(knowledgeDate);

			// 2. 调用dao执行添加
			KnowledgeDAO dao = new KnowledgeDAO();
			dao.add(cc);

			// 3. 跳转
			uri = "/servlet/KnowledgeServlet?method=list";
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 转发
		request.getRequestDispatcher(uri).forward(request, response);

	}

	// b.红酒知识展示
	public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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

			// 调用dao对象的getAll方法，得到结果
			KnowledgeDAO dao = new KnowledgeDAO();
			Map<String, Object> result = dao.getAll(pageBean);

			// 保存
			request.setAttribute("result", result);
			// 跳转
			uri = "/sys/knowledge/knowledge.jsp";
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 转发
		request.getRequestDispatcher(uri).forward(request, response);

	}

	// c.删除红酒知识
	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			// 1. 获取请求数据封装
			int knowledgeId = Integer.parseInt(request.getParameter("id"));

			Knowledge cc = new Knowledge();
			cc.setId(knowledgeId);

			// 2. 调用dao执行删除
			KnowledgeDAO dao = new KnowledgeDAO();
			dao.delete(cc);

			// 3. 跳转
			uri = "/servlet/KnowledgeServlet?method=list";
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 转发
		request.getRequestDispatcher(uri).forward(request, response);

	}

	// d.查找单个紅酒知识
	public void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {

			// 获取“当前页”参数；（当第一次访问当前页为null）
			String currPage = request.getParameter("currentPage");
			// 获取查找的标题
			String KnowledgeTitle = request.getParameter("title");
			// 判断
			if (currPage == null || "".equals(currPage.trim())) {
				currPage = "1"; // 第一次访问，设置当前页为1；
			}
			// 转换
			int currentPage = Integer.parseInt(currPage);

			// 创建dao对象，设置当前页参数；
			PageBean pageBean = new PageBean();
			pageBean.setCurrentPage(currentPage);

			Knowledge title = new Knowledge();
			title.setTitle(KnowledgeTitle);

			// 调用dao对象的search方法，得到结果
			KnowledgeDAO dao = new KnowledgeDAO();
			Map<String, Object> result = dao.search(pageBean, title);

			// 保存
			request.setAttribute("result", result);
			// 跳转
			uri = "/sys/knowledge/knowledge.jsp";
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 转发
		request.getRequestDispatcher(uri).forward(request, response);

	}

	// e.红酒知识更新跳转展示
	public void Jumpup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {

			// 1. 获取请求数据封装
			int KnowledgeId = Integer.parseInt(request.getParameter("id"));

			Knowledge cc = new Knowledge();
			cc.setId(KnowledgeId);

			// 2. 调用dao执行添加
			KnowledgeDAO dao = new KnowledgeDAO();

			// 调用dao对象的getone方法，得到结果
			Knowledge result = dao.getone(cc);

			// 保存
			request.setAttribute("listClass", result);
			// 跳转
			uri = "/sys/knowledge/update.jsp";
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 转发
		request.getRequestDispatcher(uri).forward(request, response);

	}

	// f.更新红酒知识
	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			// 1. 获取请求数据封装
			int knowledgeId = Integer.parseInt(request.getParameter("id"));
			String knowledgeTitle = request.getParameter("title");
			String knowledgeAuthor = request.getParameter("author");
			String knowledgeContent = request.getParameter("content");
			String knowledgeDate = request.getParameter("date");

			Knowledge cc = new Knowledge();
			cc.setId(knowledgeId);
			cc.setTitle(knowledgeTitle);
			cc.setAuthor(knowledgeAuthor);
			cc.setContent(knowledgeContent);
			cc.setDate(knowledgeDate);

			// 2. 调用dao执行添加
			KnowledgeDAO dao = new KnowledgeDAO();
			dao.update(cc);

			// 3. 跳转
			uri = "/servlet/KnowledgeServlet?method=list";
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