package cn.library.admin.account.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.library.admin.account.service.AccountService;
import cn.library.utils.BaseServletUtils;

public class AccountServlet extends BaseServletUtils {

	/**
	 * 序列化id
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 依赖对象
	 */
	private AccountService accountService = new AccountService();
	
	//流程
	//修改密码流程
	//1获得表单信息
	//2校验原密码是否正确，不正确则回显错误
	//3正确则再校验确认密码是否一致，不正确则回显错误
	//4都正确则进行密码修改，之后回显修改成功
	//安全退出流程
	//1清除session的admin的值
	//2重定向到首页
	
	/**
	 * 修改密码
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String updatePassword(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//1获得表单信息
		String oripass = request.getParameter("oripass").trim();
		
		//2校验原密码是否正确，不正确则回显错误
		String pass = accountService.findPassword("q2library");
		if(!pass.equals(oripass)){
			request.getSession().setAttribute("accountMeg", "原密码错误！");
			return "qqzf:/WEB-INF/jsps/admin/updateAccount.jsp";
		}
		
		//3正确则再校验确认密码是否一致，不正确则回显错误
		String newpass = request.getParameter("newpass").trim();
		String confirm = request.getParameter("confirm").trim();
		if(!confirm.equals(newpass)){
			request.getSession().setAttribute("accountMeg", "两次填写新密码不一致！");
			return "qqzf:/WEB-INF/jsps/admin/updateAccount.jsp";
		}
		
		//4都正确则进行密码修改，之后回显修改成功
		accountService.updateAccount("q2library", newpass);
		request.getSession().setAttribute("accountMeg", "修改成功！");
		return "qqzf:/WEB-INF/jsps/admin/updateAccount.jsp";
	}
	
	/**
	 * 退出登录
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String logout(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//1清除session的admin的值
		request.getSession().removeAttribute("admin");
		
		//2重定向到首页
		return "/";
	}
}
