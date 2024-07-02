package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProfileDAO;
import entity.Profile;

public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 跳转资源
	private String uri;

	/**
	 * Constructor of the object.
	 */
	public ProfileServlet() {
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
		if ("list".equals(method)) {
			// 展示
			list(request, response);
		} else if ("add".equals(method)) {
			// 添加
			add(request, response);
		} else if ("delete".equals(method)) {
			// 删除
			delete(request, response);
		} else if ("Jump".equals(method)) {
			// 跳转更新页
			Jump(request, response);
		} else if ("update".equals(method)) {
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

	// 公司简介展示
	public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 调用dao对象的方法，得到结果
			ProfileDAO dao = new ProfileDAO();
			Profile result = dao.getAll();
			// 保存
			request.setAttribute("result", result);
			// 跳转
			uri = "/sys/profile/profile.jsp";
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 转发
		request.getRequestDispatcher(uri).forward(request, response);
	}

	// 添加
	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 1. 获取请求数据封装
			String Content = request.getParameter("content");
			Profile profile = new Profile();
			profile.setContent(Content);
			// 2. 调用dao执行添加
			ProfileDAO dao = new ProfileDAO();
			dao.add(profile);
			// 3. 跳转
			uri = "/servlet/ProfileServlet?method=list";
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 转发
		request.getRequestDispatcher(uri).forward(request, response);
	}

	// 删除
	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 1. 获取请求数据封装
			int Id = Integer.parseInt(request.getParameter("id"));
			Profile profile = new Profile();
			profile.setId(Id);
			// 2. 调用dao执行删除
			ProfileDAO dao = new ProfileDAO();
			dao.delete(profile);
			// 3. 跳转
			uri = "/servlet/ProfileServlet?method=list";
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 转发
		request.getRequestDispatcher(uri).forward(request, response);
	}

	// 更新跳转
	public void Jump(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 获取请求数据封装
			int Id = Integer.parseInt(request.getParameter("id"));
			Profile profile = new Profile();
			profile.setId(Id);
			// 调用dao对象的方法，得到结果
			ProfileDAO dao = new ProfileDAO();
			Profile result = dao.getone(profile);
			// 保存
			request.setAttribute("result", result);
			// 跳转
			uri = "/sys/profile/update.jsp";
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 转发
		request.getRequestDispatcher(uri).forward(request, response);
	}

	// 更新
	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 1. 获取请求数据封装
			int Id = Integer.parseInt(request.getParameter("id"));
			String Content = request.getParameter("content");
			Profile profile = new Profile();
			profile.setId(Id);
			profile.setContent(Content);
			// 2. 调用dao执行更新
			ProfileDAO dao = new ProfileDAO();
			dao.update(profile);
			// 3. 跳转
			uri = "/servlet/ProfileServlet?method=list";
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