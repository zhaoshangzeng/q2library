package cn.library.admin.feedback.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.library.admin.feedback.domain.Feedback;
import cn.library.admin.feedback.service.FeedbackService;
import cn.library.utils.BaseServletUtils;

/**
 * feedback的负责页面数据接收的servlet
 * @author zzshang
 *
 */
public class FeedbackServlet extends BaseServletUtils {

	/**
	 * 序列化id
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 依赖对象
	 */
	private FeedbackService fbService = new FeedbackService();
	
	//流程：
	//查看所有反馈
	//1调用业务层的查找全部反馈方法，无异常则返回含反馈对象的Map对象，键为bid（存到Map是因为删除时要找）
	//2将Map对象存到session，请求转发到原jsp（存到session是因为删除的时候需要用到）
	//3jsp使用jstl遍历所有反馈
	//查看反馈内容
	//1将当前要点击的反馈的id作为参数传到本servlet
	//2调用service的根据id查找Feedback对象的方法
	//3请求转发到FBText页面，并将查到的Feedback对象存到Session给页面（因为request不能存对象）
	//删除反馈
	//1页面使用JavaScript技术获得当前点击删除的反馈条目的信息，并用location跳到本servlet,加上本页面的名字
	//2将参数给到业务层的删除反馈方法，无异常则获得返回的被删除的反馈对象
	//3获取对应session中查找反馈的Map对象，删除其中的被删除反馈对象
	//4重定向到AdminUI+页面名字参数
	
	/**
	 * 查看所有反馈
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String showFeedback(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//1调用业务层的查找全部反馈方法，无异常则返回含反馈对象的Map对象，键为bid（存到Map是因为删除时要找）
		Map<String,Feedback> feedbacks = fbService.showFeedback();
		
		//2将Map对象存到session，请求转发到原jsp（存到session是因为删除的时候需要用到）
		request.getSession().setAttribute("feedbacks", feedbacks);
		return "qqzf:/WEB-INF/jsps/admin/showFB.jsp";
		
		//3jsp使用jstl遍历所有反馈
	}
	
	/**
	 * 查看反馈内容
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String showText(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//1将当前要点击的反馈的id作为参数传到本servlet
		String fid = request.getParameter("fid");
		
		//2调用service的根据id查找Feedback对象的方法
		Feedback feedback = fbService.findById(fid);
		
		//3请求转发到FBText页面，并将查到的Feedback对象存到Session给页面（因为request不能存对象）
		request.getSession().setAttribute("feedback", feedback);
		return "qqzf:/WEB-INF/jsps/admin/FBText.jsp";
	}
	
	@SuppressWarnings("unchecked")
	public String deleteFeedback(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//1页面使用JavaScript技术获得当前点击删除的反馈条目的信息，并用location跳到本servlet,加上本页面的名字
		String delid = request.getParameter("delid");
		
		//2将参数给到业务层的删除反馈方法，无异常则获得返回的被删除的反馈对象
		fbService.deleteFeedback(delid);
		
		//3获取对应session中查找反馈的Map对象，删除其中的被删除反馈对象
		Map<String,Feedback> feedbacks = (Map<String, Feedback>) request.getSession().getAttribute("feedbacks");
		feedbacks.remove(delid);
		
		//4重定向到AdminUI+页面名字参数
		return "/admin/AdminUI?jspPage=showFB";
	}
}
