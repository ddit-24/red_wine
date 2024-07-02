package servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.InformationDAO;
import entity.Information;
import util.PageBean;

public class InformationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// 跳转资源
	private String uri;

	/**
	 * Constructor of the object.
	 */
	public InformationServlet() {
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

	// a.添加公司资讯
	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			// 1. 获取请求数据封装
			String informationTitle = request.getParameter("title");  //获取标题的值并赋值给informationTitle变量
			String informationAuthor = request.getParameter("author"); //获取作者的值并赋值给informationAuthor变量
			String informationContent = request.getParameter("content"); //获取内容的值并赋值给informationContent变量
			String informationDate = request.getParameter("date"); //获取发布时间的值并赋值给informationDate变量

			Information information = new Information(); //创建一个Information对象
			information.setTitle(informationTitle);	//将标题的值加入Information
			information.setAuthor(informationAuthor); //将作者的值加入Information
			information.setContent(informationContent);	//将内容的值加入Information
			information.setDate(informationDate); //将发布时间的值加入Information

			// 2. 调用dao执行添加
			InformationDAO dao = new InformationDAO();
			dao.add(information);
			// 3. 跳转路径
			uri = "/servlet/InformationServlet?method=list";
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 转发
		request.getRequestDispatcher(uri).forward(request, response);
	}

	// b.公司资讯展示
	public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			// 获取“当前页”参数；（当第一次访问当前页为null）
			String currPage = request.getParameter("currentPage");
			// 判断当前页是否为空
			if (currPage == null || "".equals(currPage.trim())) {
				currPage = "1"; // 第一次访问，设置当前页为1；
			}
			// 转换页参数为int类型
			int currentPage = Integer.parseInt(currPage);
			// 创建dao对象，设置当前页参数；
			PageBean pageBean = new PageBean();
			pageBean.setCurrentPage(currentPage);

			// 调用dao对象的getAll方法，得到结果
			InformationDAO dao = new InformationDAO();
			Map<String, Object> result = dao.getAll(pageBean);

			// 保存
			request.setAttribute("result", result);

			// 跳转路径
			uri = "/sys/information/information.jsp";
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 转发
		request.getRequestDispatcher(uri).forward(request, response);
	}

	// c.删除公司资讯
	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			// 获取ID的值，转换为int类型并赋值给informationId变量
			int informationId = Integer.parseInt(request.getParameter("id"));

			Information information = new Information(); //创建一个Information对象
			information.setId(informationId);  //将ID的值加入information

			// 调用dao执行删除
			InformationDAO dao = new InformationDAO(); 
			dao.delete(information);

			// 跳转路径
			uri = "/servlet/InformationServlet?method=list";
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 转发
		request.getRequestDispatcher(uri).forward(request, response);
	}

	// d.查找单个公司资讯
	public void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {

			// 获取“当前页”参数；（当第一次访问当前页为null）
			String currPage = request.getParameter("currentPage");
			// 获取查找的标题
			String InformationTitle = request.getParameter("title");
			// 判断
			if (currPage == null || "".equals(currPage.trim())) {
				currPage = "1"; // 第一次访问，设置当前页为1；
			}
			// 转换页参数为int类型
			int currentPage = Integer.parseInt(currPage);

			// 创建PageBean对象，设置当前页参数；
			PageBean pageBean = new PageBean();
			pageBean.setCurrentPage(currentPage);
			// 创建Information对象，将标题的值加入title
			Information title = new Information();
			title.setTitle(InformationTitle);

			// 调用dao对象的search方法，得到结果
			InformationDAO dao = new InformationDAO();
			Map<String, Object> result = dao.search(pageBean, title);

			// 保存
			request.setAttribute("result", result);
			// 跳转路径
			uri = "/sys/information/information.jsp";
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 转发
		request.getRequestDispatcher(uri).forward(request, response);

	}

	// e.公司资讯更新跳转展示
	public void Jumpup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			// 获取ID的值，转换为int类型并赋值给informationId变量
			int informationId = Integer.parseInt(request.getParameter("id"));
			// 创建一个Information对象，并将ID的值加入information
			Information information = new Information();
			information.setId(informationId);

			// 调用dao执行getone方法，得到结果
			InformationDAO dao = new InformationDAO();
			Information result = dao.getone(information);

			// 保存
			request.setAttribute("listClass", result);
			// 跳转路径
			uri = "/sys/information/update.jsp";
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 转发
		request.getRequestDispatcher(uri).forward(request, response);

	}

	// f.更新公司资讯
	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			int informationId = Integer.parseInt(request.getParameter("id"));  //获取ID的值，转为int类型并赋值给informationId变量
			String informationTitle = request.getParameter("title");	//获取标题的值并赋值给informationTitle变量
			String informationAuthor = request.getParameter("author");	//获取作者的值并赋值给informationAuthor变量
			String informationContent = request.getParameter("content"); //获取内容的值并赋值给informationContent变量
			String informationDate = request.getParameter("date"); //获取发布时间的值并赋值给informationDate变量

			//创建Information对象，并将相关数据加入information
			Information information = new Information();
			information.setId(informationId);
			information.setTitle(informationTitle);
			information.setAuthor(informationAuthor);
			information.setContent(informationContent);
			information.setDate(informationDate);

			// 调用dao执行更新
			InformationDAO dao = new InformationDAO();
			dao.update(information);

			// 跳转路径
			uri = "/servlet/InformationServlet?method=list";
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
