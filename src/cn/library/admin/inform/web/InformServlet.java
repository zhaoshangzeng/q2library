package cn.library.admin.inform.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.library.admin.book.domain.Book;
import cn.library.admin.inform.domain.Inform;
import cn.library.admin.inform.service.InformService;
import cn.library.utils.BaseServletUtils;
import cn.library.utils.WebUtils;

/**
 * inform的负责页面数据接收的servlet
 * @author zzshang
 *
 */
public class InformServlet extends BaseServletUtils {

	/**
	 * 序列化id
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 依赖对象
	 */
	private InformService informService = new InformService();

	//流程
	//添加通知的流程
	//1获取表单数据，封装到Inform对象，并补全id和时间
	//2把Inform对象交给业务层的添加通知方法
	//3判断添加是否成功，成功则回显成功
	//4如果业务层方法返回false，则说明题目和日期是重复的，显示一天内不能发布同样标题的通知
	//查看所有通知的流程
	//1调用业务层的查找全部通知方法，无异常则返回含通知对象的Map对象，键为bid（存到Map是因为删除时要找）
	//2将Map对象存到session，请求转发到原jsp（存到session是因为删除的时候需要用到）
	//3jsp使用jstl遍历所有通知
	//查看详细通知内容的流程
	//1将当前要点击的通知的id作为参数传到本servlet
	//2调用service的根据id查找inform对象的方法
	//3请求转发到informText页面，并将查到的Inform对象存到Session给页面（因为request不能存对象）
	//删除通知的流程
	//1页面使用JavaScript技术获得当前点击删除的通知条目的信息，并用location跳到本servlet,加上本页面的名字
	//2将参数给到业务层的删除通知方法，无异常则获得返回的被删除的通知对象
	//3获取对应session中查找通知的Map对象，删除其中的被删除通知对象
	//4重定向到AdminUI+页面名字参数
	//修改通知的流程
	//1从session中Map获取Inform对象，显示到修改界面（jsp任务）
	//2将参数封装成Inform对象，给到业务层的修改通知方法
	//3回显修改成功
	
	/**
	 * 添加通知
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String addInform(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//1获取表单数据，封装到Inform对象，并补全id和当前时间
		Inform inform = WebUtils.toBean(request.getParameterMap(), Inform.class);
		inform.setIid(WebUtils.generateID());
		inform.setItime(WebUtils.getTime());
		
		//2把Inform对象交给业务层的添加通知方法
		boolean result = informService.addInform(inform);
		
		//3判断添加是否成功，成功则回显成功
		if(result){
			request.getSession().setAttribute("addInformMeg", "添加成功！");
		}else{
			//4如果业务层方法返回false，则说明题目和日期是重复的，显示一天内不能发布同样标题的通知
			request.getSession().setAttribute("oriInform", inform); //恢复原填写数据
			request.getSession().setAttribute("addInformMeg", "一天内不能发布同样标题的通知，请勿重复添加！");
		}
		return "/admin/AdminUI?jspPage=addInform";
	}
	
	/**
	 * 显示所有信息
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String showInform(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//1调用业务层的查找全部通知方法，无异常则返回含通知对象的Map对象，键为bid（存到Map是因为删除时要找）
		Map<String,Inform> informs = informService.showInform();
		
		//2将Map对象存到session，请求转发到原jsp（存到session是因为删除的时候需要用到）
		request.getSession().setAttribute("informs", informs);
		request.getSession().setAttribute("addInformMeg", null);//帮忙清掉添加页面的信息
		request.getSession().setAttribute("upInformMeg", null);
		return "qqzf:/WEB-INF/jsps/admin/showInform.jsp";
		
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
		Inform inform = informService.findById(iid);
		
		//3请求转发到informText页面，并将查到的Inform对象存到Session给页面（因为request不能存对象）
		request.getSession().setAttribute("inform", inform);
		return "qqzf:/WEB-INF/jsps/admin/informText.jsp";
	}
	
	/**
	 * 删除通知
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public String deleteInform(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//1页面使用JavaScript技术获得当前点击删除的图书条目的信息，并用location跳到本servlet,加上本页面的名字
		String delid = request.getParameter("delid");
		
		//2将参数给到业务层的删除通知方法，无异常则获得返回的被删除的通知对象
		informService.deleteInform(delid);
		
		//3获取对应session中查找通知的Map对象，删除其中的被删除通知对象
		Map<String,Book> informs = (Map<String, Book>) request.getSession().getAttribute("informs");
		informs.remove(delid);
		
		//4重定向到AdminUI+页面名字参数
		return "/admin/AdminUI?jspPage=showInform";
	}
	
	/**
	 * 修改通知
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String updateInform(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//1从session中Map获取Inform对象，显示到修改界面（jsp任务）
		//2将参数封装成Inform对象，给到业务层的修改通知方法
		Inform inform = WebUtils.toBean(request.getParameterMap(), Inform.class);
		informService.updateBook(inform);
		
		//3回显修改成功
		request.getSession().setAttribute("upInformMeg", "修改成功！");
		return "/admin/AdminUI?jspPage=updateInform";
	}
}
