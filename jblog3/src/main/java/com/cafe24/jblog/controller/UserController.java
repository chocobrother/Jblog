package com.cafe24.jblog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cafe24.jblog.repository.UserDao;
import com.cafe24.jblog.service.UserService;
import com.cafe24.jblog.vo.BlogVo;
import com.cafe24.jblog.vo.UserVo;
import com.cafe24.mysite.exception.UserDaoException;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() {
		return "user/join";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@ModelAttribute UserVo vo,
					   HttpSession session) {
		
		userService.join(vo);
		
		
//		userService.insert();
		
		return "redirect:/user/joinsuccess";
			
	}

	@RequestMapping(value = "/joinsuccess")
	public String joinsuccess() {
		return "user/joinsuccess";
	}

	@RequestMapping(value = "/login",method = RequestMethod.GET)
	public String login() {
		return "user/login";
	}
	
//	@RequestMapping(value = "/login",method = RequestMethod.POST)
//	public String login(HttpSession session,
//			@ModelAttribute UserVo vo,
//			Model model) throws UserDaoException {
//		
//		UserVo authUser = userService.getUser(vo);
//				
//		if(authUser == null) {
//			model.addAttribute("result","fail");
//		
//			return "user/login";
//		}
//		
//		//인증처리
//		session.setAttribute("authUser", authUser);
//		
//	
//		
//		return "redirect:/main";
//	}
//	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("authUser");
		session.invalidate();
		
		return "redirect:/main";
	}
	
}
