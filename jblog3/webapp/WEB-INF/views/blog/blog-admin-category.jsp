<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.servletContext.contextPath }/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<div id="header">
			<h1>Spring 이야기</h1>
			<c:import url ="/WEB-INF/views/includes/blog-header.jsp" />
		</div>
		<div id="wrapper">
			<div id="content" class="full-screen">
				<ul class="admin-menu">
					<li><a href="${pageContext.servletContext.contextPath }/blog/admin">기본설정</a></li>
					<li class="selected"><a href="${pageContext.servletContext.contextPath }/blog/category">카테고리</a></li>
					<li><a href="${pageContext.servletContext.contextPath }/blog/write">글작성</a></li>
				</ul>
				<c:forEach items="${ list }" var="list" varStatus="status">
		      	<table class="admin-cat">
		      		<tr>
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>포스트 수</th>
		      			<th>설명</th>
		      			<th>삭제</th>      			
		      		</tr>
					<tr>
						<td>${list.no}</td>
						<td>${list.posttype }</td>
						<td>10</td>
						<td>${list.content }</td>
						<td>

						<a href="${pageContext.servletContext.contextPath }/blog/categorydelete?no=${list.no}" class="del">
						<img src="${pageContext.servletContext.contextPath }/assets/images/delete.jpg">
						</a>
						</td>
	
					</tr>  
				</table>
				<br/>
      	</c:forEach>
      			<h4 class="n-c">새로운 카테고리 추가</h4>
		      	<form action="${pageContext.servletContext.contextPath }/blog/categoryadd" method="post">
		      	<table id="admin-cat-add">
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input type="text" name="posttype"></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input type="text" name="content"></td>
		      		</tr>
		      		<tr>
		      			<td class="s">&nbsp;</td>
		      			<td><input type="submit" value="카테고리 추가"></td>
		      		</tr>      		      		
		      	</table> 
		     	 </form>
			</div>
		</div>
		<div id="footer">
			<p>
			<c:import url ="/WEB-INF/views/includes/blog-footer.jsp" />
			</p>
		</div>
	</div>
</body>
</html>