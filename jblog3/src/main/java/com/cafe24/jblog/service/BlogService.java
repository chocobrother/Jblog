package com.cafe24.jblog.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.jblog.repository.BlogDao;
import com.cafe24.jblog.vo.BlogVo;
import com.cafe24.jblog.vo.CategoryVo;
import com.cafe24.jblog.vo.PostVo;

@Service
public class BlogService {

	@Autowired
	private BlogDao blogDao;

	private static String SAVE_PATH = "/uploads";
	private static String PREFIX_URL = "/uploads/";

	public void update(BlogVo vo) {

//		 System.out.println("blogservice !!" + vo.getTitle() + vo.getImage() +
//		 vo.getUser_no());

		

		blogDao.update(vo);

	

	}

	public String restore(MultipartFile multipartFile) {

		if(multipartFile ==null || multipartFile.getOriginalFilename().equals("")) {
			return null;
		}
		
		String url = "";

		try {
			String originFileName = multipartFile.getOriginalFilename();

			// 파일확장자명은 ----.png 이기 때문에 뒤에서 부터 찾음

			String extName = originFileName.substring(originFileName.lastIndexOf("."), originFileName.length());

			Long size = multipartFile.getSize();

			String saveFileName = genSaveFileName(extName);

			System.out.println("############" + originFileName);
			System.out.println("############" + extName);
			System.out.println("############" + size);
			System.out.println("############" + saveFileName);

			writeFile(multipartFile, saveFileName);

			url = PREFIX_URL + saveFileName;

		} catch (IOException ex) {

			throw new RuntimeException(ex);
		}

		return url;

	}

	private void writeFile(MultipartFile multipartFile, String saveFileName) throws IOException {

		byte[] fileData = multipartFile.getBytes();

		FileOutputStream fos = new FileOutputStream(SAVE_PATH + "/" + saveFileName);

		fos.write(fileData);

		fos.close();

	}

	private String genSaveFileName(String extName) {

		String fileName = "";

		Calendar calendar = Calendar.getInstance();

		fileName += calendar.get(Calendar.YEAR);
		fileName += calendar.get(Calendar.MONTH);
		fileName += calendar.get(Calendar.DATE);
		fileName += calendar.get(Calendar.HOUR);
		fileName += calendar.get(Calendar.MINUTE);
		fileName += calendar.get(Calendar.SECOND);
		fileName += calendar.get(Calendar.MILLISECOND);
		fileName += extName;

		return fileName;

	}

	public BlogVo getlist()

	{
		BlogVo vo = blogDao.getlist();

		return vo;
	}

	public List<CategoryVo> getCategory()

	{

		List<CategoryVo> list = blogDao.getCategory();

		return list;
	}


	
	public CategoryVo categoryadd(CategoryVo vo) {
	
	
	System.out.println("서비스에서 vo확인 하기" + vo);
		
	blogDao.categoryadd(vo);
	
	System.out.println("리턴 Vo 받아야지 서비스임");
	
		return vo;
	}
//	public Map categoryadd(Map map) {
//		
//		Map<String,Object> returnMap = new HashMap<String,Object>();	
//		
//		returnMap = blogDao.categoryadd(map);
//		
//		System.out.println("리턴맵을 받아야지 서비스임");
//		
//		return returnMap;
//	}

	public void categorydelete(Long no) {
		blogDao.categorydelete(no);
	}

	public List<CategoryVo> categorylist() {

		List<CategoryVo> list = blogDao.categorylist();

		return list;
	}

	public void postadd(PostVo vo) {
		blogDao.postadd(vo);
	}
	
	
	public List<PostVo> postlist()

	{

		List<PostVo> list = blogDao.postlist();

		return list;
	}

	public PostVo getpostbyno(Long no)

	{
		PostVo vo = blogDao.getpostbyno(no);

		return vo;
	}
	
	public List<PostVo> catepostlist(Long no)

	{

		List<PostVo> list = blogDao.catepostlist(no);

		return list;
	}


	public Long getcateno(Long no)

	{

		Long cate_no = blogDao.getcateno(no);

		return cate_no;
	}

	

	public void postcount(Long no)

	{

		blogDao.postcount(no);

		
	}

	
}
