package cn.library.user.web.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.library.admin.feedback.domain.Feedback;
import cn.library.admin.inform.domain.Inform;
import cn.library.paging.domain.PageBean;
import cn.library.user.service.UserPageService;
import cn.library.utils.BaseServletUtils;
import cn.library.utils.WebUtils;
import cn.library.admin.book.domain.Book;

public class UserPageServlet extends BaseServletUtils {

	/**
	 * 序列化id
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 依赖对象
	 */
	private UserPageService userPageService = new UserPageService();

	//流程
	//获取新书列表流程
	//1调用业务层的方法，业务层根据当前年份和前一年份，获取属于该年份的书
	//2由业务层方法获取到存有新书的Map，存到session，请求转发到newBook.jsp
	//查看通知流程
	//1.跟管理页面的流程一样
	//查看通知内容流程
	//1.跟管理页面的流程一样
	//添加反馈流程
	//1校验手机号码，失败则回显
	//2获取表单数据，封装到feedback对象，并补全id和时间
	//3把对象交给业务层的添加通知方法
	//4判断添加是否成功，成功则回显成功
	//管理页面登录流程
	//1.获取表单信息
	//2.调用业务层根据账号获取密码方法，匹配密码是否一致（要接收业务层异常）
	//3.获得异常则表示账号不存在，则回显账号不存在
	//4.获得密码，匹配不一致，则回显密码错误
	//5.都正确则保存账号到session（为以后做过滤器做准备），并重定向到管理界面
	//模糊查询图书
	//1跟管理页面的流程一样
	
	/**
	 * 获取新书列表
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String showNewBooks(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//增加分页功能
		//a.获取当前页面，即pc（如果pc不存在，默认为1）
		int pc = getPac(request);
		//b.指定ps，即指定每页记录数
		int ps = 15;
		
		//1调用业务层的方法，业务层根据当前年份和前一年份，获取属于该年份的书
		//2由业务层方法获取到存有新书的Map，存到session，请求转发到newBook.jsp
		PageBean<Book> newbooks = userPageService.showNewBooks(pc, ps);
		request.getSession().setAttribute("newbooks", newbooks);
		return "qqzf:/WEB-INF/jsps/newBook.jsp";
	}
	
	/**
	 * 显示所有通知信息
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String showInform(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//1调用业务层的查找全部通知方法，无异常则返回含通知对象的Map对象，键为bid（存到Map是因为删除时要找）
		Map<String,Inform> informs = userPageService.showInform();

		//2将Map对象存到session，请求转发到原jsp（存到session是因为删除的时候需要用到）
		request.getSession().setAttribute("informs", informs);
		return "qqzf:/WEB-INF/jsps/inform.jsp";
		
		//3jsp使用jstl遍历所有通知
	}
	
	/**
	 * 显示通知内容
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String showText(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//1将当前要点击的通知的id作为参数传到本servlet
		String iid = request.getParameter("iid");
		
		//2调用service的根据id查找inform对象的方法
		Inform inform = userPageService.findById(iid);
		
		//3请求转发到informText页面，并将查到的Inform对象存到Session给页面（因为request不能存对象）
		request.getSession().setAttribute("inform", inform);
		return "qqzf:/WEB-INF/jsps/informText.jsp";
	}
	
	/**
	 * 添加读者反馈
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String addFeedback(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//1校验手机号码，失败则回显（包括原填写信息）
		Feedback feedback = WebUtils.toBean(request.getParameterMap(), Feedback.class);
		if(!request.getParameter("phone").matches("^1(3|4|5|7|8)\\d{9}$")){
			HttpSession s = request.getSession();
			s.setAttribute("oriFB", feedback);
			s.setAttribute("addFeedbackMeg", "手机号码有误！");
			return "qqzf:/WEB-INF/jsps/feedback.jsp";
		}
		
		//2获取表单数据，封装到feedback对象，并补全id和时间
		feedback.setFid(WebUtils.generateID());
		feedback.setFtime(WebUtils.getTime());
		
		//3把对象交给业务层的添加通知方法
		userPageService.addFeedback(feedback);
		
		//4判断添加是否成功，成功则回显成功
		HttpSession ss = request.getSession();
		ss.setAttribute("addFeedbackMeg", "添加成功！");
		ss.setAttribute("oriFB", null); //清空表单
		return "/UserUI?jspPage=feedback";
	}
	
	/**
	 * 管理页面登录
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String login(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//1.获取表单信息
		String aname = request.getParameter("aname");
		String apassword = request.getParameter("apassword");
		//2.调用业务层根据账号获取密码方法，匹配密码是否一致（要接收业务层异常）
		String pass = null;
		try{
			pass = userPageService.findPassword(aname);
		}catch(Exception e){
			//3.获得异常则表示账号不存在，则回显账号不存在
			request.setAttribute("loginMeg", "账号有误！请重新输入！");
			return "qqzf:/WEB-INF/jsps/admin_login.jsp";
		}
		//4.获得密码，匹配不一致，则回显密码错误
		if(!pass.equals(apassword)){
			request.setAttribute("loginMeg", "密码有误！请重新输入！");
			request.setAttribute("oriname", aname);
			return "qqzf:/WEB-INF/jsps/admin_login.jsp";
		}
		//5.都正确则保存账号到session（用于过滤器），并重定向到管理界面
		request.getSession().setAttribute("admin", aname);
		return "/admin/AdminUI?jspPage=admin";
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
		int ps = 13;
		
		//1获得表单数据，数据仅书名的字符串
		String bookName = request.getParameter("bookName").trim();
		//校验
		if(bookName.equals("") || bookName == null){
			request.setAttribute("notEmpty", "请输入书名，谢谢！");
			return "qqzf:/WEB-INF/jsps/showBookBN.jsp";
		}
		////解决改为get方式后的编码问题 //最后发现，即使为get，表单也会编码
//		bookName = encoding(bookName);
		
		//2把书名交给业务层的模糊查找方法，无异常则获取含Book对象的Map对象，键为bid
		PageBean<Book> books = userPageService.showBook(bookName, pc, ps);
		books.setUrl(getUrl(request));
		
		//3将Map对象存到session，请求转发到原jsp（存到session是因为删除图书的时候需要用到）
		request.getSession().setAttribute("userbooks", books);
		request.setAttribute("bookName", bookName); //不清空原内容，回显回去
		return "qqzf:/WEB-INF/jsps/showBookBN.jsp";
		
		//4jsp使用jstl遍历出匹配的书
	}
	
	/**
	 * 给get方式获取的参数进行转换编码
	 * @param bookName
	 * @return
	 */
//	private String encoding(String bookName) {
//		try {
//			bookName = new String(bookName.getBytes("ISO8859-1"),"utf-8");
//		} catch (UnsupportedEncodingException e) {
//			throw new RuntimeException(e);
//		}
//		return bookName;
//	}

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
}
