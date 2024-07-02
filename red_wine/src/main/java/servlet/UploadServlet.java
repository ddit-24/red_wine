package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.CommodityDAO;
import entity.Commodity;

public class UploadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// 跳转资源
	private String uri;

	/**
	 * Constructor of the object.
	 */
	public UploadServlet() {
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

	// a.添加商品
	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/********* 文件上传组件： 处理文件上传 ************/

		try {
			// 1. 文件上传工厂
			FileItemFactory factory = new DiskFileItemFactory();
			// 2. 创建文件上传核心工具类
			ServletFileUpload upload = new ServletFileUpload(factory);

			// 设置大小限制参数
			upload.setFileSizeMax(10 * 1024 * 1024); // 单个文件大小限制
			upload.setSizeMax(50 * 1024 * 1024); // 总文件大小限制
			upload.setHeaderEncoding("utf-8"); // 对中文文件编码处理
			
			// 3. 判断： 当前表单是否为文件上传表单
			if (upload.isMultipartContent(request)) {
				// 4. 把请求数据转换为一个个FileItem对象，再用集合封装
				List<FileItem> list = upload.parseRequest(request);
				Commodity result = new Commodity();
				// 遍历： 得到每一个上传的数据
				for (FileItem item : list) {
					// 判断：普通文本数据
					if (item.isFormField()) {
						// 获取名称
						String name = item.getFieldName();
						// 获取值
						String value = item.getString("utf-8");   //特别注意编码问题

						// 判断获取文本数据
						if ("class_id".equals(name)) {
							result.setClass_id(Integer.parseInt(value));
						} else if ("title".equals(name)) {
							result.setTitle(value);
						} else if ("price".equals(name)) {
							result.setPrice(Float.parseFloat(value));
						} else if ("specifications".equals(name)) {
							result.setSpecifications(value);
						} else if ("place".equals(name)) {
							result.setPlace(value);
						} else if ("alcohol".equals(name)) {
							result.setAlcohol(value);
						} else if ("content".equals(name)) {
							result.setContent(value);
						}

					}
					// 文件表单项
					else {
						/******** 文件上传 ***********/
						// a. 获取文件名称
						String name = item.getName();
						// ----处理上传文件名重名问题----
						// a1. 先得到唯一标记
						String id = UUID.randomUUID().toString();
						// a2. 拼接文件名
						name = id + name;
						result.setPhoto(name);
						// b. 得到上传目录
						String basePath = getServletContext().getRealPath("/upload");  //tomcat服务器路径
						//String basePath = "D:/下载/红酒/red_wine/src/main/Webapp/upload";   
						// c. 创建要上传的文件对象
						File file = new File(basePath, name); // 文件服务器路径
						// d. 文件夹不存在就自动创建
						if (!new File(basePath).isDirectory())
							new File(basePath).mkdirs();
						// e. 上传
						item.write(file);
						item.delete(); // 删除组件运行时产生的临时文件
					}

				}

				// 调用dao执行添加
				CommodityDAO dao = new CommodityDAO();
				dao.add(result);

			} else {
				System.out.println("当前表单不是文件上传表单，处理失败！");
			}
			// 3. 跳转
			uri = "/servlet/CommodityServlet?method=list";
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 转发
		request.getRequestDispatcher(uri).forward(request, response);

	}

	// f.更新商品
	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			// 1. 文件上传工厂
			FileItemFactory factory = new DiskFileItemFactory();
			// 2. 创建文件上传核心工具类
			ServletFileUpload upload = new ServletFileUpload(factory);

			// 设置大小限制参数
			upload.setFileSizeMax(10 * 1024 * 1024); // 单个文件大小限制
			upload.setSizeMax(50 * 1024 * 1024); // 总文件大小限制
			upload.setHeaderEncoding("UTF-8"); // 对中文文件编码处理
			// 3. 判断： 当前表单是否为文件上传表单
			if (upload.isMultipartContent(request)) {
				// 4. 把请求数据转换为一个个FileItem对象，再用集合封装
				List<FileItem> list = upload.parseRequest(request);
				Commodity result = new Commodity();
				// 遍历： 得到每一个上传的数据
				for (FileItem item : list) {
					// 判断：普通文本数据
					if (item.isFormField()) {
						// 获取名称
						String name = item.getFieldName();
						// 获取值
						String value = item.getString("utf-8");   //特别注意编码问题

						// 判断获取文本数据
						if ("class_id".equals(name)) {
							result.setClass_id(Integer.parseInt(value));
						} else if ("id".equals(name)) {
							result.setId(Integer.parseInt(value));
						} else if ("title".equals(name)) {
							result.setTitle(value);
						} else if ("price".equals(name)) {
							result.setPrice(Float.parseFloat(value));
						} else if ("specifications".equals(name)) {
							result.setSpecifications(value);
						} else if ("place".equals(name)) {
							result.setPlace(value);
						} else if ("alcohol".equals(name)) {
							result.setAlcohol(value);
						} else if ("content".equals(name)) {
							result.setContent(value);
						}

					}
					// 文件表单项
					else {
						/******** 文件上传 ***********/
						// a. 获取文件名称
						String name = item.getName();
						// a2. 拼接文件名
						if (name == null || "".equals(name.trim())) {
							result.setPhoto(name);
						}else{
							// ----处理上传文件名重名问题----
							// a1. 先得到唯一标记
							String id = UUID.randomUUID().toString();
							name = id + name;
							result.setPhoto(name);
							// b. 得到上传目录
							String basePath = getServletContext().getRealPath("/upload");  //tomcat服务器路径
//							String basePath = "D:/下载/红酒/red_wine/src/main/Webapp/upload";   
							// c. 创建要上传的文件对象
							File file = new File(basePath, name); // 文件服务器路径
							// d. 文件夹不存在就自动创建
							if (!new File(basePath).isDirectory())
								new File(basePath).mkdirs();
							// e. 上传
							item.write(file);
							item.delete(); // 删除组件运行时产生的临时文件
						}						
					}
				}

				// 调用dao执行添加
				CommodityDAO dao = new CommodityDAO();
				dao.update(result);

			} else {
				System.out.println("当前表单不是文件上传表单，处理失败！");
			}
			// 3. 跳转
			uri = "/servlet/CommodityServlet?method=list";
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