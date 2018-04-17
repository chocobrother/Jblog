package com.cafe24.jblog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.jblog.service.BlogService;
import com.cafe24.jblog.vo.BlogVo;
import com.cafe24.jblog.vo.CategoryVo;
import com.cafe24.jblog.vo.PostVo;
import com.cafe24.jblog.vo.UserVo;
import com.cafe24.security.Auth;
import com.cafe24.security.AuthUser;


@Controller
@RequestMapping("/{id:(?!uploads|assets).*}")
public class BlogController {

	@Autowired
	private BlogService blogService;
	
	@Auth(role=Auth.Role.ADMIN)
	@RequestMapping("")
	public String main(Model model,
			@PathVariable("id") String id) {
		
		BlogVo vo = blogService.getlist();
	
		List<CategoryVo> list = blogService.categorylist();
		
		List<PostVo> postlist = blogService.postlist();
		
		Map<String,Object> map = new HashMap<String,Object>();
			
		//블로그 제목 위함
		map.put("vo", vo);
		
		//카테고리 리스트
		map.put("list", list);
		
		//post 리스트
		map.put("postlist", postlist);
		
		model.addAttribute("map",map);
		

	
		
		return "blog/blog-main";
	}
	
//	//카테고리 번호 
	@RequestMapping("/main2/{no}")
	public String main2(Model model,
			@PathVariable("no") Long no,
			@AuthUser UserVo vo1) {
		
		
		System.out.println("테스트~~~");
		BlogVo vo = blogService.getlist();
				
		List<CategoryVo> list = blogService.categorylist();
		
		List<PostVo> postlist = null;  
		
		postlist = blogService.catepostlist(no);
	
		Map<String,Object> map = new HashMap();
		
		map.put("list", list);
		
		map.put("postlist", postlist);
		
		map.put("vo", vo);
		
		model.addAttribute("map",map);		
		
		
//		return "redirect:/"+vo1.getId();
		return "blog/blog-main";
		
}
//글 넘버
	//카테번호
	@RequestMapping("/main3/{no}")
	public String main3(Model model,
			@PathVariable("no") Long no,
			@AuthUser UserVo vo1) {
		
		
		Long cate_no = blogService.getcateno(no);
		
		
		System.out.println("Cate nonooo : " + cate_no);
		
		BlogVo vo = blogService.getlist();
		
		PostVo postvo = blogService.getpostbyno(no);
				
		List<CategoryVo> list = blogService.categorylist();
		
		List<PostVo> postlist = null;  
		
		postlist = blogService.catepostlist(cate_no);
	
		Map<String,Object> map = new HashMap();
		
		map.put("list", list);
		
		map.put("postlist", postlist);
		
		map.put("vo", vo);
		
		map.put("postvo",postvo);
		
		model.addAttribute("map",map);		
		
		
//		return "redirect:/"+vo1.getId();
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
//	@RequestMapping("/update","/{path1}")
	@RequestMapping(value="/{path1}", method=RequestMethod.POST)
	public String update(
						@ModelAttribute BlogVo vo,
						@PathVariable("path1") Optional<Long> path1,
						@PathVariable("id") String id,
//						@RequestParam(value = "user_no", required = false)String user_no1,
						@RequestParam("image_file") MultipartFile multipartFile,
						Model model) {
		
		Long user_no = 0L;
		
		if(path1.isPresent()) {
			user_no = path1.get();
		}
		
		
		vo.setUser_no(user_no);
		
		String url = blogService.restore(multipartFile);
		
		System.out.println("picture url " + url);
	
		vo.setImage(url);
		
		blogService.update(vo);
		
		return "redirect:/"+id;
	}
	
	@Auth
	@RequestMapping("/category")
	public String category(Model model) {
		
		BlogVo vo = blogService.getlist();
		
		
		List<CategoryVo> list = null;  
		
		list = blogService.getCategory();
		
		Map<String,Object> map = new HashMap();
		
		map.put("list", list);
		
		map.put("vo", vo);
		
		model.addAttribute("map",map);
		
		
		return "blog/blog-admin-category";
	}
	
	
	//카테고리 별로 글 분류 
	@RequestMapping("/catepostlist/{no}")
	public String catepostlist(
			@PathVariable("no") Long cate_no,
			Model model) {
		
		
		
		
		return "blog/list";
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
	@RequestMapping(value = "/write",  method=RequestMethod.GET)
	public String post(Model model	) {
		
		System.out.println(" 포스트 에드 확인");
		
		BlogVo vo = blogService.getlist();
		
		List<CategoryVo> list = blogService.categorylist();
	
		Map<String,Object> map = new HashMap();
		
		map.put("list", list);
		
		map.put("vo", vo);
		
		model.addAttribute("map",map);
		
		return "blog/blog-admin-write";
	}
	
	@Auth
	@RequestMapping(value = "/postadd",  method=RequestMethod.GET)
	public String postadd(@ModelAttribute PostVo vo) {
				
		Long cate_no = vo.getCate_no();
		
		System.out.println("post add +" + cate_no);
		
		blogService.postcount(cate_no);
		
		blogService.postadd(vo);
		
//		return "redirect:/blog/main";
		return "redirect:/blog/write";
	}
	

}
