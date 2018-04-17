package com.cafe24.security;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cafe24.jblog.vo.UserVo;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		// 1. handler 종류 확인
		if (handler instanceof HandlerMethod == false) {
			return true;
		}

		// 2.casting
		HandlerMethod handlerMethod = (HandlerMethod) handler;

		// 3. @Auth 받아오기.
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);

		// 4. Method에 @Auth가 없는 경우

		if (auth == null) {
			return true;
		}

		// 5. @Auth 가 붙어있는 경우, 인증여부 체크

		HttpSession session = request.getSession();

		if (session == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");

			return false;
		}

		UserVo authUser = (UserVo) session.getAttribute("authUser");

		if (authUser == null) {
			response.sendRedirect(request.getContextPath() + "/main");
			return false;
		}

		// 관리자인지 아닌지.

		//1.첫번째 방법
		System.out.println("interceptor id " + request.getRequestURI());
		String uri = request.getRequestURI();
		String userId = uri.substring(uri.lastIndexOf('/') + 1, uri.length());

		System.out.println("SUBstring id = " + userId);

		//2. 두번째 방법
		Map<?, ?> map = (Map<?, ?>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		String ss = (String) map.get("id");

		System.out.println("handlerMapping iD" + ss);

//		sif (authUser.getId().equals(userId))

			// 6. 접근허가
			return true;

	}

}
