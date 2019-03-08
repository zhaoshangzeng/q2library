package cn.library.user.web.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		//1得到session，判断admin是否有值
		HttpServletRequest request = (HttpServletRequest)req;
		String name = (String)request.getSession().getAttribute("admin");
		//2有则放行，无则到错误页面
		if(name != null){
			chain.doFilter(req, resp);
		}else{
			request.getRequestDispatcher("/errors/errorAdmin.jsp").forward(request, (HttpServletResponse)resp);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
