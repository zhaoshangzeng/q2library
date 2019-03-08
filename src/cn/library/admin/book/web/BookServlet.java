package cn.library.admin.book.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.library.admin.book.domain.Book;
import cn.library.admin.book.service.BookService;
import cn.library.paging.domain.PageBean;
import cn.library.utils.BaseServletUtils;
import cn.library.utils.WebUtils;

/**
 * Book的负责页面数据接收的servlet
 * @author zzshang
 *
 */
public class BookServlet extends BaseServletUtils {

	/**
	 * 序列化id
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 依赖对象
	 */
	private BookService bookService = new BookService();
	
	//流程：
	//添加图书的流程
	//1获取表单数据，封装到Book对象，并补全id
	//2把Book对象交给业务层的添加图书方法
	//3返回true则重定向到AdminUI+参数，将成功信息存到session，显示添加成功
	//4如果业务层方法返回false，则说明书名+作者+出版日期是重复的，显示该书已经存在（根据书名+作者+出版日期）
	//补充：判断该书存在，可以抛自定义异常（本项目使用boolean值判断即可）
	//显示全部图书流程
	//1调用业务层的查找全部图书方法，无异常则返回含Book对象的Map对象，键为bid（存到Map是因为删除时要找）
	//2将Map对象存到session，请求转发到原jsp（存到session是因为删除图书的时候需要用到）
	//3jsp使用jstl遍历所有书
	//查找图书的流程
	//1获得表单数据，数据仅书名的字符串
	//2把书名交给业务层的模糊查找方法，无异常则获取含Book对象的Map对象，键为bid
	//3将Map对象存到session，请求转发到原jsp（存到session是因为删除图书的时候需要用到）
	//4jsp使用jstl遍历出匹配的书
	//删除图书流程（要confirm进行确认再操作）
	//1页面使用JavaScript技术获得当前点击删除的图书条目的信息，并用location跳到本servlet,加上本页面的名字
	//2将参数给到业务层的删除图书方法，无异常则获得返回的被删除的图书对象
	//3判断是哪个页面的删除，获取对应session中查找图书的Map对象，删除其中的被删除图书对象
	//4重定向到AdminUI+页面名字参数
	//修改图书
	//1要区分是从哪个页面点进去的修改，以决定从session中哪个Map获取Book对象，显示到修改界面（jsp任务）
	//2将参数封装成Book对象，给到业务层的修改图书方法
	//3回显修改成功
	//分页流程
	//a.获取当前页面，即pc（如果pc不存在，默认为1）
	//b.指定ps，即指定每页记录数
	//c.调用service层方法，得到pageBean（融合原查询方法）
	//d.将pageBean保存到request域，请求转发到需要分页的jsp（融合原查询方法）
	
	//注意：同一业务的多个方法要合并，具体方法交给持久层
	
	/**
	 * 添加图书
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String addBook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//1获取表单数据，封装到Book对象，并补全id
		Book book = WebUtils.toBean(request.getParameterMap(), Book.class);
		book.setBid(WebUtils.generateID());
		
		//2把Book对象交给业务层的添加图书方法
		Boolean result = bookService.addBook(book);
		
		//3返回true则重定向到AdminUI+参数，将成功信息存到session，显示添加成功
		if(result){
			request.getSession().setAttribute("addBookMeg", "添加成功！");
		}else{
			//4如果业务层方法返回false，则说明书名+作者+出版日期是重复的，显示该书已经存在（根据书名+作者+出版日期）
			request.getSession().setAttribute("oriBook", book); //恢复原填写数据
			request.getSession().setAttribute("addBookMeg", "数据库已经有该图书（根据书名、作者和出版日期判断），请勿重复添加！");
		}
		return "/admin/AdminUI?jspPage=addBook";
	}
	
	/**
	 * 查找全部图书
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String showAllBook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//增加分页功能
		//a.获取当前页面，即pc（如果pc不存在，默认为1）
		int pc = getPac(request);
		//b.指定ps，即指定每页记录数
		int ps = 18;
		
		//1调用业务层的查找全部图书方法，无异常则返回含Book对象的Map对象，键为bid（存到Map是因为删除时要找）
		PageBean<Book> allBook = bookService.showAllBook(pc,ps);
		
		//2将Map对象存到session，请求转发到原jsp
		request.getSession().setAttribute("allBook", allBook);
		request.getSession().setAttribute("upBookMeg", null);//帮忙清理修改界面的信息
		return "qqzf:/WEB-INF/jsps/admin/showAllBook.jsp";
		
		//3jsp使用jstl遍历所有书
	}
	
	/**
	 * 查找匹配的书
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String showBook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//增加分页功能
		//a.获取当前页面，即pc（如果pc不存在，默认为1）
		int pc = getPac(request);
		//b.指定ps，即指定每页记录数
		int ps = 15;
		
		//1获得表单数据，数据仅书名的字符串
		String bookName = request.getParameter("bookName").trim();
		//校验
		if(bookName.equals("") || bookName == null){
			request.setAttribute("notEmpty", "请输入书名，谢谢！");
			return "qqzf:/WEB-INF/jsps/admin/showBook.jsp";
		}
		//解决改为get方式后的编码问题 //最后发现，即使为get，表单也会编码
//		bookName = encoding(bookName);
		
		//2把书名交给业务层的模糊查找方法，无异常则获取含Book对象的Map对象，键为bid
		PageBean<Book> books = bookService.showBook(bookName, pc, ps);
		books.setUrl(getUrl(request));
		
		//3将Map对象存到session，请求转发到原jsp（存到session是因为删除图书的时候需要用到）
		request.getSession().setAttribute("books", books);
		request.setAttribute("bookName", bookName); //不清空原内容，回显回去
		request.getSession().setAttribute("upBookMeg", null);//帮忙清理修改界面的信息
		return "qqzf:/WEB-INF/jsps/admin/showBook.jsp";
		
		//4jsp使用jstl遍历出匹配的书
	}
	
	/**
	 * 删除图书
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public String deleteBook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//1页面使用JavaScript技术获得当前点击删除的图书条目的bid，并用location跳到本servlet,加上本页面的名字
		String jspName = request.getParameter("jspName");
		String delid = request.getParameter("delid");
		
		//2将参数给到业务层的删除图书方法，无异常则获得返回的被删除的图书对象
		bookService.deleteBook(delid);
		
		//3判断是哪个页面的删除，获取对应session中查找图书的Map对象，删除其中的被删除图书对象
		//4重定向到AdminUI+页面名字参数
		if("all".equals(jspName)){
			PageBean<Book> allBook = (PageBean<Book>) request.getSession().getAttribute("allBook");
			allBook.getBeanMap().remove(delid);
			return "/admin/AdminUI?jspPage=showAllBook";
		}else if("search".equals(jspName)){
			PageBean<Book> books = (PageBean<Book>) request.getSession().getAttribute("books");
			books.getBeanMap().remove(delid);
			return "/admin/AdminUI?jspPage=showBook";
		}else{
			return "/admin/AdminUI?jspPage=showBook";
		}
	}
	
	public String updateBook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//1要区分是从哪个页面点进去的修改，以决定从session中哪个Map获取Book对象，显示到修改界面（jsp任务）
		//2将参数封装成Book对象，给到业务层的修改图书方法
		Book book = WebUtils.toBean(request.getParameterMap(), Book.class);
		bookService.updateBook(book);
		
		//3回显修改成功
		request.getSession().setAttribute("upBookMeg", "修改成功！");
		if("noall".equals(request.getParameter("page"))){
			return "/admin/AdminUI?jspPage=updateBook";
		}else{
			return "/admin/AdminUI?jspPage=updateBookFromAll";
		}
	}
	
	/**
	 * 获取页码pc
	 * @param request
	 * @return
	 */
	private int getPac(HttpServletRequest request){
		String value = request.getParameter("pc");
		if(value == null ||	value.trim().isEmpty()){
			return 1;
		}
		return Integer.parseInt(value);
	}
	
	/**
	 * 保留原条件查询的url后缀
	 */
	private String getUrl(HttpServletRequest request){
		String contextPath = request.getContextPath(); //项目地址
		String servletPath = request.getServletPath(); //servlet地址
		String queryString = request.getQueryString(); //参数
		
		//去掉pc，让页面自己加
		if(queryString.contains("&pc=")){
			int index = queryString.lastIndexOf("&pc=");
			queryString= queryString.substring(0,index);
		}
		return contextPath + servletPath + "?" +queryString;
	}
	
	/**
	 * 给get方式获取的参数进行转换编码
	 * @param bookName
	 * @return
	 */
//	private String encoding(String bookName) {
//		try {
//			bookName = new String(bookName.getBytes("ISO-8859-1"),"utf-8");
//		} catch (UnsupportedEncodingException e) {
//			throw new RuntimeException();
//		}
//		return bookName;
//	}
}
