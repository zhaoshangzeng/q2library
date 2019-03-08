package cn.library.utils;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * servlet的基本处理类
 * 作用：合并所有web.Controller层的servlet类，处理重复部分内容，
 * 		同时，请求转发或重定向语句变成返回值，基本类来处理请求转发或重定向
 * 原理：子servlet变成基本类的方法，请求参数值与方法同名，根据请求参数+反射技术找到相应的方法
 * 注意：抽象类，需要继承，同时不能重写service方法，子类自定义同参方法，方法有返回值，类型String，
 * 		而且要求jsp页面传参
 * @author zzshang
 *
 */
public abstract class BaseServletUtils extends HttpServlet{

	/**
	 * 序列化id
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 合并doGet和doPost方法
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//处理编码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//1.获取请求参数method，判断用户页面想要请求哪个方法
		String methodName = request.getParameter("method");
		if(methodName == null || methodName.isEmpty()){
			throw new RuntimeException("您未传递method参数，无法准确处理您的请求!");
		}
		
		//2.使用反射技术，让反射去调用相应的方法（省去if...else...判断）
		Method method = null;
		String result = null;
		try {
			method = this.getClass().getMethod(methodName, HttpServletRequest.class,
					HttpServletResponse.class);  //获取相应方法名的Method对象
		} catch (Exception e) {
			throw new RuntimeException("您传递的method参数值 " + methodName + "不存在！");
		}
		try {
			result = (String) method.invoke(this, request, response); //调用方法
		} catch (Exception e) {
			throw new RuntimeException("您想调用的方法: " + methodName + 
					"(HttpServletRequest, HttpServletResponse)，内部发生了异常！" +
					"\r\n异常内容是" + e.toString() + e.getLocalizedMessage());
//			e.printStackTrace();
		}
		
		//3.通过返回值，增加请求转发或重定向功能，以后还能扩展
		if(result == null || result.trim().equals(""))	return ;
		if(result.contains(":")){
			int index = result.indexOf(":");
			String prefix = result.substring(0, index); //要操作的内容
			String path = result.substring(index+1);	//地址
			if("qqzf".equals(prefix)){
				path = response.encodeURL(path);
				request.getRequestDispatcher(path).forward(request, response);
				return;
			}else if("cdx".equals(prefix)){
				path = response.encodeURL(request.getContextPath() + path);
				response.sendRedirect(path);
				return;
			}else{
				throw new RuntimeException("您指定的操作：" + prefix + "，当前版本不支持！");
			}
		}else{
			//没有写冒号，默认为重定向
			result = response.encodeURL(request.getContextPath() + result);
			response.sendRedirect(result);
			return;
		}
	}
}
