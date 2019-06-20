package com.gray.wei.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.GenericFilterBean;

import com.alibaba.fastjson.JSONObject;

/**
 * 过滤器
 * 
 * @author TianWei
 *
 */
public class SdFilter extends GenericFilterBean {

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		PrintWriter writer = null;
		try {
			JSONObject jo = new JSONObject();
			HttpServletRequest request = (HttpServletRequest) req;
			HttpServletResponse response = (HttpServletResponse) res;
			response.setContentType("text/plain;charset=UTF-8");
			String remoteIp = request.getHeader("x-forwarded-for");
			if (remoteIp == null || remoteIp.length() == 0 || remoteIp.equalsIgnoreCase("unknown")) {
				remoteIp = request.getHeader("Proxy-Client-IP");
				if (remoteIp == null || remoteIp.length() == 0 || remoteIp.equalsIgnoreCase("unknown")) {
					remoteIp = request.getHeader("WL-Proxy-Client-IP");
					if (remoteIp == null || remoteIp.length() == 0 || remoteIp.equalsIgnoreCase("unknown")) {
						remoteIp = req.getRemoteAddr();
					}
				}
			}
			String requestURI = request.getRequestURI();// 访问地址
			if (requestURI != null) {
				chain.doFilter(request, response);// 直接通过
			} else {
				jo.put("url", requestURI);
				writer = response.getWriter();
				writer.print(jo);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				writer.close();
			}
		}

	}

}
