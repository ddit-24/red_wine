package servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.RecruitDAO;
import entity.Recruit;
import util.PageBean;

public class RecruitServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// 跳转资源
	private String uri;

	/**
	 * Constructor of the object.
	 */
	public RecruitServlet() {
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
			// 更新
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

	// a.添加
	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			// 1. 获取请求数据封装
			String recruitTitle = request.getParameter("title");
			String recruitJob_describe = request.getParameter("job_describe");
			String recruitRequirement = request.getParameter("requirement");

			Recruit cc = new Recruit();
			cc.setTitle(recruitTitle);
			cc.setJob_describe(recruitJob_describe);
			cc.setRequirement(recruitRequirement);

			// 2. 调用dao执行添加
			RecruitDAO dao = new RecruitDAO();
			dao.add(cc);

			// 3. 跳转
			uri = "/servlet/RecruitServlet?method=list";
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 转发
		request.getRequestDispatcher(uri).forward(request, response);

	}

	// b.招贤纳士展示
	public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			//获取“当前页”参数；（当第一次访问当前页为null）
			String currPage = request.getParameter("currentPage");
			//判断 
			if (currPage == null || "".equals(currPage.trim())) {
				currPage = "1";       //第一次访问，设置当前页为1；
			}
			//转换
			int currentPage = Integer.parseInt(currPage);
			
			//创建dao对象，设置当前页参数；
			PageBean pageBean = new PageBean();
			pageBean.setCurrentPage(currentPage);
			
			// 调用dao对象的getAll方法，得到结果
			RecruitDAO dao = new RecruitDAO();
			Map<String, Object> result = dao.getAll(pageBean);

			// 保存
			request.setAttribute("result", result);
			// 跳转
			uri = "/sys/recruit/recruit.jsp";
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 转发
		request.getRequestDispatcher(uri).forward(request, response);

	}

	// c.删除
	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			// 1. 获取请求数据封装
			int RecruitId = Integer.parseInt(request.getParameter("id"));

			Recruit cc = new Recruit();
			cc.setId(RecruitId);

			// 2. 调用dao执行删除
			RecruitDAO dao = new RecruitDAO();
			dao.delete(cc);

			// 3. 跳转
			uri = "/servlet/RecruitServlet?method=list";
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 转发
		request.getRequestDispatcher(uri).forward(request, response);

	}

	// d.查找单个信息
	public void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {

			//获取“当前页”参数；（当第一次访问当前页为null）
			String currPage = request.getParameter("currentPage");
			//获取查找的职位名称
			String RecruitTitle = request.getParameter("title");
			//判断 
			if (currPage == null || "".equals(currPage.trim())) {
				currPage = "1";       //第一次访问，设置当前页为1；
			}
			//转换
			int currentPage = Integer.parseInt(currPage);
			
			//创建dao对象，设置当前页参数；
			PageBean pageBean = new PageBean();
			pageBean.setCurrentPage(currentPage);
			
			Recruit title = new Recruit();
			title.setTitle(RecruitTitle);
			
			//调用dao对象的search方法，得到结果
			RecruitDAO dao = new RecruitDAO();
			Map<String, Object> result = dao.search(pageBean, title);

			// 保存
			request.setAttribute("result", result);
			// 跳转
			uri = "/sys/recruit/recruit.jsp";
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 转发
		request.getRequestDispatcher(uri).forward(request, response);

	}

	// e.更新跳转展示
	public void Jumpup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {

			// 1. 获取请求数据封装
			int RecruitId = Integer.parseInt(request.getParameter("id"));

			Recruit cc = new Recruit();
			cc.setId(RecruitId);

			// 2. 调用dao执行添加
			RecruitDAO dao = new RecruitDAO();

			// 调用dao对象的getone方法，得到结果
			Recruit result = dao.getone(cc);

			// 保存
			request.setAttribute("listClass", result);
			// 跳转
			uri = "/sys/recruit/update.jsp";
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 转发
		request.getRequestDispatcher(uri).forward(request, response);

	}

	// f.更新招贤纳士
	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			// 1. 获取请求数据封装
			int recruitId = Integer.parseInt(request.getParameter("id"));
			String recruitTitle = request.getParameter("title");
			String recruitJob_describe = request.getParameter("job_describe");
			String recruitRequirement = request.getParameter("requirement");

			Recruit cc = new Recruit();
			cc.setId(recruitId);
			cc.setTitle(recruitTitle);
			cc.setJob_describe(recruitJob_describe);
			cc.setRequirement(recruitRequirement);

			// 2. 调用dao执行添加
			RecruitDAO dao = new RecruitDAO();
			dao.update(cc);

			// 3. 跳转
			uri = "/servlet/RecruitServlet?method=list";
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
