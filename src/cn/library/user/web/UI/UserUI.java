package cn.library.user.web.UI;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserUI extends HttpServlet {

	/**
	 * 序列化id
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 方法说明：处理所有用户界面JSP页面的相互跳转
	 * 方法目标：处理所有用户界面JSP页面的相互跳转
	 * 方法使用：jsp页面的a链接先跳转到本serlet，并带上参数jspPage，参数值是最终指向的jsp名字。
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @throws ServletException
	 * @throws IOException
	 * @see javax.servlet.http.HttpServlet#doGet
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//根据参数，进行相应的请求转发
		String jspPage = request.getParameter("jspPage");
		request.getRequestDispatcher("/WEB-INF/jsps/" + jspPage + ".jsp").forward(request, response);
		return;
	}
}
