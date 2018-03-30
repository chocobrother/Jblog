package com.cafe24.jblog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.jblog.service.BlogService;
import com.cafe24.jblog.vo.BlogVo;
import com.cafe24.jblog.vo.CategoryVo;
import com.cafe24.jblog.vo.PostVo;
import com.cafe24.security.Auth;

@Controller
@RequestMapping("/blog")
public class BlogController {

	@Autowired
	private BlogService blogService;
	
	
	@RequestMapping("/main")
	public String main(Model model) {
		
		BlogVo vo = blogService.getlist();
	
		List<CategoryVo> list = blogService.categorylist();
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		
		map.put("vo", vo);
		map.put("list", list);
		
		model.addAttribute("map",map);
		
//		model.addAttribute("vo",vo);
	
		
		return "blog/blog-main";
	}
	
	@Auth
	@RequestMapping("/admin")
	public String admin(Model model) {
		
		
		BlogVo vo = blogService.getlist();
		
		model.addAttribute("vo",vo);
		
		
		return "blog/blog-admin-basic";
	}
	
	@Auth
	@RequestMapping("/update")
	public String update(@ModelAttribute BlogVo vo,
						@RequestParam(value = "user_no", required = false)String user_no1,
						@RequestParam("image_file") MultipartFile multipartFile,
						Model model) {
		
		Long user_no = Long.parseLong(user_no1);
		System.out.println("dddddddddddddddddddd");
		vo.setUser_no(user_no);
		
		
		String url = blogService.restore(multipartFile);
	
		vo.setImage(url);
		
		blogService.update(vo);
		
//		Map<String,Object> map = new HashMap<String,Object>();
		
		model.addAttribute("url", url);
//		System.out.println("usr~~~~" + url);
//		map.put("vo", vo);
//		map.put("url", url);

		return "redirect:/blog/main";
	}
	
	@Auth
	@RequestMapping("/category")
	public String category(Model model) {
		
		List<CategoryVo> list = null;  
		
		list = blogService.getCategory();
		
		model.addAttribute("list",list);
		
		
		return "blog/blog-admin-category";
	}
	
	@Auth
	@RequestMapping("/categoryadd")
	public String categoryadd(@ModelAttribute CategoryVo vo) {
		
		BlogVo blogvo = blogService.getlist();
		
		Long user_no = blogvo.getUser_no();
		
		Map<String, Object> map = new HashMap<String, Object>();
	
		map.put("vo", vo);
		map.put("user_no", user_no);
		
		
		blogService.categoryadd(map);
		
		 return "redirect:/blog/category";
	}
	
	@Auth
	@RequestMapping("/categorydelete")
	public String categorydelete(@RequestParam(value = "no",required = false)String no1
								) {
		
		
		Long no = Long.parseLong(no1);
		
		blogService.categorydelete(no);
		
		 return "redirect:/blog/category";
	}
	
	@Auth
	@RequestMapping("/write")
	public String post(Model model	) {
		
		List<CategoryVo> list = blogService.categorylist();
	
		model.addAttribute("list",list);
		
		return "blog/blog-admin-write";
	}
	
	@Auth
	@RequestMapping("/postadd")
	public String postadd(@ModelAttribute PostVo vo) {
		
	  
		Long cate_no = vo.getCate_no();
		
		blogService.postadd(vo);
		
		return "redirect:/blog/main";
	}
	
}
