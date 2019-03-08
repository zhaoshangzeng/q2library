package cn.library.admin.book.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.library.admin.book.domain.Book;
import cn.library.admin.inform.domain.Inform;
import cn.library.paging.domain.PageBean;

/**
 * 所有管理界面jsp，都需要经过该servlet
 * @author zzshang
 *
 */
public class AdminUI extends HttpServlet {

	/**
	 * 序列化id
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 方法说明：处理book管理界面JSP页面的相互跳转
	 * 方法目标：处理所有管理界面JSP页面的相互跳转
	 * 方法使用：jsp页面的a链接先跳转到本serlet，并带上参数jspPage，参数值是最终指向的jsp名字。
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @throws ServletException
	 * @throws IOException
	 * @see javax.servlet.http.HttpServlet#doGet
	 */
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//获取参数
		String jspPage = request.getParameter("jspPage");
		//点击修改书，修改界面需要书内容显示
		if("updateBook".equals(jspPage)){
			String upid = request.getParameter("upid");
			PageBean<Book> books = (PageBean<Book>) request.getSession().getAttribute("books");
			Book book = books.getBeanMap().get(upid);
			request.getSession().setAttribute("upBook", book);
		}
		if("updateBookFromAll".equals(jspPage)){
			String upid = request.getParameter("upid");
			PageBean<Book> allBook = (PageBean<Book>) request.getSession().getAttribute("allBook");
			Book book = allBook.getBeanMap().get(upid);
			request.getSession().setAttribute("upBook", book);
		}
		//点击修改通知，修改界面需要通知内容显示
		if("updateInform".equals(jspPage)){
			String upid = request.getParameter("upid");
			Map<String,Inform> informs = (Map<String, Inform>) request.getSession().getAttribute("informs");
			Inform one = informs.get(upid);
			request.getSession().setAttribute("upInform", one);
		}
		if("showBook".equals(jspPage)){
			request.setAttribute("notEmpty", "请在搜索框输入书名！");//用于jsp页面做判断，去掉不该有的东西
		}
		//根据参数，进行相应的请求转发			
		request.getRequestDispatcher("/WEB-INF/jsps/admin/" + jspPage + ".jsp").forward(request, response);
		return;
	}

}
